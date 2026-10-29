package all.hyundai.ex2_check_digital_logic_pattern;

import helpers.Test;
import java.util.function.Supplier;

// H-2 디지털 로직 패턴 검사
// 문제: https://www.codetree.ai/ko/frequent-problems/hsat/problems/check-digital-logic-pattern/description
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
        String input1 = "011010010100\n3 3\n";
        Supplier<String> r1 = () -> normalize(sol.solution(input1));
        Test.check("r1", r1, "1");

        // 공식 예제 2
        String input2 = "011010010100\n3 4\n";
        Supplier<String> r2 = () -> normalize(sol.solution(input2));
        Test.check("r2", r2, "0");
    }

    // 채점 사이트처럼 공백/개행 차이를 무시하고 토큰을 비교합니다.
    private static String normalize(String output) {
        return output == null ? "<null>" : output.trim().replaceAll("\\s+", " ");
    }
}
