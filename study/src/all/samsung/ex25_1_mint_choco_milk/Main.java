package all.samsung.ex25_1_mint_choco_milk;

import helpers.Test;
import java.util.function.Supplier;

// S25-1 민트 초코 우유
// 문제: https://www.codetree.ai/ko/frequent-problems/samsung-sw/problems/mint-choco-milk/description
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
        String input1 = "4 2\nTTCC\nTTTM\nCCMM\nCMMM\n1 3 3 3\n2 23 16 8\n12 6 7 8\n12 8 3 5\n";
        Supplier<String> r1 = () -> normalize(sol.solution(input1));
        Test.check("r1", r1, "33 0 0 14 17 31 7 35 9 0 1 10 21 4");
    }

    // 채점 사이트처럼 공백/개행 차이를 무시하고 토큰을 비교합니다.
    private static String normalize(String output) {
        return output == null ? "<null>" : output.trim().replaceAll("\\s+", " ");
    }
}
