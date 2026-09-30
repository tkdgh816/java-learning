package kr.sesac.wordcounter.text.reader;

import kr.sesac.wordcounter.text.tokenizer.TextTokenizer;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.csv.DuplicateHeaderMode;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class TsvReader extends DelimitedTableReader {
    private static final CSVFormat FORMAT = CSVFormat.TDF.builder()
            .setQuote(null) // 따옴표를 일반 문자로 취급
            .setHeader()
            .setSkipHeaderRecord(true)
            .setDuplicateHeaderMode(DuplicateHeaderMode.DISALLOW)
            .get();


    public TsvReader(Collection<String> targetHeaderNames) {
        super(targetHeaderNames);
    }

    @Override
    CSVFormat getFormat() {
        return FORMAT;
    }
}
