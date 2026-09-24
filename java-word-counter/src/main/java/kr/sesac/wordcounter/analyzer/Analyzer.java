package kr.sesac.wordcounter.analyzer;

import kr.sesac.wordcounter.text.TokenCount;
import kr.sesac.wordcounter.text.tokenizers.TextTokenizer;
import kr.sesac.wordcounter.text.readers.TextReader;
import kr.sesac.wordcounter.text.readers.TextReaderKind;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class Analyzer {
    private final Path inputPath;
    private final TextTokenizer tokenizer;
    private final Map<Path, TextReaderKind> readerKindsByPath = new HashMap<>();
    private final Map<String, Long> countsByToken = new HashMap<>();
    private long totalWordCount;    // 전체 단어 등장 횟수
    private int skippedFileCount;   // 지원 파일이 아니어서 건너뛴 횟수
    private int successfulFileCount;   // 지원 파일 분석 성공 횟수
    private long elapsedNanoTime;

    public Analyzer(String pathString, TextTokenizer tokenizer) throws IOException {
        if (tokenizer == null) {
            throw new IllegalArgumentException("tokenizer는 null일 수 없습니다.");
        }

        try {
            Path inputPath = Path.of(pathString);
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

            this.inputPath = inputPath;
        } catch (InvalidPathException e) {
            // 경로 형식 자체가 잘못되었을 때
            throw new IllegalArgumentException("잘못된 경로 형식입니다: " + pathString);
        }

        if (readerKindsByPath.isEmpty()) {
            throw new IllegalArgumentException("지원하는 파일이 없습니다. 지원 확장자: txt, csv, tsv, html, htm");
        }

        this.tokenizer = tokenizer;

        analyze();
    }

    public Path getInputPath() {
        return inputPath;
    }

    public boolean hasSuccessfulFiles() {
        return successfulFileCount > 0;
    }

    private void analyze() {
        long currentTime = System.nanoTime();
        for (var entry : readerKindsByPath.entrySet()) {
            Path filePath = entry.getKey();
            try {
                TextReader textReader = entry.getValue().createReader();
                for (String token : textReader.getTokens(filePath, tokenizer)) {
                    countsByToken.put(token, countsByToken.getOrDefault(token, 0L) + 1L);
                    totalWordCount++;
                }
                successfulFileCount++;
            } catch (IOException e) {
                System.out.println("파일 분석 실패: " + filePath);
                System.out.println("원인: " + e.getMessage());
            }
        }

        elapsedNanoTime = System.nanoTime() - currentTime;
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

    public List<TokenCount> getTopWords(int limit) {
        return countsByToken.entrySet().stream()
                .sorted(TOKEN_COUNT_COMPARATOR)
                .limit(limit)
                .map(entry -> new TokenCount(entry.getKey(), entry.getValue()))
                .toList();
    }

    public List<TokenCount> getAllWordsSorted() {
        return countsByToken.entrySet().stream()
                .sorted(TOKEN_COUNT_COMPARATOR)
                .map(entry -> new TokenCount(entry.getKey(), entry.getValue()))
                .toList();
    }

    public TokenCount findWord(String word) {
        List<String> filteredTokens = tokenizer.tokenize(word);

        if (filteredTokens.size() != 1) {
            throw new IllegalArgumentException("단어 하나를 입력하세요.");
        }

        String targetWord = filteredTokens.getFirst();
        return new TokenCount(targetWord, countsByToken.getOrDefault(targetWord, 0L));
    }

    public String getSummary() {
        StringBuilder summary = new StringBuilder();
        summary.append(String.format("입력: %s%n", inputPath));
        int attemptedFileCount = readerKindsByPath.size();
        summary.append(String.format("파일: 시도 %d개 / 성공 %d개 / 실패 %d개 / 건너뜀 %d개%n", attemptedFileCount, successfulFileCount, attemptedFileCount - successfulFileCount, skippedFileCount));
        summary.append(String.format("전체 단어: %d개 / 서로 다른 단어: %d개%n", totalWordCount, countsByToken.size()));
        summary.append(String.format("처리 시간: %dms%n", elapsedNanoTime / 1_000_000L));

        return summary.toString();
    }

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