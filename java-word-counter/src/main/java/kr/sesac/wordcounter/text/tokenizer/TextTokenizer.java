package kr.sesac.wordcounter.text.tokenizer;

import java.util.function.Consumer;

public interface TextTokenizer {
    void tokenize(String text, Consumer<String> consumer);
}
