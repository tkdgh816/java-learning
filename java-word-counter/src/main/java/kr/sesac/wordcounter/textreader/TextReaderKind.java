package kr.sesac.wordcounter.textreader;

import java.util.Locale;
import java.util.function.Supplier;

public enum TextReaderKind {
    TXT(TxtReader::new),
    CSV(CsvReader::new),
    TSV(TsvReader::new),
    HTML(HtmlReader::new);

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

    public TextReader getTextReader() {
        return supplier.get();
    }
}
