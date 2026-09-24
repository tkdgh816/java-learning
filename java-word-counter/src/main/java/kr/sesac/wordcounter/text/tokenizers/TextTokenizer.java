package kr.sesac.wordcounter.text.tokenizers;

import java.util.List;

public interface TextTokenizer {
    List<String> tokenize(String text);
}
