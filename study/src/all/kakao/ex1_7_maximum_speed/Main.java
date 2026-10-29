package all.kakao.ex1_7_maximum_speed;

import helpers.Test;
import java.util.function.Supplier;

// K1-7 최고 속도
// 문제: https://school.programmers.co.kr/learn/courses/30/lessons/468376
// PDF 출처: https://tech.kakao.com/posts/813
class Solution {
    public int[] solution(int[][] city, int[][] road) {
        // TODO: 직접 구현하세요.
        return new int[0];
    }
}

public class Main {
    public static void main(String[] args) {
        Solution sol = new Solution();

        // 공식 예제 1
        Supplier<int[]> r1 = () -> sol.solution(new int[][] { new int[] { -1, 3 }, new int[] { 7, 3 }, new int[] { 1, -1 }, new int[] { -2, 6 } }, new int[][] { new int[] { -1, 7, 7, 7, 80 }, new int[] { -3, 3, 9, 3, 45 }, new int[] { -2, -4, -2, 6, 60 }, new int[] { 1, -4, 1, 8, 50 }, new int[] { 5, 1, 5, 7, 70 } });
        Test.check("r1", r1, new int[] { 70, 50, 0 });

        // 공식 예제 2
        Supplier<int[]> r2 = () -> sol.solution(new int[][] { new int[] { 3, 5 }, new int[] { 3, 3 }, new int[] { 2, 1 }, new int[] { 9, 1 }, new int[] { 7, -1 } }, new int[][] { new int[] { 3, -2, 3, 4, 30 }, new int[] { 5, 1, 9, 1, 29 }, new int[] { 3, 4, 3, 8, 99 }, new int[] { 1, 1, 5, 1, 99 }, new int[] { 7, -3, 7, 5, 99 } });
        Test.check("r2", r2, new int[] { 0, 30, 29, 29 });
    }
}
