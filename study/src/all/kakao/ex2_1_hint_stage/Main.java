package all.kakao.ex2_1_hint_stage;

import helpers.Test;
import java.util.function.Supplier;

// K2-1 힌트 스테이지
// 문제: https://school.programmers.co.kr/learn/courses/30/lessons/468377
// PDF 출처: https://tech.kakao.com/posts/814
class Solution {
    public int solution(int[][] cost, int[][] hint) {
        // TODO: 직접 구현하세요.
        return 0;
    }
}

public class Main {
    public static void main(String[] args) {
        Solution sol = new Solution();

        // 공식 예제 1
        Supplier<Integer> r1 = () -> sol.solution(new int[][] { new int[] { 160, 140, 120, 110, 60 }, new int[] { 290, 270, 260, 120, 10 }, new int[] { 160, 130, 120, 60, 20 }, new int[] { 160, 120, 80, 70, 20 }, new int[] { 110, 70, 60, 30, 20 } }, new int[][] { new int[] { 40, 2, 3 }, new int[] { 40, 5, 3 }, new int[] { 20, 5, 4 }, new int[] { 50, 5, 5 } });
        Test.check("r1", r1, 810);

        // 공식 예제 2
        Supplier<Integer> r2 = () -> sol.solution(new int[][] { new int[] { 110, 100, 90, 80, 70, 50, 10 }, new int[] { 170, 160, 150, 140, 130, 110, 30 }, new int[] { 260, 250, 190, 180, 170, 130, 100 }, new int[] { 170, 150, 120, 90, 60, 40, 30 }, new int[] { 220, 140, 110, 100, 70, 60, 50 }, new int[] { 290, 180, 150, 130, 100, 20, 10 }, new int[] { 110, 100, 90, 70, 50, 40, 30 } }, new int[][] { new int[] { 40, 3, 4, 3 }, new int[] { 80, 3, 7, 4 }, new int[] { 40, 7, 6, 5 }, new int[] { 40, 7, 5, 5 }, new int[] { 50, 6, 7, 6 }, new int[] { 30, 7, 7, 7 } });
        Test.check("r2", r2, 1080);

        // 공식 예제 3
        Supplier<Integer> r3 = () -> sol.solution(new int[][] { new int[] { 290, 270, 170, 100, 60, 20 }, new int[] { 210, 190, 180, 170, 120, 80 }, new int[] { 200, 180, 170, 80, 40, 20 }, new int[] { 100, 90, 70, 60, 40, 10 }, new int[] { 230, 200, 170, 150, 90, 80 }, new int[] { 150, 100, 80, 60, 40, 10 } }, new int[][] { new int[] { 230, 2, 2, 2, 2 }, new int[] { 330, 5, 3, 4, 3 }, new int[] { 180, 5, 4, 4, 6 }, new int[] { 280, 5, 5, 6, 6 }, new int[] { 260, 6, 6, 6, 6 } });
        Test.check("r3", r3, 1180);

        // 공식 예제 4
        Supplier<Integer> r4 = () -> sol.solution(new int[][] { new int[] { 49250, 42271, 40724, 36310, 32560, 30670, 24100, 10378 }, new int[] { 58510, 56101, 54078, 32864, 31443, 19451, 18098, 7187 }, new int[] { 68812, 66112, 65024, 60529, 53992, 39865, 31325, 17700 }, new int[] { 13768, 12866, 11379, 10425, 6853, 6176, 5655, 2556 }, new int[] { 51748, 48647, 41478, 39756, 25302, 18081, 16504, 811 }, new int[] { 52690, 34113, 32370, 29555, 19343, 11763, 7566, 5962 }, new int[] { 9306, 9190, 8196, 7573, 6275, 4723, 1316, 212 }, new int[] { 40713, 40158, 31449, 22349, 20956, 20377, 19489, 14450 } }, new int[][] { new int[] { 0, 2, 4, 6, 2 }, new int[] { 0, 6, 3, 3, 4 }, new int[] { 0, 6, 4, 5, 5 }, new int[] { 0, 7, 5, 8, 7 }, new int[] { 0, 7, 7, 7, 7 }, new int[] { 0, 7, 7, 7, 8 }, new int[] { 0, 8, 8, 8, 8 } });
        Test.check("r4", r4, 267789);

        // 공식 예제 5
        Supplier<Integer> r5 = () -> sol.solution(new int[][] { new int[] { 330, 300, 277, 162, 126 }, new int[] { 640, 392, 358, 345, 231 }, new int[] { 960, 872, 678, 392, 113 }, new int[] { 210, 195, 176, 42, 37 }, new int[] { 980, 964, 623, 327, 154 } }, new int[][] { new int[] { 976, 2, 2, 3, 5, 3, 3 }, new int[] { 746, 3, 3, 3, 3, 4, 3 }, new int[] { 319, 5, 4, 4, 4, 4, 4 }, new int[] { 966, 5, 5, 5, 5, 5, 5 } });
        Test.check("r5", r5, 3004);

        // 공식 예제 6
        Supplier<Integer> r6 = () -> sol.solution(new int[][] { new int[] { 230, 223, 192, 93, 59, 33 }, new int[] { 290, 240, 193, 117, 113, 42 }, new int[] { 950, 908, 796, 598, 574, 328 }, new int[] { 720, 692, 588, 237, 102, 61 }, new int[] { 780, 569, 522, 450, 337, 293 }, new int[] { 850, 837, 768, 568, 382, 124 } }, new int[][] { new int[] { 158, 2, 2, 3 }, new int[] { 260, 5, 4, 3 }, new int[] { 268, 4, 4, 4 }, new int[] { 426, 5, 5, 6 }, new int[] { 330, 6, 6, 6 } });
        Test.check("r6", r6, 3426);
    }
}
