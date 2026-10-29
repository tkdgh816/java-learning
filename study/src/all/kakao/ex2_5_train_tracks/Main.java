package all.kakao.ex2_5_train_tracks;

import helpers.Test;
import java.util.function.Supplier;

// K2-5 기차 선로
// 문제: https://school.programmers.co.kr/learn/courses/30/lessons/468381
// PDF 출처: https://tech.kakao.com/posts/814
class Solution {
    public int solution(int[][] grid) {
        // TODO: 직접 구현하세요.
        return 0;
    }
}

public class Main {
    public static void main(String[] args) {
        Solution sol = new Solution();

        // 공식 예제 1
        Supplier<Integer> r1 = () -> sol.solution(new int[][] { new int[] { 1, 0, -1 }, new int[] { 0, 0, 7 }, new int[] { 0, 0, 2 } });
        Test.check("r1", r1, 2);

        // 공식 예제 2
        Supplier<Integer> r2 = () -> sol.solution(new int[][] { new int[] { 1, 0, 0, 0, 0, -1, -1 }, new int[] { -1, 0, 0, 1, 0, 0, 1 } });
        Test.check("r2", r2, 2);

        // 공식 예제 3
        Supplier<Integer> r3 = () -> sol.solution(new int[][] { new int[] { 1, 0, 0, 0, 0 }, new int[] { 0, 0, 3, 0, 2 }, new int[] { 0, 0, 0, 0, 2 } });
        Test.check("r3", r3, 4);

        // 공식 예제 4
        Supplier<Integer> r4 = () -> sol.solution(new int[][] { new int[] { 1, 0, 0, 0 }, new int[] { 0, 0, 0, 0 }, new int[] { 0, 0, 0, 0 }, new int[] { 0, 0, 0, 0 }, new int[] { 0, 0, 0, 1 } });
        Test.check("r4", r4, 644);

        // 공식 예제 5
        Supplier<Integer> r5 = () -> sol.solution(new int[][] { new int[] { 1, 7 }, new int[] { 0, 2 } });
        Test.check("r5", r5, 1);

        // 공식 예제 6
        Supplier<Integer> r6 = () -> sol.solution(new int[][] { new int[] { 1, -1, 0, 0 }, new int[] { -1, 0, 0, 0 }, new int[] { 0, 0, 0, -1 }, new int[] { 0, 0, -1, 1 } });
        Test.check("r6", r6, 0);
    }
}
