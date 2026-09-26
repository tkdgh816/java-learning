package kr.sesac.wordcounter.analysis;

import kr.sesac.wordcounter.model.TokenCount;
import kr.sesac.wordcounter.text.reader.TextReader;
import kr.sesac.wordcounter.text.tokenizer.TextTokenizer;

import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class FileTokenCounter {
    private final Path filePath;
    private final TextReader textReader;
    private final TextTokenizer tokenizer;

    private final Map<String, Long> countsByToken = new HashMap<>();
    private long wordCount;

    FileTokenCounter(Path filePath, TextReader textReader, TextTokenizer tokenizer) throws IOException {
        this.filePath = filePath;
        this.textReader = textReader;
        this.tokenizer = tokenizer;

        analyze();
    }

    private void analyze() throws IOException {
        textReader.readTokens(filePath, tokenizer, token -> {
            countsByToken.put(token, countsByToken.getOrDefault(token, 0L) + 1L);
            wordCount++;
        });
    }

    public List<TokenCount> getTokenCounts() {
        return countsByToken.entrySet().stream()
                .map(entry -> new TokenCount(entry.getKey(), entry.getValue()))
                .toList();
    }

    public long getWordCount() {
        return wordCount;
    }
}