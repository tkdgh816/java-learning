package kr.sesac.wordcounter.repository;

import kr.sesac.wordcounter.model.TokenCount;

import java.util.Collection;
import java.util.List;

public interface TokenCountRepository {
    /**
     * 서로 다른 토큰 개수를 반환합니다.
     */
    long getUniqueCount();

    /**
     * 토큰을 하나씩 전체 결과에 추가합니다.
     */
    void addAll(List<String> tokens);

    /**
     * 집계 결과를 전체 결과에 추가합니다.
     */
    void addAll(Collection<TokenCount> tokenCounts);

    /**
     * 출현 횟수 내림차순, 토큰 오름차순으로 상위 결과를 조회합니다.
     */
    List<TokenCount> findTop(int limit);

    /**
     * 특정 토큰의 집계 결과를 조회합니다.
     */
    TokenCount findByToken(String token);

    /**
     * 출현 횟수 내림차순, 토큰 오름차순으로 전체 결과를 조회합니다.
     */
    List<TokenCount> findAllSorted();
}
