package part5.ex2_immigration;

// 수업자료 17페이지: 입국심사
// https://school.programmers.co.kr/learn/courses/30/lessons/43238

import java.util.Arrays;

public class Main {
    static void main(String[] args) {
        Solution sol = new Solution();

        var r1 = sol.solution(6, new int[]{7, 10});
        System.out.println(r1); // 기대값: 28

        var r2 = sol.solution(1, new int[]{5});
        System.out.println(r2); // 기대값: 5

        var r3 = sol.solution(10, new int[]{1});
        System.out.println(r3); // 기대값: 10

        var r4 = sol.solution(5, new int[]{2, 2});
        System.out.println(r4); // 기대값: 6

        var r5 = sol.solution(4, new int[]{1, 2});
        System.out.println(r5); // 기대값: 3

        var r6 = sol.solution(8, new int[]{3, 6, 9});
        System.out.println(r6); // 기대값: 15

        var r7 = sol.solution(7, new int[]{2, 3, 4});
        System.out.println(r7); // 기대값: 8

        var r8 = sol.solution(20, new int[]{7, 10, 15});
        System.out.println(r8); // 기대값: 70

        var r9 = sol.solution(100, new int[]{1, 1000000000});
        System.out.println(r9); // 기대값: 100

        var r10 = sol.solution(1000000000, new int[]{1000000000, 1000000000});
        System.out.println(r10); // 기대값: 500000000000000000
    }
}

class Solution {
    public long solution(int n, int[] times) {
        int max = Arrays.stream(times).max().orElseThrow();
        long start = 0L;
        long end = (long) max * n;

        while (start <= end) {
            long mid = start + (end - start) / 2;

            // 특정 시간에 처리할 수 있는 최대 수용 인원은
            // 특정 시간에 각 심사관이 최대로 처리할 수 있는 인원의 합
            long capacity = Arrays.stream(times)
                    .mapToLong(time -> mid / time)
                    .sum();

            // 최대 수용 인원이 모든 인원수보다 작다면 시간을 늘림
            // 크거나 같다면 하나의 최소 시간을 찾을 때까지 시간을 줄임
            if (capacity < n) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }

        return start;
    }
}