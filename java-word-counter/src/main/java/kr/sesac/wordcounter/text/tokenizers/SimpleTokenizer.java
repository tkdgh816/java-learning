package kr.sesac.wordcounter.text.tokenizers;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class SimpleTokenizer implements TextTokenizer {
    @Override
    public List<String> tokenize(String text) {
        List<String> tokens = new ArrayList<>();

        int startIndex = 0;
        boolean containsLetter = false;

        for (int i = 0; i < text.length(); i++) {
            char character = text.charAt(i);

            if (isNumber(character)) {
                continue;
            }

            if (isAlphabet(character) || isHangeul(character)) {
                containsLetter = true;
                continue;
            }

            if (containsLetter) {
                tokens.add(text.substring(startIndex, i).toLowerCase(Locale.ROOT));
            }

            startIndex = i + 1;
            containsLetter = false;
        }


        if (containsLetter) {
            tokens.add(text.substring(startIndex).toLowerCase(Locale.ROOT));
        }

        return tokens;
    }

    private boolean isNumber(char character) {
        return character >= '0' && character <= '9';
    }

    private boolean isAlphabet(char character) {
        return (character >= 'A' && character <= 'Z') || (character >= 'a' && character <= 'z');
    }

    private boolean isHangeul(char character) {
        return (character >= '가' && character <= '힣') || (character >= 'ㄱ' && character <= 'ㅎ') || (character >= 'ㅏ' && character <= 'ㅣ');
    }
}
