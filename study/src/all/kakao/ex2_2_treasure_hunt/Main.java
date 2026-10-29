package all.kakao.ex2_2_treasure_hunt;

import java.util.function.Function;

import helpers.Test;
import java.util.function.Supplier;

// K2-2 보물 찾기
// 문제: https://school.programmers.co.kr/learn/courses/30/lessons/468378
// PDF 출처: https://tech.kakao.com/posts/814
class Solution {
    public int solution(int[] depth, int money, Function<Integer, Integer> excavate) {
        // TODO: 직접 구현하세요.
        return 0;
    }
}

public class Main {
    public static void main(String[] args) {
        // 공식 예제 1
        Supplier<Integer> r1 = () -> runCase(new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 }, 55, 3);
        Test.check("r1", r1, 3);

        // 공식 예제 2
        Supplier<Integer> r2 = () -> runCase(new int[] { 1, 1, 1, 1, 1 }, 3, 5);
        Test.check("r2", r2, 5);

        // 공식 예제 3
        Supplier<Integer> r3 = () -> runCase(new int[] { 2, 100, 1, 100, 3, 100, 1 }, 200, 6);
        Test.check("r3", r3, 6);

        // 공식 예제 4
        Supplier<Integer> r4 = () -> runCase(new int[] { 2, 100, 1, 100, 3, 100, 1 }, 200, 5);
        Test.check("r4", r4, 5);

        // 공식 예제 5
        Supplier<Integer> r5 = () -> runCase(new int[] { 3, 2, 1, 2, 3, 2, 1, 2 }, 8, 5);
        Test.check("r5", r5, 5);

        // 공식 예제 6
        Supplier<Integer> r6 = () -> runCase(new int[] { 1, 1000, 1, 1, 1, 10, 15, 1 }, 1002, 2);
        Test.check("r6", r6, 2);
    }

    // 고정 위치의 공식 예제용 로컬 채점기입니다.
    // 실제 채점에는 이전 응답과 모순되지 않도록 보물 위치가 바뀌는 경우도 있습니다.
    private static int runCase(int[] depth, int money, int treasure) {
        int[] original = depth.clone();
        long[] spent = {0};
        boolean[] found = {false};
        int result = new Solution().solution(depth.clone(), money, col -> {
            if (col < 1 || col > original.length)
                throw new IllegalArgumentException("굴착 열 범위 초과: " + col);
            spent[0] += original[col - 1];
            if (spent[0] > money)
                throw new IllegalStateException("굴착 예산 초과: " + spent[0]);
            if (col == treasure) found[0] = true;
            return Integer.compare(treasure, col);
        });
        if (!found[0]) throw new IllegalStateException("보물을 실제로 발굴하지 않았습니다.");
        return result;
    }
}
