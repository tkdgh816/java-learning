package all.samsung.ex24_2_magical_forest_exploration;

import helpers.Test;
import java.util.function.Supplier;

// S24-2 마법의 숲 탐색
// 문제: https://www.codetree.ai/ko/frequent-problems/samsung-sw/problems/magical-forest-exploration/description
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
        String input1 = "6 5 6\n2 3\n2 0\n4 2\n2 0\n2 0\n2 2\n";
        Supplier<String> r1 = () -> normalize(sol.solution(input1));
        Test.check("r1", r1, "29");

        // 공식 예제 2
        String input2 = "7 9 6\n4 1\n5 1\n2 1\n8 1\n2 2\n6 0\n";
        Supplier<String> r2 = () -> normalize(sol.solution(input2));
        Test.check("r2", r2, "37");
    }

    // 채점 사이트처럼 공백/개행 차이를 무시하고 토큰을 비교합니다.
    private static String normalize(String output) {
        return output == null ? "<null>" : output.trim().replaceAll("\\s+", " ");
    }
}
