package kr.sesac.wordcounter.analysis;

import java.nio.file.Path;
import java.util.List;

public record AnalysisSummary(
        Path inputPath,
        FileAnalysisSummary fileSummary,
        WordAnalysisSummary wordSummary,
        long elapsedMilliseconds,
        List<FileAnalysisFailure> failures) {
}
