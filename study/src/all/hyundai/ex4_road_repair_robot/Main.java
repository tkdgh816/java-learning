package all.hyundai.ex4_road_repair_robot;

import helpers.Test;
import java.util.function.Supplier;

// H-4 도로 보수 로봇
// 문제: https://www.codetree.ai/ko/frequent-problems/hsat/problems/road-repair-robot/description
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
        String input1 = "3 1\n1 3 4\n";
        Supplier<String> r1 = () -> normalize(sol.solution(input1));
        Test.check("r1", r1, "4");

        // 공식 예제 2
        String input2 = "3 2\n1 3 4\n";
        Supplier<String> r2 = () -> normalize(sol.solution(input2));
        Test.check("r2", r2, "2");

        // 공식 예제 3
        String input3 = "7 3\n1 3 6 7 8 9 10\n";
        Supplier<String> r3 = () -> normalize(sol.solution(input3));
        Test.check("r3", r3, "3");
    }

    // 채점 사이트처럼 공백/개행 차이를 무시하고 토큰을 비교합니다.
    private static String normalize(String output) {
        return output == null ? "<null>" : output.trim().replaceAll("\\s+", " ");
    }
}
