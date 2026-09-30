package kr.sesac.wordcounter.analysis.result;

import java.nio.file.Path;

public record AnalysisSummary(
        Path inputPath,
        FileAnalysisSummary fileSummary,
        WordAnalysisSummary wordSummary,
        long elapsedMilliseconds) {
}
