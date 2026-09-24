package kr.sesac.wordcounter.text.readers;

import kr.sesac.wordcounter.text.tokenizers.TextTokenizer;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

public class HtmlReader implements TextReader {
    private static final String EXCLUDED_SELECTORS = "script, style, nav, header, footer";

    private final String selector;

    public HtmlReader(String selector) {
        this.selector = selector;
    }

    @Override
    public List<String> getTokens(Path filePath, TextTokenizer tokenizer) throws IOException {
        Document document = Jsoup.parse(filePath.toFile(), "UTF-8");
        Elements matches = document.select(selector);
        if (matches.size() != 1) {
            throw new IOException("본문 요소가 없거나 여러 개입니다");
        }

        Element element = matches.getFirst();
        element.select(EXCLUDED_SELECTORS).remove();

        return tokenizer.tokenize(element.text());
    }
}
