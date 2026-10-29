package all.part2.op1_largest_number;

// https://school.programmers.co.kr/learn/courses/30/lessons/42746

import helpers.Test;

import java.util.*;
import java.util.function.Supplier;

class Solution {
    public String solution(int[] numbers) {
        StringBuilder sb = new StringBuilder();
        List<Number> list = Arrays.stream(numbers)
                .mapToObj(Number::new)
                .sorted()
                .toList();

        boolean zero = true;
        for(Number n : list){
            if(zero && n.number == 0){
                continue;
            }
            zero = false;
            sb.append(n.number);
        }

        String str = sb.toString();
        return str.isBlank() ? "0" : str;
    }

    private static class Number implements Comparable<Number> {
        int[] values = new int[4];
        int number;

        Number(int number) {
            this.number = number;
            Deque<Integer> stack = new ArrayDeque<>();

            while (number > 0) {
                stack.push(number % 10);
                number /= 10;
            }

            int i = 0;
            while (!stack.isEmpty()) {
                Arrays.fill(values, i++, 4, stack.pop());
            }
        }

        @Override
        public int compareTo(Number other) {
            for (int i = 0; i < 4; i++) {
                int cmp = Integer.compare(other.values[i], this.values[i]);
                if (cmp == 0) {
                    continue;
                }
                return cmp;
            }
            return Integer.compare(this.number, other.number);
        }
    }
}

class Solution2 {
    public String solution(int[] numbers) {
        String[] nums = Arrays.stream(numbers)
                .mapToObj(String::valueOf)
                .toArray(String[]::new);

        Arrays.sort(nums, (a, b) -> (b + a).compareTo(a + b));

        if (nums[0].equals("0")) {
            return "0";
        }

        return String.join("", nums);
    }
}

public class Main {
    static void main(String[] args) {
        Solution sol = new Solution();
//        Solution2 sol = new Solution2();

// 1. 기본 예제 1
        var r1 = sol.solution(new int[]{6, 10, 2});
        Test.check("r1", r1, "6210");

// 2. 기본 예제 2
        var r2 = sol.solution(new int[]{3, 30, 34, 5, 9});
        Test.check("r2", r2, "9534330");

// 3. 최소 입력 - 0 하나
        var r3 = sol.solution(new int[]{0});
        Test.check("r3", r3, "0");

// 4. 최대 원소 하나
        var r4 = sol.solution(new int[]{1000});
        Test.check("r4", r4, "1000");

// 5. 모든 원소가 0
        var r5 = sol.solution(new int[]{0, 0, 0, 0});
        Test.check("r5", r5, "0");

// 6. 0과 양수가 섞인 경우
        var r6 = sol.solution(new int[]{0, 0, 1, 0});
        Test.check("r6", r6, "1000");

// 7. 접두사가 같은 숫자들
        var r7 = sol.solution(new int[]{1, 10, 100, 1000});
        Test.check("r7", r7, "1101001000");

// 8. 숫자 크기와 연결 순서가 다른 경우
        var r8 = sol.solution(new int[]{12, 121});
        Test.check("r8", r8, "12121");

// 9. 동일 접두사에 여러 숫자가 존재
        var r9 = sol.solution(new int[]{12, 121, 120});
        Test.check("r9", r9, "12121120");

// 10. 길이가 다른 유사 숫자
        var r10 = sol.solution(new int[]{8, 83, 830});
        Test.check("r10", r10, "883830");

// 11. 긴 숫자가 반드시 먼저 오지는 않음
        var r11 = sol.solution(new int[]{34, 343});
        Test.check("r11", r11, "34343");

// 12. 동일 접두사의 정렬 순서
        var r12 = sol.solution(new int[]{40, 404, 4});
        Test.check("r12", r12, "440440");

// 13. 연결 순서 비교 결과가 동일한 숫자들
        var r13 = sol.solution(new int[]{9, 99, 999, 0});
        Test.check("r13", r13, "9999990");

// 14. 숫자 자체의 내림차순 정렬로는 틀리는 경우
        var r14 = sol.solution(new int[]{10, 2});
        Test.check("r14", r14, "210");

// 15. 짧은 접두사와 긴 접두사의 복합 비교
        var r15 = sol.solution(new int[]{3, 30, 303, 34});
        Test.check("r15", r15, "34330330");

// 16. 유사한 세 자리 수가 포함된 경우
        var r16 = sol.solution(new int[]{9, 90, 909, 91});
        Test.check("r16", r16, "99190990");

// 17. 0을 포함한 접두사 정렬
        var r17 = sol.solution(new int[]{2, 20, 200, 0});
        Test.check("r17", r17, "2202000");

// 18. 최대 원소와 0의 조합
        var r18 = sol.solution(new int[]{1000, 0, 0});
        Test.check("r18", r18, "100000");

// 19. 최대 입력 - 100,000개 모두 0
        int[] largeZeros = new int[100000];
        Supplier<String> r19 = () -> sol.solution(largeZeros);
        Test.mark("r19", r19, "0");

// 20. 최대 입력 - 100,000개 모두 1000
        int[] largeMax = new int[100000];
        Arrays.fill(largeMax, 1000);
        Supplier<String> r20 = () -> sol.solution(largeMax);
        Test.mark("r20", r20, "1000".repeat(100000));

// 21. 최대 입력 - 접두사가 같은 두 숫자의 교차 배열
        int[] largePrefix = new int[100000];
        for (int i = 0; i < largePrefix.length; i++) largePrefix[i] = i % 2 == 0 ? 12 : 121;
        Supplier<String> r21 = () -> sol.solution(largePrefix);
        Test.mark("r21", r21, "12".repeat(50000) + "121".repeat(50000));

// 22. 최대 입력 - 다양한 길이와 0이 섞인 반복 배열
        int[] pattern = {0, 90, 91, 9, 1000, 100, 10, 1};
        int[] largeMixed = new int[100000];
        for (int i = 0; i < largeMixed.length; i++) largeMixed[i] = pattern[i % pattern.length];
        Supplier<String> r22 = () -> sol.solution(largeMixed);
        Test.mark("r22", r22, "9".repeat(12500) + "91".repeat(12500) + "90".repeat(12500) + "1".repeat(12500) + "10".repeat(12500) + "100".repeat(12500) + "1000".repeat(12500) + "0".repeat(12500));

// 23. 최대 입력 - 연결 순서 비교 결과가 동일한 숫자들
        int[] equalPattern = {9, 99, 999};
        int[] largeEqual = new int[100000];
        for (int i = 0; i < largeEqual.length; i++) largeEqual[i] = equalPattern[i % equalPattern.length];
        Supplier<String> r23 = () -> sol.solution(largeEqual);
        Test.mark("r23", r23, "9".repeat(199999));

// 24. 최대 입력 - 90종류의 두 자리 숫자를 반복한 대규모 정렬
        int[] largeVariety = new int[100000];
        for (int i = 0; i < 99990; i++) largeVariety[i] = 10 + (i % 90);
        StringBuilder expectedLarge = new StringBuilder(200000);
        for (int n = 99; n >= 10; n--) expectedLarge.append(String.valueOf(n).repeat(1111));
        expectedLarge.append("0".repeat(10));
        Supplier<String> r24 = () -> sol.solution(largeVariety);
        Test.mark("r24", r24, expectedLarge.toString());

// 25. 10, 100, 1000 사이의 정렬 우선순위
        var r25 = sol.solution(new int[]{1000, 100, 10});
        Test.check("r25", r25, "101001000");

// 26. 동일한 숫자들의 반복 정렬
        var r26 = sol.solution(new int[]{1000, 100, 10, 1000, 100, 10});
        Test.check("r26", r26, "101010010010001000");

// 27. 마지막 자릿수 반복으로 잘못 정렬되는 경우
        var r27 = sol.solution(new int[]{31, 312});
        Test.check("r27", r27, "31312");

// 28. 확장한 자릿수가 같아지는 경우
        var r28 = sol.solution(new int[]{12, 122});
        Test.check("r28", r28, "12212");
    }
}
