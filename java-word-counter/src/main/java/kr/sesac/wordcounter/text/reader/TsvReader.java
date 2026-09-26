package kr.sesac.wordcounter.text.reader;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.DuplicateHeaderMode;

import java.util.List;

public class TsvReader extends DelimitedTableReader {
    private static final CSVFormat FORMAT = CSVFormat.newFormat('\t').builder()
            .setHeader()
            .setSkipHeaderRecord(true)
            .setDuplicateHeaderMode(DuplicateHeaderMode.DISALLOW)
            .get();


    public TsvReader(List<String> targetHeaderNames) {
        super(targetHeaderNames);
    }

    @Override
    CSVFormat getFormat() {
        return FORMAT;
    }
}
