package all.kakao.ex1_6_restore_power_circuit;

import helpers.Test;
import java.util.function.Supplier;

// K1-6 발전소 회로 복구
// 문제: https://school.programmers.co.kr/learn/courses/30/lessons/468375
// PDF 출처: https://tech.kakao.com/posts/813
class Solution {
    public int solution(int h, String[] grid, int[][] panels, int[][] seqs) {
        // TODO: 직접 구현하세요.
        return 0;
    }
}

public class Main {
    public static void main(String[] args) {
        Solution sol = new Solution();

        // 공식 예제 1
        Supplier<Integer> r1 = () -> sol.solution(3, new String[] { ".#.##..", ".#..##.", ".......", "##.###.", ".@.#...", "...#..." }, new int[][] { new int[] { 2, 3, 4 }, new int[] { 2, 5, 6 }, new int[] { 1, 1, 1 }, new int[] { 3, 6, 3 } }, new int[][] { new int[] { 3, 2 }, new int[] { 1, 2 }, new int[] { 4, 1 }, new int[] { 4, 3 } });
        Test.check("r1", r1, 36);

        // 공식 예제 2
        Supplier<Integer> r2 = () -> sol.solution(1, new String[] { "@......", ".######", ".......", "######.", ".......", ".####..", "....#.." }, new int[][] { new int[] { 1, 7, 4 }, new int[] { 1, 3, 5 }, new int[] { 1, 1, 3 } }, new int[][] { new int[] { 1, 3 }, new int[] { 3, 2 } });
        Test.check("r2", r2, 31);

        // 공식 예제 3
        Supplier<Integer> r3 = () -> sol.solution(4, new String[] { "........#", "........#", "@.......#", ".#.#....#", "........#", "#........", "#.#..####", "..#..####", ".....####" }, new int[][] { new int[] { 2, 9, 1 }, new int[] { 2, 1, 8 }, new int[] { 1, 1, 3 }, new int[] { 3, 3, 2 }, new int[] { 1, 2, 8 } }, new int[][] { new int[] { 1, 2 }, new int[] { 2, 3 }, new int[] { 3, 4 }, new int[] { 4, 5 } });
        Test.check("r3", r3, 47);
    }
}
