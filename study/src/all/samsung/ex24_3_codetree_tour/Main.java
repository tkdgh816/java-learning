package all.samsung.ex24_3_codetree_tour;

import helpers.Test;
import java.util.function.Supplier;

// S24-3 코드트리 투어
// 문제: https://www.codetree.ai/ko/frequent-problems/samsung-sw/problems/codetree-tour/description
class Solution {
    // 표준 입력 전체를 받아 표준 출력 전체를 반환합니다.
    public String solution(String input) {
        // TODO: 직접 구현하세요.
        // input을 파싱하고 결과를 StringBuilder에 모아 반환하세요.
        return "";
    }
}

public class Main {
    public static void main(String[] args) {
        Solution sol = new Solution();

        // 공식 예제 1
        String input1 = "16\n100 6 7 0 1 3 1 2 2 2 2 1 0 3 5 1 3 3 5 4 1 2 1 3\n200 1 5 1\n200 2 5 2\n200 3 3 1\n400\n400\n200 4 3 3\n200 5 10 5\n300 3\n400\n400\n200 6 10 4\n500 5\n400\n400\n400\n";
        Supplier<String> r1 = () -> normalize(sol.solution(input1));
        Test.check("r1", r1, "1 2 -1 -1 5 6 -1");

        // 공식 예제 2
        String input2 = "16\n100 6 17 0 1 8 0 2 15 0 3 20 0 4 25 0 5 30 5 5 1 1 0 20 1 2 18 3 1 19 1 4 24 1 5 27 4 1 18 2 3 17 3 4 19 3 5 23 4 5 34 1 4 9\n200 12100 10000 5\n200 13213 9997 5\n200 19198 9996 4\n200 5353 9999 3\n200 3535 9862 3\n200 3333 9899 2\n300 1\n300 3535\n400\n400\n400\n500 5\n400\n400\n400\n";
        Supplier<String> r2 = () -> normalize(sol.solution(input2));
        Test.check("r2", r2, "5353 19198 12100 13213 3333 -1");
    }

    // 채점 사이트처럼 공백/개행 차이를 무시하고 토큰을 비교합니다.
    private static String normalize(String output) {
        return output == null ? "<null>" : output.trim().replaceAll("\\s+", " ");
    }
}
