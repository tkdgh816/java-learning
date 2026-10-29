package all.kakao.ex1_5_organize_kakao_apps;

import helpers.Test;
import java.util.function.Supplier;

// K1-5 카카오 앱 정리하기
// 문제: https://school.programmers.co.kr/learn/courses/30/lessons/468374
// PDF 출처: https://tech.kakao.com/posts/813
class Solution {
    public int[][] solution(int[][] board, int[][] commands) {
        // TODO: 직접 구현하세요.
        return new int[0][];
    }
}

public class Main {
    public static void main(String[] args) {
        Solution sol = new Solution();

        // 공식 예제 1
        Supplier<int[][]> r1 = () -> sol.solution(new int[][] { new int[] { 0, 2, 2, 0, 0, 0, 0, 0 }, new int[] { 0, 2, 2, 0, 0, 4, 4, 0 }, new int[] { 0, 3, 3, 3, 1, 4, 4, 0 }, new int[] { 0, 3, 3, 3, 0, 0, 0, 0 }, new int[] { 0, 3, 3, 3, 5, 5, 6, 0 }, new int[] { 0, 0, 0, 0, 5, 5, 0, 0 } }, new int[][] { new int[] { 3, 1 }, new int[] { 3, 1 } });
        Test.check("r1", r1, new int[][] { new int[] { 0, 0, 2, 2, 0, 0, 0, 0 }, new int[] { 4, 4, 2, 2, 0, 0, 0, 0 }, new int[] { 4, 4, 0, 3, 3, 3, 1, 0 }, new int[] { 0, 0, 0, 3, 3, 3, 0, 0 }, new int[] { 6, 0, 0, 3, 3, 3, 5, 5 }, new int[] { 0, 0, 0, 0, 0, 0, 5, 5 } });

        // 공식 예제 2
        Supplier<int[][]> r2 = () -> sol.solution(new int[][] { new int[] { 0, 9, 1, 1, 6, 0, 0, 0 }, new int[] { 2, 2, 1, 1, 0, 0, 0, 0 }, new int[] { 2, 2, 3, 4, 4, 4, 0, 0 }, new int[] { 5, 0, 0, 4, 4, 4, 7, 0 }, new int[] { 0, 0, 0, 4, 4, 4, 8, 8 }, new int[] { 0, 0, 0, 0, 0, 0, 8, 8 } }, new int[][] { new int[] { 2, 1 }, new int[] { 3, 1 }, new int[] { 9, 2 }, new int[] { 4, 1 } });
        Test.check("r2", r2, new int[][] { new int[] { 8, 8, 0, 1, 1, 6, 0, 0 }, new int[] { 8, 8, 0, 1, 1, 0, 0, 0 }, new int[] { 4, 4, 4, 9, 3, 0, 0, 0 }, new int[] { 4, 4, 4, 7, 2, 2, 0, 0 }, new int[] { 4, 4, 4, 0, 2, 2, 0, 0 }, new int[] { 0, 5, 0, 0, 0, 0, 0, 0 } });

        // 공식 예제 3
        Supplier<int[][]> r3 = () -> sol.solution(new int[][] { new int[] { 1, 1, 0 }, new int[] { 1, 1, 0 } }, new int[][] { new int[] { 1, 4 }, new int[] { 1, 3 }, new int[] { 1, 2 } });
        Test.check("r3", r3, new int[][] { new int[] { 0, 1, 1 }, new int[] { 0, 1, 1 } });
    }
}
