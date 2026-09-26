package kr.sesac.wordcounter.text.tokenizer;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 정규식을 사용하여 영어, 한글, 숫자로 구성된 토큰을 추출합니다.
 */
public class RegexTokenizer implements TextTokenizer {
    private static final Pattern TOKEN_PATTERN = Pattern.compile("[a-zA-Z0-9가-힣ㄱ-ㅎㅏ-ㅣ]+");
    private static final Pattern NUMERIC_PATTERN = Pattern.compile("[0-9]+");

    @Override
    public List<String> tokenize(String text) {
        List<String> tokens = new ArrayList<>();

        // 영어, 한글, 숫자로만 이루어진 토큰
        Matcher tokenMatcher = TOKEN_PATTERN.matcher(text);
        while (tokenMatcher.find()) {
            String token = tokenMatcher.group();

            // 숫자만으로 이루어진 토큰은 배제
            if (!NUMERIC_PATTERN.matcher(token).matches()) {
                tokens.add(token.toLowerCase(Locale.ROOT));
            }
        }

        return tokens;
    }
}
