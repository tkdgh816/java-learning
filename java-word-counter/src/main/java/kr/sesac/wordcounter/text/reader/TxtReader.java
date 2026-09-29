package kr.sesac.wordcounter.text.reader;

import kr.sesac.wordcounter.text.tokenizer.TextTokenizer;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.function.Consumer;

public class TxtReader implements TextReader {
    @Override
    public void readTokens(Path filePath, TextTokenizer tokenizer, Consumer<String> tokenConsumer) {
        try (BufferedReader reader = Files.newBufferedReader(filePath, StandardCharsets.UTF_8)) {
            String line;

            while ((line = reader.readLine()) != null) {
                tokenizer.tokenize(line, tokenConsumer);
            }
        } catch (IOException e) {
            throw new TextReaderException("TXT 문서를 읽을 수 없습니다.", e);
        }
    }
}
