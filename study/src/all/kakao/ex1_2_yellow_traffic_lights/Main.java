package all.kakao.ex1_2_yellow_traffic_lights;

import helpers.Test;
import java.util.function.Supplier;

// K1-2 노란불 신호등
// 문제: https://school.programmers.co.kr/learn/courses/30/lessons/468371
// PDF 출처: https://tech.kakao.com/posts/813
class Solution {
    public int solution(int[][] signals) {
        // TODO: 직접 구현하세요.
        return 0;
    }
}

public class Main {
    public static void main(String[] args) {
        Solution sol = new Solution();

        // 공식 예제 1
        Supplier<Integer> r1 = () -> sol.solution(new int[][] { new int[] { 2, 1, 2 }, new int[] { 5, 1, 1 } });
        Test.check("r1", r1, 13);

        // 공식 예제 2
        Supplier<Integer> r2 = () -> sol.solution(new int[][] { new int[] { 2, 3, 2 }, new int[] { 3, 1, 3 }, new int[] { 2, 1, 1 } });
        Test.check("r2", r2, 11);

        // 공식 예제 3
        Supplier<Integer> r3 = () -> sol.solution(new int[][] { new int[] { 3, 3, 3 }, new int[] { 5, 4, 2 }, new int[] { 2, 1, 2 } });
        Test.check("r3", r3, 193);

        // 공식 예제 4
        Supplier<Integer> r4 = () -> sol.solution(new int[][] { new int[] { 1, 1, 4 }, new int[] { 2, 1, 3 }, new int[] { 3, 1, 2 }, new int[] { 4, 1, 1 } });
        Test.check("r4", r4, -1);
    }
}
