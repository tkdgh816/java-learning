package kr.sesac.wordcounter.analysis;

import kr.sesac.wordcounter.analysis.result.AnalysisSummary;
import kr.sesac.wordcounter.analysis.result.FileAnalysisFailure;
import kr.sesac.wordcounter.analysis.result.FileAnalysisSummary;
import kr.sesac.wordcounter.analysis.result.WordAnalysisSummary;
import kr.sesac.wordcounter.model.TokenCount;
import kr.sesac.wordcounter.repository.TokenCountRepository;
import kr.sesac.wordcounter.text.reader.TextReader;
import kr.sesac.wordcounter.text.reader.TextReaderKind;
import kr.sesac.wordcounter.text.tokenizer.TextTokenizer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

public class Analyzer {
    private final TextTokenizer tokenizer;
    private final TokenCountRepository repository;
    private long totalTokenCount;    // 전체 분석 단어 횟수
    private int successfulFileCount;   // 지원 파일 분석 성공 횟수
    private final AnalysisSummary analysisSummary;
    private final List<FileAnalysisFailure> failures = new ArrayList<>();

    public static Analyzer analyze(String pathString, TextTokenizer tokenizer, TokenCountRepository repository, AnalysisMode analysisMode) throws IOException{
        return new Analyzer(pathString, tokenizer, repository, analysisMode);
    }

    private Analyzer(String pathString, TextTokenizer tokenizer, TokenCountRepository repository, AnalysisMode analysisMode) throws IOException {
        if (tokenizer == null) {
            throw new IllegalArgumentException("tokenizer는 null일 수 없습니다.");
        }

        if (repository == null) {
            throw new IllegalArgumentException("repository는 null일 수 없습니다.");
        }

        if (analysisMode == null) {
            throw new IllegalArgumentException("analysisMode는 null일 수 없습니다.");
        }

        Path inputPath;
        Map<Path, TextReaderKind> readerKindsByPath = new HashMap<>();
        int skippedFileCount = 0;

        try {
            inputPath = Path.of(pathString);
            List<Path> filePaths;

            if (Files.isDirectory(inputPath)) {
                // 경로가 디렉터리인 경우 하위 파일(디렉터리 제외) 경로들을 목록에 넣음
                try (var fileListStream = Files.list(inputPath)) {
                    filePaths = fileListStream.filter(Files::isRegularFile).toList();
                }
            } else if (Files.isRegularFile(inputPath)) {
                // 경로가 파일인 경우 그 경로를 목록에 넣음
                filePaths = List.of(inputPath);
            } else {
                throw new IllegalArgumentException("잘못된 경로입니다: " + inputPath);
            }


            for (Path filePath : filePaths) {
                TextReaderKind kind = getTextReaderKind(filePath);

                if (kind == null) {
                    skippedFileCount++;
                    continue;
                }

                readerKindsByPath.put(filePath, kind);
            }
        } catch (InvalidPathException e) {
            // 경로 형식 자체가 잘못되었을 때
            throw new IllegalArgumentException("잘못된 경로 형식입니다: " + pathString);
        }

        if (readerKindsByPath.isEmpty()) {
            throw new IllegalArgumentException("지원하는 파일이 없습니다. 지원 확장자: txt, csv, tsv, html, htm");
        }

        this.tokenizer = tokenizer;
        this.repository = repository;


        long elapsedNanoTime = System.nanoTime();
        // 분석 모드에 따라 분석 시작 (순차/병렬 처리)
        switch (analysisMode) {
            case AnalysisMode.SEQUENTIAL -> analyzeSequential(readerKindsByPath);
            case AnalysisMode.FIXED_THREAD_POOL -> analyzeFixedThreadPool(readerKindsByPath);
        }
        // 분석 처리 시간 기록
        elapsedNanoTime = System.nanoTime() - elapsedNanoTime;

        FileAnalysisSummary fileSummary = new FileAnalysisSummary(readerKindsByPath.size(), successfulFileCount, skippedFileCount);
        WordAnalysisSummary wordSummary = new WordAnalysisSummary(totalTokenCount, repository.getUniqueCount());
        analysisSummary = new AnalysisSummary(
                inputPath,
                fileSummary,
                wordSummary,
                elapsedNanoTime / 1_000_000L,
                List.copyOf(failures));
    }

    public boolean hasSuccessfulFiles() {
        return successfulFileCount > 0;
    }

    private void analyzeSequential(Map<Path, TextReaderKind> readerKindsByPath) {
        for (var entry : readerKindsByPath.entrySet()) {
            Path filePath = entry.getKey();
            // 파일 유형에 해당하는 TextReader 가져옴
            TextReader textReader = entry.getValue().createReader();

            // 파일을 분석하고 결과를 집계, 실패 시 실패 목록에 추가
            try {
                FileTokenCounter fileTokenCounter = new FileTokenCounter(filePath, textReader, tokenizer);
                integrate(fileTokenCounter);
            } catch (IOException e) {
                failures.add(new FileAnalysisFailure(filePath, e.getMessage()));
            }
        }
    }

    private static final int MAX_THREAD_COUNT = 4;

    private void analyzeFixedThreadPool(Map<Path, TextReaderKind> readerKindsByPath) throws IOException {
        // 파일 경로와 파일 분석 결과
        Map<Path, Future<FileTokenCounter>> futuresByFilePath = new HashMap<>();

        // 적절한 스레드 개수를 계산하여 스레드 풀 생성
        int threadCount = Math.min(readerKindsByPath.size(), MAX_THREAD_COUNT);
        try (ExecutorService executorService = Executors.newFixedThreadPool(threadCount)) {
            for (var entry : readerKindsByPath.entrySet()) {
                Path filePath = entry.getKey();

                // 개별 파일 분석
                Future<FileTokenCounter> future = executorService.submit(() -> {
                    TextReader textReader = entry.getValue().createReader();
                    return new FileTokenCounter(filePath, textReader, tokenizer);
                });

                futuresByFilePath.put(filePath, future);
            }
        }

        for (var entry : futuresByFilePath.entrySet()) {
            Path filePath = entry.getKey();
            Future<FileTokenCounter> future = entry.getValue();

            try {
                // 개별 파일 분석 작업 완료를 기다리고 결과 통합
                FileTokenCounter fileTokenCounter = future.get();
                integrate(fileTokenCounter);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new IOException();
            } catch (ExecutionException e) {
                // 개별 파일 분석 실패 시 실패 목록에 추가
                if (e.getCause() instanceof IOException ioException) {
                    failures.add(new FileAnalysisFailure(filePath, ioException.getMessage()));
                    continue;
                }
                throw new IOException("분석 작업 실패: " + e.getCause().getMessage());
            } catch (CancellationException e) {
                throw new IOException("분석 작업 취소");
            }
        }
    }

    // 각 파일 분석 결과를 통합하여 집계
    private void integrate(FileTokenCounter fileTokenCounter) {
        if (fileTokenCounter != null) {
            repository.addAll(fileTokenCounter.getTokenCounts());
            totalTokenCount += fileTokenCounter.getWordCount();
            successfulFileCount++;
        }
    }

    // 지정된 개수의 상위 단어 목록 반환
    public List<TokenCount> getTopWords(int limit) {
        return repository.findTop(limit);
    }

    // 상위 단어들로 정렬된 모든 단어 목록 반환
    public List<TokenCount> getAllWordsSorted() {
        return repository.findAllSorted();
    }

    // 특정 단어 빈도 수 검색
    public TokenCount findByWord(String word) {
        List<String> filteredTokens = new ArrayList<>();
        tokenizer.tokenize(word, filteredTokens::add);

        if (filteredTokens.size() != 1) {
            throw new IllegalArgumentException("단어 하나를 입력하세요.");
        }

        String targetWord = filteredTokens.getFirst();
        return repository.findByToken(targetWord);
    }

    // 분석 요약 반환
    public AnalysisSummary getSummary() {
        return analysisSummary;
    }

    // 경로 검사하여 확장자 기반으로 TextReaderKind 반환
    // 실패하면 null 반환
    private static TextReaderKind getTextReaderKind(Path path) {
        String fileName = path.getFileName().toString();
        int dotIndex = fileName.lastIndexOf('.');
        if (dotIndex > 0) {
            try {
                return TextReaderKind.fromExtension(fileName.substring(dotIndex + 1));
            } catch (IllegalArgumentException e) {
                // 아래에서 null 반환
            }
        }

        return null;
    }
}