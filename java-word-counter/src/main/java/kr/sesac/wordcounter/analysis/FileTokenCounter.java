package kr.sesac.wordcounter.analysis;

import kr.sesac.wordcounter.model.TokenCount;
import kr.sesac.wordcounter.text.reader.TextReader;
import kr.sesac.wordcounter.text.reader.TextReaderException;
import kr.sesac.wordcounter.text.tokenizer.TextTokenizer;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface FileTokenCounter {
    List<TokenCount> getTokenCounts();

    long getTotalTokenCount();
}