package all.part1.op2_find_k_closest;

// https://leetcode.com/problems/find-k-closest-elements/description/

import helpers.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.IntStream;

class Solution {
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        int left = 0, right = arr.length - k - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            int cmp = Integer.compare(arr[mid + k] - x, x - arr[mid]);

            if (cmp < 0) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        List<Integer> list = new ArrayList<>(k);
        for (int i = left; i < left + k; i++) {
            list.add(arr[i]);
        }
        return list;
    }
}

public class Main {
    static void main(String[] args) {
        Solution sol = new Solution();

// 1. 기본 예제 1
        var r1 = sol.findClosestElements(new int[]{1, 2, 3, 4, 5}, 4, 3);
        Test.check("r1", r1, List.of(1, 2, 3, 4));

// 2. 기본 예제 2
        var r2 = sol.findClosestElements(new int[]{1, 1, 2, 3, 4, 5}, 4, -1);
        Test.check("r2", r2, List.of(1, 1, 2, 3));

// 3. 배열 길이가 1인 경우
        var r3 = sol.findClosestElements(new int[]{7}, 1, 7);
        Test.check("r3", r3, List.of(7));

// 4. 배열 길이가 1이고 x가 멀리 있는 경우
        var r4 = sol.findClosestElements(new int[]{-10000}, 1, 10000);
        Test.check("r4", r4, List.of(-10000));

// 5. k가 배열 길이와 같은 경우
        var r5 = sol.findClosestElements(new int[]{1, 3, 5, 7}, 4, 6);
        Test.check("r5", r5, List.of(1, 3, 5, 7));

// 6. x가 배열의 최솟값보다 작은 경우
        var r6 = sol.findClosestElements(new int[]{-4, -2, 0, 2}, 2, -10000);
        Test.check("r6", r6, List.of(-4, -2));

// 7. x가 배열의 최댓값보다 큰 경우
        var r7 = sol.findClosestElements(new int[]{-4, -2, 0, 2}, 2, 10000);
        Test.check("r7", r7, List.of(0, 2));

// 8. x가 배열의 중앙에 있는 경우
        var r8 = sol.findClosestElements(new int[]{1, 3, 5, 7, 9}, 3, 5);
        Test.check("r8", r8, List.of(3, 5, 7));

// 9. 거리 동률일 때 작은 값을 선택하는 경우
        var r9 = sol.findClosestElements(new int[]{1, 2, 3, 4, 5}, 2, 3);
        Test.check("r9", r9, List.of(2, 3));

// 10. 음수와 양수 사이에서 거리 동률이 발생하는 경우
        var r10 = sol.findClosestElements(new int[]{-5, -3, -1, 1, 3, 5}, 3, 0);
        Test.check("r10", r10, List.of(-3, -1, 1));

// 11. 모든 원소가 동일한 경우
        var r11 = sol.findClosestElements(new int[]{2, 2, 2, 2}, 3, 2);
        Test.check("r11", r11, List.of(2, 2, 2));

// 12. 중복 원소와 거리 동률이 함께 있는 경우
        var r12 = sol.findClosestElements(new int[]{1, 1, 2, 2, 3, 3}, 4, 2);
        Test.check("r12", r12, List.of(1, 1, 2, 2));

// 13. 원소 사이의 간격이 큰 경우
        var r13 = sol.findClosestElements(new int[]{-10, -5, 0, 100, 101, 102}, 3, 99);
        Test.check("r13", r13, List.of(100, 101, 102));

// 14. 음수와 양수가 대칭인 경우
        var r14 = sol.findClosestElements(new int[]{-10, -2, -1, 1, 2, 10}, 4, 0);
        Test.check("r14", r14, List.of(-2, -1, 1, 2));

// 15. k가 1이고 x와 일치하는 원소가 있는 경우
        var r15 = sol.findClosestElements(new int[]{1, 2, 3, 4, 5}, 1, 3);
        Test.check("r15", r15, List.of(3));

// 16. k가 1이고 두 원소의 거리가 같은 경우
        var r16 = sol.findClosestElements(new int[]{1, 9}, 1, 5);
        Test.check("r16", r16, List.of(1));

// 17. 원소의 간격이 일정하지 않은 경우
        var r17 = sol.findClosestElements(new int[]{1, 4, 7, 10, 13}, 2, 8);
        Test.check("r17", r17, List.of(7, 10));

// 18. 모든 원소가 음수인 경우
        var r18 = sol.findClosestElements(new int[]{-10, -9, -8, -7, -6}, 3, -8);
        Test.check("r18", r18, List.of(-9, -8, -7));

// 19. 결과에 포함할 마지막 원소의 거리가 같은 경우
        var r19 = sol.findClosestElements(new int[]{-10, -5, 0, 5, 10}, 4, 0);
        Test.check("r19", r19, List.of(-10, -5, 0, 5));

// 20. 최솟값 경계에 x가 있는 경우
        var r20 = sol.findClosestElements(new int[]{-10000, -9999, 0, 9999, 10000}, 2, -10000);
        Test.check("r20", r20, List.of(-10000, -9999));

// 21. 최댓값 경계에 x가 있는 경우
        var r21 = sol.findClosestElements(new int[]{-10000, 0, 10000}, 2, 10000);
        Test.check("r21", r21, List.of(0, 10000));

// 22. 최대 배열 길이, k = n
        int[] largeArr22 = IntStream.range(-5000, 5000).toArray();
        Supplier<List<Integer>> r22 = () -> sol.findClosestElements(largeArr22, 10000, 0);
        Test.mark("r22", r22, IntStream.range(-5000, 5000).boxed().toList());

// 23. 최대 배열 길이, k = n - 1
        int[] largeArr23 = IntStream.range(-5000, 5000).toArray();
        Supplier<List<Integer>> r23 = () -> sol.findClosestElements(largeArr23, 9999, 0);
        Test.mark("r23", r23, IntStream.range(-4999, 5000).boxed().toList());

// 24. 최대 배열 길이, k = n / 2
        int[] largeArr24 = IntStream.range(-5000, 5000).toArray();
        Supplier<List<Integer>> r24 = () -> sol.findClosestElements(largeArr24, 5000, 0);
        Test.mark("r24", r24, IntStream.range(-2500, 2500).boxed().toList());

// 25. 최대 배열 길이, k = 1
        int[] largeArr25 = IntStream.range(-5000, 5000).toArray();
        Supplier<List<Integer>> r25 = () -> sol.findClosestElements(largeArr25, 1, 10000);
        Test.mark("r25", r25, List.of(4999));

// 26. 최대 배열 길이, x가 모든 원소보다 작은 경우
        int[] largeArr26 = IntStream.range(-5000, 5000).toArray();
        Supplier<List<Integer>> r26 = () -> sol.findClosestElements(largeArr26, 5000, -10000);
        Test.mark("r26", r26, IntStream.range(-5000, 0).boxed().toList());

// 27. 최대 배열 길이, 모든 원소가 동일한 경우
        int[] largeArr27 = new int[10000];
        Arrays.fill(largeArr27, -10000);
        Supplier<List<Integer>> r27 = () -> sol.findClosestElements(largeArr27, 10000, 10000);
        Test.mark("r27", r27, Collections.nCopies(10000, -10000));

// 28. 최대 배열 길이, 모든 값이 2번씩 중복되는 경우
        int[] largeArr28 = IntStream.range(0, 10000).map(i -> i / 2 - 2500).toArray();
        Supplier<List<Integer>> r28 = () -> sol.findClosestElements(largeArr28, 5001, 0);
        Test.mark("r28", r28, IntStream.range(0, 5001).map(i -> i / 2 - 1250).boxed().toList());
    }
}
