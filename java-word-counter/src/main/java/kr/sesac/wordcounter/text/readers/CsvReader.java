package kr.sesac.wordcounter.text.readers;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.DuplicateHeaderMode;

import java.util.List;

public class CsvReader extends DelimitedTableReader {
    private static final CSVFormat FORMAT = CSVFormat.RFC4180.builder()
            .setHeader()
            .setSkipHeaderRecord(true)
            .setDuplicateHeaderMode(DuplicateHeaderMode.DISALLOW)
            .get();


    public CsvReader(List<String> targetHeaderNames) {
        super(targetHeaderNames);
    }

    @Override
    CSVFormat getFormat() {
        return FORMAT;
    }
}
