package all.samsung.ex26_2_perfume_workshop;

import helpers.Test;
import java.util.function.Supplier;

// S26-2 코디의 향수 공방
// 문제: https://www.codetree.ai/ko/frequent-problems/samsung-sw/problems/perfume-workshop/description
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
        String input1 = "9\n1 3 2 3 5\n2 4\n4 7\n3 2\n3 2\n4 10\n4 3\n5 10\n3 5\n";
        Supplier<String> r1 = () -> normalize(sol.solution(input1));
        Test.check("r1", r1, "2 3 -1 2 -1 20 -1");

        // 공식 예제 2
        String input2 = "7\n1 2 1 4\n5 12\n2 3\n4 5\n3 1\n4 5\n5 10\n";
        Supplier<String> r2 = () -> normalize(sol.solution(input2));
        Test.check("r2", r2, "1 2 1 -1 7");
    }

    // 채점 사이트처럼 공백/개행 차이를 무시하고 토큰을 비교합니다.
    private static String normalize(String output) {
        return output == null ? "<null>" : output.trim().replaceAll("\\s+", " ");
    }
}
