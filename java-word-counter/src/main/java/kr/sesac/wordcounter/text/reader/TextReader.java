package kr.sesac.wordcounter.text.reader;

import kr.sesac.wordcounter.text.tokenizer.TextTokenizer;

import java.nio.file.Path;
import java.util.function.Consumer;

/**
 * 텍스트 기반 문서를 형식에 맞게 읽고 분석에 필요한 정보를 제공합니다.
 */
public interface TextReader {
    /**
     * 지정한 파일을 읽고 토큰화한 결과를 하나씩 소비자에게 전달합니다.
     *
     * <p>같은 토큰이 여러 번 발견되면 발견된 횟수만큼 전달하며,
     * 토큰은 문서에서 발견된 순서대로 전달합니다.</p>
     */
    void readTokens(Path filePath, TextTokenizer tokenizer, Consumer<String> tokenConsumer);
}
