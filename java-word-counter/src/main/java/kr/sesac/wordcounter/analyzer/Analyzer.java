package kr.sesac.wordcounter.analyzer;

import kr.sesac.wordcounter.textreader.TextReader;
import kr.sesac.wordcounter.textreader.TextReaderKind;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Analyzer {
    private final Path inputPath;
    private final List<Path> filePaths;
    private int skippedCount;
    private int failedCount;

    private Analyzer(Path inputPath, List<Path> filePaths) {
        this.inputPath = inputPath;
        this.filePaths = filePaths;
    }

    public static Analyzer analyze(String pathString) throws IOException {
        Analyzer analyzer;
        try {
            Path inputPath = Path.of(pathString).normalize();
            List<Path> filePaths;

            if (Files.isDirectory(inputPath)) {
                filePaths = Files.list(inputPath).toList();
            } else if (Files.isRegularFile(inputPath)) {
                filePaths = List.of(inputPath);
            } else {
                throw new IllegalArgumentException("잘못된 경로입니다: " + inputPath);
            }

            analyzer = new Analyzer(inputPath, filePaths);
        } catch (InvalidPathException e) {
            throw new IllegalArgumentException("잘못된 경로 형식입니다: " + pathString);
        }

        analyzer.analyze();

        return analyzer;
    }

    public Path getInputPath() {
        return inputPath;
    }

    public void getTopWordsAndCounts() {

    }

    private void analyze() {
        Map<Path, TextReaderKind> map = new HashMap<>();
        for (Path filePath : filePaths) {
            TextReaderKind kind = getTextReaderKind(filePath);

            if (kind == null) {
                skippedCount++;
                continue;
            }

            map.put(filePath, kind);
        }

        for (var entry : map.entrySet()) {
            Path filePath = entry.getKey();
            try (BufferedReader bufferedReader = Files.newBufferedReader(filePath, StandardCharsets.UTF_8)) {
                TextReader textReader = entry.getValue().getTextReader();
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    textReader.read(line);
                }
            } catch (IOException e) {
                failedCount++;
            }
        }
    }

    public String getReport() {
        StringBuilder report = new StringBuilder();
        report.append(String.format("입력: %s%n", inputPath));
        int trialCount = filePaths.size();
        int successCount = trialCount - failedCount - skippedCount;
        report.append(String.format("파일: 시도 %d개 / 성공 %d개 / 실패 %d개 / 건너뜀 %d개%n", trialCount, successCount, failedCount, skippedCount));
        report.append(String.format("전체 단어: "));
        report.append(String.format("처리 시간: "));

        return report.toString();
    }

    private TextReaderKind getTextReaderKind(Path path) {
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