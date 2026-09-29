package kr.sesac.wordcounter.text.reader;

import java.util.List;
import java.util.Locale;
import java.util.function.Supplier;

public enum TextReaderKind {
    TXT(TxtReader::new),
    CSV(() -> new CsvReader(List.of("text"))),
    TSV(() -> new TsvReader(List.of("document"))),
    HTML(() -> new HtmlReader("#content", "script, style, nav, header, footer"));

    private final Supplier<TextReader> supplier;

    TextReaderKind(Supplier<TextReader> supplier) {
        this.supplier = supplier;
    }

    public static TextReaderKind fromExtension(String extension) {
        return switch (extension.toLowerCase(Locale.ROOT)) {
            case "txt" -> TextReaderKind.TXT;
            case "csv" -> TextReaderKind.CSV;
            case "tsv" -> TextReaderKind.TSV;
            case "html", "htm" -> TextReaderKind.HTML;
            default -> throw new IllegalArgumentException("지원하지 않는 확장자입니다: " + extension);
        };
    }

    public TextReader createReader() {
        return supplier.get();
    }
}
