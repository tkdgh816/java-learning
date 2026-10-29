package all.kakao.ex1_4_virus_pipes;

import helpers.Test;
import java.util.function.Supplier;

// K1-4 바이러스 파이프
// 문제: https://school.programmers.co.kr/learn/courses/30/lessons/468373
// PDF 출처: https://tech.kakao.com/posts/813
class Solution {
    public int solution(int n, int infection, int[][] edges, int k) {
        // TODO: 직접 구현하세요.
        return 0;
    }
}

public class Main {
    public static void main(String[] args) {
        Solution sol = new Solution();

        // 공식 예제 1
        Supplier<Integer> r1 = () -> sol.solution(10, 1, new int[][] { new int[] { 1, 2, 1 }, new int[] { 1, 3, 1 }, new int[] { 1, 4, 3 }, new int[] { 1, 5, 2 }, new int[] { 5, 6, 1 }, new int[] { 5, 7, 1 }, new int[] { 2, 8, 3 }, new int[] { 2, 9, 2 }, new int[] { 9, 10, 1 } }, 2);
        Test.check("r1", r1, 6);

        // 공식 예제 2
        Supplier<Integer> r2 = () -> sol.solution(7, 6, new int[][] { new int[] { 1, 2, 3 }, new int[] { 1, 4, 3 }, new int[] { 4, 5, 1 }, new int[] { 5, 6, 1 }, new int[] { 3, 6, 2 }, new int[] { 3, 7, 2 } }, 3);
        Test.check("r2", r2, 7);
    }
}
