package kr.sesac.wordcounter.text.readers;

import kr.sesac.wordcounter.text.tokenizers.TextTokenizer;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
public class TxtReader implements TextReader {

    @Override
    public List<String> getTokens(Path filePath, TextTokenizer tokenizer) throws IOException {
        List<String> tokens = new ArrayList<>();

        try (BufferedReader reader = Files.newBufferedReader(filePath, StandardCharsets.UTF_8)) {
            String line;

            while ((line = reader.readLine()) != null) {
                tokens.addAll(tokenizer.tokenize(line));
            }
        }

        return tokens;
    }
}
