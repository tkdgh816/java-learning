package all.kakao.ex2_3_hide_cactus;

import helpers.Test;
import java.util.function.Supplier;

// K2-3 선인장 숨기기
// 문제: https://school.programmers.co.kr/learn/courses/30/lessons/468379
// PDF 출처: https://tech.kakao.com/posts/814
class Solution {
    public int[] solution(int m, int n, int h, int w, int[][] drops) {
        // TODO: 직접 구현하세요.
        return new int[0];
    }
}

public class Main {
    public static void main(String[] args) {
        Solution sol = new Solution();

        // 공식 예제 1
        Supplier<int[]> r1 = () -> sol.solution(4, 5, 2, 2, new int[][] { new int[] { 0, 0 }, new int[] { 3, 1 }, new int[] { 1, 3 }, new int[] { 2, 4 }, new int[] { 1, 1 }, new int[] { 2, 2 }, new int[] { 2, 3 }, new int[] { 0, 4 } });
        Test.check("r1", r1, new int[] { 2, 2 });

        // 공식 예제 2
        Supplier<int[]> r2 = () -> sol.solution(3, 3, 1, 1, new int[][] { new int[] { 0, 0 }, new int[] { 0, 1 }, new int[] { 0, 2 }, new int[] { 1, 0 } });
        Test.check("r2", r2, new int[] { 1, 1 });

        // 공식 예제 3
        Supplier<int[]> r3 = () -> sol.solution(4, 6, 3, 4, new int[][] { new int[] { 1, 2 } });
        Test.check("r3", r3, new int[] { 0, 0 });

        // 공식 예제 4
        Supplier<int[]> r4 = () -> sol.solution(4, 6, 1, 2, new int[][] { new int[] { 0, 1 }, new int[] { 0, 3 }, new int[] { 0, 5 }, new int[] { 1, 1 }, new int[] { 1, 3 }, new int[] { 1, 5 }, new int[] { 2, 1 }, new int[] { 2, 3 }, new int[] { 2, 5 }, new int[] { 3, 1 }, new int[] { 3, 3 }, new int[] { 3, 5 } });
        Test.check("r4", r4, new int[] { 3, 4 });

        // 공식 예제 5
        Supplier<int[]> r5 = () -> sol.solution(2, 2, 2, 2, new int[][] { new int[] { 0, 0 }, new int[] { 0, 1 }, new int[] { 1, 1 }, new int[] { 1, 0 } });
        Test.check("r5", r5, new int[] { 0, 0 });

        // 공식 예제 6
        Supplier<int[]> r6 = () -> sol.solution(4, 4, 3, 1, new int[][] { new int[] { 2, 0 }, new int[] { 1, 3 }, new int[] { 3, 2 }, new int[] { 0, 1 } });
        Test.check("r6", r6, new int[] { 0, 2 });
    }
}
