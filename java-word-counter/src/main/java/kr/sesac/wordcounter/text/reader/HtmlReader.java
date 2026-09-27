package kr.sesac.wordcounter.text.reader;

import kr.sesac.wordcounter.text.tokenizer.TextTokenizer;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.nio.file.Path;
import java.util.function.Consumer;

public class HtmlReader implements TextReader {
    private static final String EXCLUDED_SELECTORS = "script, style, nav, header, footer";

    private final String selector;

    public HtmlReader(String selector) {
        this.selector = selector;
    }

    @Override
    public void readTokens(Path filePath, TextTokenizer tokenizer, Consumer<String> tokenConsumer) throws IOException {
        Document document = Jsoup.parse(filePath.toFile(), "UTF-8");
        Elements matches = document.select(selector);
        if (matches.size() != 1) {
            throw new IOException("본문 요소가 없거나 여러 개입니다");
        }

        Element element = matches.getFirst();
        element.select(EXCLUDED_SELECTORS).remove();

        tokenizer.tokenize(element.text(), tokenConsumer);
    }
}
