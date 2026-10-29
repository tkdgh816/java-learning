package all.hyundai.ex3_optimizing_battery_efficiency;

import helpers.Test;
import java.util.function.Supplier;

// H-3 배터리 효율 최적화 하기
// 문제: https://www.codetree.ai/ko/frequent-problems/hsat/problems/optimizing-battery-efficiency/description
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
        String input1 = "2 4\n10 2 8 9\n15 3 10 2\n";
        Supplier<String> r1 = () -> normalize(sol.solution(input1));
        Test.check("r1", r1, "84");

        // 공식 예제 2
        String input2 = "3 4\n10 11 -10 9\n15 -10 10 2\n-10 22 31 10\n";
        Supplier<String> r2 = () -> normalize(sol.solution(input2));
        Test.check("r2", r2, "144");
    }

    // 채점 사이트처럼 공백/개행 차이를 무시하고 토큰을 비교합니다.
    private static String normalize(String output) {
        return output == null ? "<null>" : output.trim().replaceAll("\\s+", " ");
    }
}
