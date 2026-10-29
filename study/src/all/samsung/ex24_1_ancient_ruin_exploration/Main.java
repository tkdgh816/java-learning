package all.samsung.ex24_1_ancient_ruin_exploration;

import helpers.Test;
import java.util.function.Supplier;

// S24-1 고대 문명 유적 탐사
// 문제: https://www.codetree.ai/ko/frequent-problems/samsung-sw/problems/ancient-ruin-exploration/description
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
        String input1 = "2 20\n7 6 7 6 7\n6 7 6 7 6\n6 7 1 5 4\n7 6 3 2 1\n5 4 3 2 7\n3 2 3 5 2 4 6 1 3 2 5 6 2 1 5 6 7 1 2 3\n";
        Supplier<String> r1 = () -> normalize(sol.solution(input1));
        Test.check("r1", r1, "17");

        // 공식 예제 2
        String input2 = "2 30\n7 6 7 6 7\n6 7 6 7 6\n6 7 1 5 4\n7 6 3 2 1\n5 4 3 2 7\n3 2 3 5 2 4 6 1 3 2 5 6 2 1 6 6 7 5 4 2 2 3 1 3 1 2 4 2 1 3\n";
        Supplier<String> r2 = () -> normalize(sol.solution(input2));
        Test.check("r2", r2, "17 3");
    }

    // 채점 사이트처럼 공백/개행 차이를 무시하고 토큰을 비교합니다.
    private static String normalize(String output) {
        return output == null ? "<null>" : output.trim().replaceAll("\\s+", " ");
    }
}
