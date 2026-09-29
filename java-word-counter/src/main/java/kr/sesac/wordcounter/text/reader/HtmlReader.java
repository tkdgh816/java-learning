package kr.sesac.wordcounter.text.reader;

import kr.sesac.wordcounter.text.tokenizer.TextTokenizer;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.jsoup.select.Evaluator;
import org.jsoup.select.Selector;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.function.Consumer;

public class HtmlReader implements TextReader {
    private final Evaluator excludedEvaluator;
    private final Evaluator targetEvaluator;

    public HtmlReader(String targetSelector, String excludedSelectors) {
        try {
            targetEvaluator = Selector.evaluatorOf(targetSelector);
            excludedEvaluator = Selector.evaluatorOf(excludedSelectors);
        } catch (Selector.SelectorParseException e) {
            throw new IllegalStateException("selector 파싱에 실패했습니다.", e);
        }
    }

    @Override
    public void readTokens(Path filePath, TextTokenizer tokenizer, Consumer<String> tokenConsumer) {
        try {
            Document document = Jsoup.parse(filePath.toFile(), StandardCharsets.UTF_8.name());

            Elements match = document.select(targetEvaluator);
            if (match.size() != 1) {
                throw new TextReaderException(targetEvaluator + " 요소가 없거나 여러 개입니다");
            }

            // 원본에 영향을 주지 않도록 복사
            Element element = match.getFirst().clone();
            element.select(excludedEvaluator).remove();

            tokenizer.tokenize(element.text(), tokenConsumer);
        } catch (IOException e) {
            throw new TextReaderException("HTML 문서를 읽을 수 없습니다.", e);
        }
    }
}
