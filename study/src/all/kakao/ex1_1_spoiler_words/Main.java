package all.kakao.ex1_1_spoiler_words;

import helpers.Test;
import java.util.function.Supplier;

// K1-1 중요한 단어를 스포 방지
// 문제: https://school.programmers.co.kr/learn/courses/30/lessons/468370
// PDF 출처: https://tech.kakao.com/posts/813
class Solution {
    public int solution(String message, int[][] spoiler_ranges) {
        // TODO: 직접 구현하세요.
        return 0;
    }
}

public class Main {
    public static void main(String[] args) {
        Solution sol = new Solution();

        // 공식 예제 1
        Supplier<Integer> r1 = () -> sol.solution("here is muzi here is a secret message", new int[][] { new int[] { 0, 3 }, new int[] { 23, 28 } });
        Test.check("r1", r1, 1);

        // 공식 예제 2
        Supplier<Integer> r2 = () -> sol.solution("my phone number is 01012345678 and may i have your phone number", new int[][] { new int[] { 5, 5 }, new int[] { 25, 28 }, new int[] { 34, 40 }, new int[] { 53, 59 } });
        Test.check("r2", r2, 4);
    }
}
