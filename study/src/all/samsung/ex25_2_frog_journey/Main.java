package all.samsung.ex25_2_frog_journey;

import helpers.Test;
import java.util.function.Supplier;

// S25-2 개구리의 여행
// 문제: https://www.codetree.ai/ko/frequent-problems/samsung-sw/problems/frog-journey/description
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
        String input1 = "8\n.S..#.##\n##.S.##.\n##S#S##S\n..SS.S##\n.S#S.#S#\n..#S...#\n###....S\n#.S.SS#.\n5\n1 1 1 3\n4 1 4 5\n6 2 1 1\n7 4 8 8\n8 2 6 1\n";
        Supplier<String> r1 = () -> normalize(sol.solution(input1));
        Test.check("r1", r1, "5 15 25 -1 27");
    }

    // 채점 사이트처럼 공백/개행 차이를 무시하고 토큰을 비교합니다.
    private static String normalize(String output) {
        return output == null ? "<null>" : output.trim().replaceAll("\\s+", " ");
    }
}
