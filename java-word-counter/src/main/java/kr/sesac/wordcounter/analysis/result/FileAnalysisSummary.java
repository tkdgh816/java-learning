package kr.sesac.wordcounter.analysis.result;

import java.util.List;

public record FileAnalysisSummary(
    int attemptedCount,
    int successfulCount,
    int skippedCount,
    List<FileAnalysisFailure> failures){

    public int failedCount() {
        return failures.size();
    }
}
