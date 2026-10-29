package all.samsung.ex24_5_medusa_and_warriors;

import helpers.Test;
import java.util.function.Supplier;

// S24-5 메두사와 전사들
// 문제: https://www.codetree.ai/ko/frequent-problems/samsung-sw/problems/medusa-and-warriors/description
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
        String input1 = "4 4\n1 3 3 3\n3 1 0 3 1 0 2 2\n0 0 0 0\n0 0 0 0\n0 0 1 1\n1 0 0 0\n";
        Supplier<String> r1 = () -> normalize(sol.solution(input1));
        Test.check("r1", r1, "4 2 2 0 2 0 1 1 1 0 0 0 0 0 0 0");

        // 공식 예제 2
        String input2 = "6 4\n3 1 1 2\n3 5 1 4 0 4 1 3\n0 0 0 0 1 0\n0 1 0 0 1 1\n1 1 0 0 0 0\n0 0 1 0 1 1\n0 0 0 0 0 0\n0 0 0 0 1 1\n";
        Supplier<String> r2 = () -> normalize(sol.solution(input2));
        Test.check("r2", r2, "3 2 0 2 2 1 0 2 0 0 2 0 0 2 0 1 1 1 0");
    }

    // 채점 사이트처럼 공백/개행 차이를 무시하고 토큰을 비교합니다.
    private static String normalize(String output) {
        return output == null ? "<null>" : output.trim().replaceAll("\\s+", " ");
    }
}
