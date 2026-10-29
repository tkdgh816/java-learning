package all.samsung.ex24_4_color_tree;

import helpers.Test;
import java.util.function.Supplier;

// S24-4 색깔 트리
// 문제: https://www.codetree.ai/ko/frequent-problems/samsung-sw/problems/color-tree/description
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
        String input1 = "15\n100 1 -1 1 3\n100 2 1 2 1\n100 3 2 3 2\n400\n100 4 1 1 3\n100 5 4 3 2\n400\n200 4 4\n100 6 4 5 2\n300 1\n300 5\n300 6\n400\n200 2 4\n400\n";
        Supplier<String> r1 = () -> normalize(sol.solution(input1));
        Test.check("r1", r1, "5 15 1 4 5 23 16");

        // 공식 예제 2
        String input2 = "18\n100 52137 -1 1 5\n100 12384 52137 2 5\n100 46812 -1 2 3\n100 47513 46812 2 3\n100 11111 12384 3 5\n100 12121 11111 4 5\n100 21212 12121 5 5\n400\n200 12121 4\n400\n200 21212 5\n400\n200 11111 5\n400\n200 46812 2\n400\n200 52137 5\n400\n";
        Supplier<String> r2 = () -> normalize(sol.solution(input2));
        Test.check("r2", r2, "57 33 57 18 18 7");
    }

    // 채점 사이트처럼 공백/개행 차이를 무시하고 토큰을 비교합니다.
    private static String normalize(String output) {
        return output == null ? "<null>" : output.trim().replaceAll("\\s+", " ");
    }
}
