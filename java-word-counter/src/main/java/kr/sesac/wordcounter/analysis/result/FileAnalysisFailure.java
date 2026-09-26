package kr.sesac.wordcounter.analysis.result;

import java.nio.file.Path;

public record FileAnalysisFailure(
        Path filePath,
        String reason) {
}
