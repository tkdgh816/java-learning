package kr.sesac.wordcounter.analysis;

import kr.sesac.wordcounter.model.TokenCount;
import kr.sesac.wordcounter.text.reader.TextReader;
import kr.sesac.wordcounter.text.reader.TextReaderException;
import kr.sesac.wordcounter.text.tokenizer.TextTokenizer;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class SourceFileTokenCounter implements FileTokenCountResult {
    private final Path filePath;
    private final TextReader textReader;
    private final TextTokenizer tokenizer;

    private final Map<String, Long> countsByToken = new HashMap<>();
    private long totalTokenCount;

    private SourceFileTokenCounter(Path filePath, TextReader textReader, TextTokenizer tokenizer) throws TextReaderException {
        this.filePath = filePath;
        this.textReader = textReader;
        this.tokenizer = tokenizer;
    }

    public static SourceFileTokenCounter countTokens(Path filePath, TextReader textReader, TextTokenizer tokenizer) throws TextReaderException {
        SourceFileTokenCounter tokenCounter = new SourceFileTokenCounter(filePath, textReader, tokenizer);

        // 파일 분석 시작
        tokenCounter.analyze();

        return tokenCounter;
    }

    private void analyze() {
        textReader.readTokens(filePath, tokenizer, token -> {
            countsByToken.put(token, countsByToken.getOrDefault(token, 0L) + 1L);
            totalTokenCount++;
        });
    }

    @Override
    public List<TokenCount> getTokenCounts() {
        return countsByToken.entrySet().stream()
                .map(entry -> new TokenCount(entry.getKey(), entry.getValue()))
                .toList();
    }

    @Override
    public long getTotalTokenCount() {
        return totalTokenCount;
    }
}
