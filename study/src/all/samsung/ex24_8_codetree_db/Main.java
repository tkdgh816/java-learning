package all.samsung.ex24_8_codetree_db;

import helpers.Test;
import java.util.function.Supplier;

// S24-8 코드트리 DB
// 문제: https://www.codetree.ai/ko/frequent-problems/samsung-sw/problems/codetree-db/description
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
        String input1 = "10\ninit\ninsert Gimbab 3000\ndelete Kimbab\ninsert Udon 6000\ninit\ninsert Udon 7000\ninsert EelSushi 17000\ninsert Sushi 14000\nrank 3\nsum 15000\n";
        Supplier<String> r1 = () -> normalize(sol.solution(input1));
        Test.check("r1", r1, "1 0 1 1 1 1 EelSushi 21000");

        // 공식 예제 2
        String input2 = "32\ninit\ndelete hat\ndelete hop\ndelete dong\ninsert jack 2000\nsum 13000\ninit\ninsert hat 18000\nsum 1000\nrank 4\nsum 16000\ninsert lip 20000\ninsert bow 20000\ndelete map\nrank 3\nsum 19000\ninsert lucas 9000\nrank 4\nsum 4000\ninsert david 12000\nsum 19000\ndelete cat\nsum 11000\ninsert fly 5000\ninsert dong 16000\nsum 10000\ninsert will 5000\ninsert dong 13000\nsum 10000\nrank 3\nsum 17000\nrank 6\n";
        Supplier<String> r2 = () -> normalize(sol.solution(input2));
        Test.check("r2", r2, "0 0 0 1 2000 1 0 None 0 1 0 0 None 18000 1 None 0 1 39000 0 9000 1 1 14000 0 0 14000 david 42000 lip");
    }

    // 채점 사이트처럼 공백/개행 차이를 무시하고 토큰을 비교합니다.
    private static String normalize(String output) {
        return output == null ? "<null>" : output.trim().replaceAll("\\s+", " ");
    }
}
