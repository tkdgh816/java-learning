package kr.sesac.wordcounter.analysis.result;

public record FileAnalysisSummary(
    int attemptedCount,
    int successfulCount,
    int skippedCount){

    public int failedCount() {
        return attemptedCount - successfulCount;
    }
}
