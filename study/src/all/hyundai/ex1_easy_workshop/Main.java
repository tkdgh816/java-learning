package all.hyundai.ex1_easy_workshop;

import helpers.Test;
import java.util.function.Supplier;

// H-1 편안한 워크숍
// 문제: https://www.codetree.ai/ko/frequent-problems/hsat/problems/easy-workshop/description
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
        String input1 = "4 3\n6 7 8 1\n9 1 1 1 \n1 1 4 8\n1 1 1 9\n";
        Supplier<String> r1 = () -> normalize(sol.solution(input1));
        Test.check("r1", r1, "1");

        // 공식 예제 2
        String input2 = "5 4\n16 57 98 11 52\n49 61 71 31 39\n51 41 54 88 93\n71 21 31 41 20\n48 34 22 50 44\n";
        Supplier<String> r2 = () -> normalize(sol.solution(input2));
        Test.check("r2", r2, "10");
    }

    // 채점 사이트처럼 공백/개행 차이를 무시하고 토큰을 비교합니다.
    private static String normalize(String output) {
        return output == null ? "<null>" : output.trim().replaceAll("\\s+", " ");
    }
}
