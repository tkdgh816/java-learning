package week1_1017.rank;

// https://leetcode.com/problems/rank-transform-of-an-array/

import helpers.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.function.Supplier;
import java.util.stream.IntStream;

class Solution {
    public int[] arrayRankTransform(int[] arr) {
        int length = arr.length;
        if (length == 0) {
            return new int[0];
        }

        // arr: [30, 10, 20]
        // elements: [(1, 30), (2, 10), (3, 20)]
        // elements(sorted): [(2, 10), (3, 20), (1, 30)]
        // ranks: [3, 1, 2]

        Element[] elements = new Element[length];
        for (int i = 0; i < length; i++) {
            elements[i] = new Element(i, arr[i]);
        }
        Arrays.sort(elements, Comparator.comparingInt(Element::value));

        int[] ranks = new int[length];
        int rank = 1;
        int current = elements[0].value;
        ranks[elements[0].index] = rank;

        for (int i = 1; i < length; i++) {
            if (current != elements[i].value) {
                current = elements[i].value;
                rank++;
            }
            ranks[elements[i].index] = rank;
        }

        return ranks;
    }

    record Element(int index, int value) {

    }
}

public class Main {
    static void main(String[] args) {
        Solution sol = new Solution();

// 1. 공식 예제 1
        var r1 = sol.arrayRankTransform(new int[]{40, 10, 20, 30});
        Test.check("r1", r1, new int[]{4, 1, 2, 3});

// 2. 공식 예제 2: 모든 값이 동일
        var r2 = sol.arrayRankTransform(new int[]{100, 100, 100});
        Test.check("r2", r2, new int[]{1, 1, 1});

// 3. 공식 예제 3: 중복값과 불규칙한 순서
        var r3 = sol.arrayRankTransform(
                new int[]{37, 12, 28, 9, 100, 56, 80, 5, 12}
        );
        Test.check("r3", r3, new int[]{5, 3, 4, 2, 8, 6, 7, 1, 3});

// 4. 빈 배열
        var r4 = sol.arrayRankTransform(new int[]{});
        Test.check("r4", r4, new int[]{});

// 5. 원소가 하나인 배열
        var r5 = sol.arrayRankTransform(new int[]{42});
        Test.check("r5", r5, new int[]{1});

// 6. 음수로만 구성된 배열
        var r6 = sol.arrayRankTransform(new int[]{-10, -20, -30});
        Test.check("r6", r6, new int[]{3, 2, 1});

// 7. 음수, 0, 양수 및 중복값이 섞인 경우
        var r7 = sol.arrayRankTransform(new int[]{-5, 0, 5, -5, 0, 5});
        Test.check("r7", r7, new int[]{1, 2, 3, 1, 2, 3});

// 8. 여러 중복 그룹이 존재하는 경우
        var r8 = sol.arrayRankTransform(new int[]{5, 5, 1, 1, 3});
        Test.check("r8", r8, new int[]{3, 3, 1, 1, 2});

// 9. 이미 오름차순으로 정렬된 배열
        var r9 = sol.arrayRankTransform(new int[]{1, 2, 3, 4, 5});
        Test.check("r9", r9, new int[]{1, 2, 3, 4, 5});

// 10. 내림차순으로 정렬된 배열
        var r10 = sol.arrayRankTransform(new int[]{5, 4, 3, 2, 1});
        Test.check("r10", r10, new int[]{5, 4, 3, 2, 1});

// 11. 최댓값, 최솟값, 0이 존재하는 경우
        var r11 = sol.arrayRankTransform(
                new int[]{1000000000, -1000000000, 0}
        );
        Test.check("r11", r11, new int[]{3, 1, 2});

// 12. 숫자 정렬과 문자열 정렬의 차이를 검증
        var r12 = sol.arrayRankTransform(new int[]{2, 10, 2, 10, 1});
        Test.check("r12", r12, new int[]{2, 3, 2, 3, 1});

// 13. -1, 0, 1의 반복
        var r13 = sol.arrayRankTransform(new int[]{0, -1, 1, 0, -1, 1});
        Test.check("r13", r13, new int[]{2, 1, 3, 2, 1, 3});

// 14. 중복값이 연속적으로 배치된 경우
        var r14 = sol.arrayRankTransform(new int[]{2, 2, 2, 1, 1, 3, 3, 3});
        Test.check("r14", r14, new int[]{2, 2, 2, 1, 1, 3, 3, 3});

// 15. 자릿수가 다른 음수의 정렬
        var r15 = sol.arrayRankTransform(new int[]{-100, -2, -10, -2, -100});
        Test.check("r15", r15, new int[]{1, 3, 2, 3, 1});

// 16. 서로 인접한 큰 정수들의 순위
        var r16 = sol.arrayRankTransform(
                new int[]{1000000000, 999999999, -1000000000, -999999999}
        );
        Test.check("r16", r16, new int[]{4, 3, 1, 2});

// 17. 자릿수가 다른 수와 반복값이 혼합된 경우
        var r17 = sol.arrayRankTransform(
                new int[]{100, 10, 1000, 10, 1000, 100}
        );
        Test.check("r17", r17, new int[]{2, 1, 3, 1, 3, 2});

// 18. 중복 횟수가 많고 값의 종류가 적은 경우
        var r18 = sol.arrayRankTransform(new int[]{9, 9, 9, 7, 7, 7, 9});
        Test.check("r18", r18, new int[]{2, 2, 2, 1, 1, 1, 2});

// 19. 최대 길이 100000, 고유값 2개 반복
        Supplier<int[]> r19 = () -> sol.arrayRankTransform(
                IntStream.range(0, 100000)
                        .map(i -> i % 2 == 0 ? -10 : 10)
                        .toArray()
        );
        Test.mark(
                "r19",
                r19,
                IntStream.range(0, 100000)
                        .map(i -> i % 2 == 0 ? 1 : 2)
                        .toArray()
        );

// 20. 최대 길이 100000, 모든 값이 서로 다름
        Supplier<int[]> r20 = () -> sol.arrayRankTransform(
                IntStream.range(0, 100000)
                        .map(i -> 100000 - i)
                        .toArray()
        );
        Test.mark(
                "r20",
                r20,
                IntStream.range(0, 100000)
                        .map(i -> 100000 - i)
                        .toArray()
        );
    }
}