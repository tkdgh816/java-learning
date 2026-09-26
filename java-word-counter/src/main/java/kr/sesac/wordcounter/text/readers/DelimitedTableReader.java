package kr.sesac.wordcounter.text.readers;

import kr.sesac.wordcounter.text.tokenizers.TextTokenizer;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public abstract class DelimitedTableReader implements TextReader {
    private final Set<String> targetHeaderNames;

    DelimitedTableReader(List<String> targetHeaderNames) {
        this.targetHeaderNames = targetHeaderNames.stream()
                .map(String::strip)
                .collect(Collectors.toSet());
    }

    abstract CSVFormat getFormat();

    @Override
    public void readTokens(Path filePath, TextTokenizer tokenizer, Consumer<String> tokenConsumer) throws IOException {
        try (BufferedReader reader = Files.newBufferedReader(filePath, StandardCharsets.UTF_8);
             CSVParser parser = getFormat().parse(reader)) {

            // 헤더 이름은 대소문자를 구분하며 앞뒤 공백을 제거해 비교
            // 헤더 이름은 비어 있지 않고 중복되지 않아야 함
            Map<String, Integer> rawHeaderMap = parser.getHeaderMap();
            List<Integer> targetHeaderIndexes = getTargetHeaderIndexes(rawHeaderMap);

            for (CSVRecord record : parser) {
                // 레코드의 셀 수가 헤더와 다른 경우
                if (record.size() != rawHeaderMap.size()) {
                    throw new IOException("레코드의 셀 수가 헤더와 다릅니다.");
                }

                for (Integer targetHeaderIndex : targetHeaderIndexes) {
                    tokenizer.tokenize(record.get(targetHeaderIndex)).forEach(tokenConsumer);
                }
            }
        } catch (UncheckedIOException | IllegalArgumentException e) {
            throw new IOException(e.getMessage());
        }
    }

    // 입력 헤더와 일치하는 파일 헤더 인덱스를 반환
    private List<Integer> getTargetHeaderIndexes(Map<String, Integer> rawHeaderMap) throws IOException {
        List<Integer> targetHeaderIndexes = new ArrayList<>();

        // 파일 헤더의 앞뒤 공백을 제거하고 입력 헤더와 일치하는 헤더 인덱스를 추가
        for (var headerEntry : rawHeaderMap.entrySet()) {
            String header = headerEntry.getKey().strip();
            if (targetHeaderNames.contains(header)) {
                targetHeaderIndexes.add(headerEntry.getValue());
            }
        }

        // 입력 헤더와 일치하는 파일 헤더가 없는지 확인
        if (targetHeaderIndexes.size() != targetHeaderNames.size()) {
            throw new IOException("분석 열이 헤더에 없습니다.");
        }
        return targetHeaderIndexes;
    }
}
