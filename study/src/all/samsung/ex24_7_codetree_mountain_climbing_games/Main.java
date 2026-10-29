package all.samsung.ex24_7_codetree_mountain_climbing_games;

import helpers.Test;
import java.util.function.Supplier;

// S24-7 코드트리 등산 게임
// 문제: https://www.codetree.ai/ko/frequent-problems/samsung-sw/problems/codetree-mountain-climbing-games/description
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
        String input1 = "4\n100 4 1 3 2 4\n200 3\n300\n400 2\n";
        Supplier<String> r1 = () -> normalize(sol.solution(input1));
        Test.check("r1", r1, "4000004");

        // 공식 예제 2
        String input2 = "4\n100 4 1 3 2 4\n200 3\n300\n400 1\n";
        Supplier<String> r2 = () -> normalize(sol.solution(input2));
        Test.check("r2", r2, "3000004");

        // 공식 예제 3
        String input3 = "15\n100 5 3 2 5 4 3\n400 4\n400 3\n400 5\n300\n300\n200 7\n200 8\n400 4\n200 5\n200 3\n200 1\n200 10\n200 8\n400 10\n";
        Supplier<String> r3 = () -> normalize(sol.solution(input3));
        Test.check("r3", r3, "3000005 3000005 3000005 6000008 8000010");
    }

    // 채점 사이트처럼 공백/개행 차이를 무시하고 토큰을 비교합니다.
    private static String normalize(String output) {
        return output == null ? "<null>" : output.trim().replaceAll("\\s+", " ");
    }
}
