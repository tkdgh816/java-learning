package kr.sesac.wordcounter.analysis;

import java.nio.file.Path;

public record FileAnalysisFailure(
        Path filePath,
        String reason) {
}
