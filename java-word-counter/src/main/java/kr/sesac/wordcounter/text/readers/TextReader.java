package kr.sesac.wordcounter.text.readers;

import kr.sesac.wordcounter.text.tokenizers.TextTokenizer;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

/**
 * 텍스트 기반 문서를 형식에 맞게 읽고 분석에 필요한 정보를 제공합니다.
 */
public interface TextReader {
    /**
     * 지정한 파일을 읽고 토큰화 규칙을 적용한 토큰 목록을 반환합니다.
     * <p>반환 목록에는 같은 토큰이 여러 번 포함될 수 있으며, 토큰의 순서는 파일에서 읽은 순서를 따릅니다.</p>
     */
    List<String> getTokens(Path filePath, TextTokenizer tokenizer) throws IOException;
}
