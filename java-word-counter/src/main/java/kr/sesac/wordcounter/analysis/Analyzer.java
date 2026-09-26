package kr.sesac.wordcounter.analysis;

import kr.sesac.wordcounter.text.readers.TextReader;
import kr.sesac.wordcounter.text.readers.TextReaderKind;
import kr.sesac.wordcounter.text.tokenizers.TextTokenizer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.util.*;
import java.util.Map.Entry;
import java.util.concurrent.*;

public class Analyzer {
    private final TextTokenizer tokenizer;
    private final Map<String, Long> countsByToken = new HashMap<>();
    private long totalWordCount;    // 전체 단어 등장 횟수
    private int successfulFileCount;   // 지원 파일 분석 성공 횟수
    private final AnalysisSummary analysisSummary;
    private final List<FileAnalysisFailure> failures = new ArrayList<>();

    public Analyzer(String pathString, TextTokenizer tokenizer, AnalysisMode analysisMode) throws IOException {
        if (tokenizer == null) {
            throw new IllegalArgumentException("tokenizer는 null일 수 없습니다.");
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


        long elapsedNanoTime = System.nanoTime();
        switch (analysisMode) {
            case AnalysisMode.SEQUENTIAL -> analyzeSequential(readerKindsByPath);
            case AnalysisMode.FIXED_THREAD_POOL -> analyzeFixedThreadPool(readerKindsByPath);
        }
        elapsedNanoTime = System.nanoTime() - elapsedNanoTime;

        FileAnalysisSummary fileSummary = new FileAnalysisSummary(readerKindsByPath.size(), successfulFileCount, skippedFileCount);
        WordAnalysisSummary wordSummary = new WordAnalysisSummary(totalWordCount, countsByToken.size());
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
                FileAnalyzer fileAnalyzer = new FileAnalyzer(filePath, textReader, tokenizer);
                integrate(fileAnalyzer);
            } catch (IOException e) {
                failures.add(new FileAnalysisFailure(filePath, e.getMessage()));
            }
        }
    }

    private static final int MAX_THREAD_COUNT = 4;

    private void analyzeFixedThreadPool(Map<Path, TextReaderKind> readerKindsByPath) throws IOException {
        // 파일 경로와 파일 분석 결과
        Map<Path, Future<FileAnalyzer>> futuresByFilePath = new HashMap<>();

        // 적절한 스레드 개수를 계산하여 스레드 풀 생성
        int threadCount = Math.min(readerKindsByPath.size(), MAX_THREAD_COUNT);
        try (ExecutorService executorService = Executors.newFixedThreadPool(threadCount)) {
            for (var entry : readerKindsByPath.entrySet()) {
                Path filePath = entry.getKey();

                // 개별 파일 분석
                Future<FileAnalyzer> future = executorService.submit(() -> {
                    TextReader textReader = entry.getValue().createReader();
                    return new FileAnalyzer(filePath, textReader, tokenizer);
                });

                futuresByFilePath.put(filePath, future);
            }
        }

        for (var entry : futuresByFilePath.entrySet()) {
            Path filePath = entry.getKey();
            Future<FileAnalyzer> future = entry.getValue();

            try {
                // 개별 파일 분석 작업 완료를 기다리고 결과 통합
                FileAnalyzer fileAnalyzer = future.get();
                integrate(fileAnalyzer);
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
    private void integrate(FileAnalyzer fileAnalyzer) {
        if (fileAnalyzer != null) {
            for (TokenCount tokenCount : fileAnalyzer.getTokenCounts()) {
                String token = tokenCount.token();
                countsByToken.put(token, countsByToken.getOrDefault(token, 0L) + tokenCount.count());
            }
            totalWordCount += fileAnalyzer.getWordCount();
            successfulFileCount++;
        }
    }

    // 출현 횟수가 많은 단어가 먼저
    // 출현 횟수가 같으면 String.compareTo 오름차순
    private static final Comparator<Entry<String, Long>> TOKEN_COUNT_COMPARATOR =
            (e1, e2) -> {
                int countComparison = Long.compare(e2.getValue(), e1.getValue());
                if (countComparison == 0) {
                    return e1.getKey().compareTo(e2.getKey());
                }
                return countComparison;
            };

    // 지정된 개수의 상위 단어 목록 반환
    public List<TokenCount> getTopWords(int limit) {
        return countsByToken.entrySet().stream()
                .sorted(TOKEN_COUNT_COMPARATOR)
                .limit(limit)
                .map(entry -> new TokenCount(entry.getKey(), entry.getValue()))
                .toList();
    }

    // 상위 단어들로 정렬된 모든 단어 목록 반환
    public List<TokenCount> getAllWordsSorted() {
        return countsByToken.entrySet().stream()
                .sorted(TOKEN_COUNT_COMPARATOR)
                .map(entry -> new TokenCount(entry.getKey(), entry.getValue()))
                .toList();
    }

    // 특정 단어 빈도 수 검색
    public TokenCount findWord(String word) {
        List<String> filteredTokens = tokenizer.tokenize(word);

        if (filteredTokens.size() != 1) {
            throw new IllegalArgumentException("단어 하나를 입력하세요.");
        }

        String targetWord = filteredTokens.getFirst();
        return new TokenCount(targetWord, countsByToken.getOrDefault(targetWord, 0L));
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