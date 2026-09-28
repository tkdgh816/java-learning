package kr.sesac.wordcounter.analysis;

import kr.sesac.wordcounter.analysis.result.AnalysisSummary;
import kr.sesac.wordcounter.model.TokenCount;

import java.util.List;

public interface TokenAnalyzer {
    boolean hasSuccessfulFiles();
    List<TokenCount> getTopWords(int limit);
    List<TokenCount> getAllWordsSorted();
    TokenCount findByWord(String word);
    AnalysisSummary getSummary();
}
