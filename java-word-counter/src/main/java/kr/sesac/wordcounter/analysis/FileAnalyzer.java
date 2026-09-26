package kr.sesac.wordcounter.analysis;

import kr.sesac.wordcounter.text.readers.TextReader;
import kr.sesac.wordcounter.text.tokenizers.TextTokenizer;

import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class FileAnalyzer{
    private final Path filePath;
    private final TextReader textReader;
    private final TextTokenizer tokenizer;

    private final Map<String, Long> countsByToken = new HashMap<>();
    private long wordCount;

    FileAnalyzer(Path filePath, TextReader textReader, TextTokenizer tokenizer) throws IOException {
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