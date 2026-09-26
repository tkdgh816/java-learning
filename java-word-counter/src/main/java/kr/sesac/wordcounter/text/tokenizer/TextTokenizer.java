package kr.sesac.wordcounter.text.tokenizer;

import java.util.List;

public interface TextTokenizer {
    List<String> tokenize(String text);
}
