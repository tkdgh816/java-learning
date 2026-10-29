package all.part5.op1_koko_eating_bananas;

// https://leetcode.com/problems/koko-eating-bananas/description/

import helpers.Test;

import java.util.Arrays;
import java.util.function.Supplier;

class Solution {
    public int minEatingSpeed(int[] piles, int h) {
        int left = 1;
        int right = Arrays.stream(piles).max().orElseThrow();

        int lowerBound = -1;
        while (left <= right) {
            int mid = left + (right - left) / 2;

            long elapsed = getElapsed(piles, mid);

            if (elapsed > h) {
                left = mid + 1;
            } else {
                lowerBound = mid;
                right = mid - 1;
            }
        }

        return lowerBound;
    }

    private long getElapsed(int[] piles, int k) {
        long sum = 0;
        for (int pile : piles) {
            sum += Math.ceilDiv(pile, k);
        }
        return sum;
    }
}

public class Main {
    static void main(String[] args) {
        Solution sol = new Solution();

        var r0 = sol.minEatingSpeed(new int[]{805306368, 805306368, 805306368}, 1000000000);
        Test.check("r0", r0, 3);

        // 1. 기본 예제 1
        var r1 = sol.minEatingSpeed(new int[]{3, 6, 7, 11}, 8);
        Test.check("r1", r1, 4);

// 2. 기본 예제 2
        var r2 = sol.minEatingSpeed(new int[]{30, 11, 23, 4, 20}, 5);
        Test.check("r2", r2, 30);

// 3. 기본 예제 3
        var r3 = sol.minEatingSpeed(new int[]{30, 11, 23, 4, 20}, 6);
        Test.check("r3", r3, 23);

// 4. 최소 입력 크기
        var r4 = sol.minEatingSpeed(new int[]{1}, 1);
        Test.check("r4", r4, 1);

// 5. 바나나 개수 최댓값, 시간 최솟값
        var r5 = sol.minEatingSpeed(new int[]{1_000_000_000}, 1);
        Test.check("r5", r5, 1_000_000_000);

// 6. 나누어떨어지지 않는 경우의 올림 처리
        var r6 = sol.minEatingSpeed(new int[]{1_000_000_000}, 3);
        Test.check("r6", r6, 333_333_334);

// 7. 주어진 시간이 최대인 경우
        var r7 = sol.minEatingSpeed(new int[]{1_000_000_000}, 1_000_000_000);
        Test.check("r7", r7, 1);

// 8. h가 piles.length와 같은 경우
        var r8 = sol.minEatingSpeed(new int[]{1, 2, 3, 4}, 4);
        Test.check("r8", r8, 4);

// 9. h가 piles.length보다 1 큰 경우
        var r9 = sol.minEatingSpeed(new int[]{1, 2, 3, 4}, 5);
        Test.check("r9", r9, 3);

// 10. 시간이 증가하면서 최소 속도가 변경되는 경우
        var r10 = sol.minEatingSpeed(new int[]{1, 2, 3, 4}, 6);
        Test.check("r10", r10, 2);

// 11. 최소 속도가 1인 경우
        var r11 = sol.minEatingSpeed(new int[]{1, 2, 3, 4}, 10);
        Test.check("r11", r11, 1);

// 12. 같은 크기의 더미, 시간이 부족한 경우
        var r12 = sol.minEatingSpeed(new int[]{4, 4, 4, 4}, 7);
        Test.check("r12", r12, 4);

// 13. 같은 크기의 더미, 시간이 충분한 경우
        var r13 = sol.minEatingSpeed(new int[]{4, 4, 4, 4}, 8);
        Test.check("r13", r13, 2);

// 14. 작은 더미와 큰 더미가 섞인 경우
        var r14 = sol.minEatingSpeed(new int[]{1, 1, 100}, 5);
        Test.check("r14", r14, 34);

// 15. 시간의 여유가 있지만 최소 속도는 1이 아닌 경우
        var r15 = sol.minEatingSpeed(new int[]{1, 1, 100}, 100);
        Test.check("r15", r15, 2);

// 16. 큰 값이 서로 1 차이 나는 경우
        var r16 = sol.minEatingSpeed(new int[]{999_999_999, 1_000_000_000}, 3);
        Test.check("r16", r16, 999_999_999);

// 17. 큰 값에서 시간 경계가 변경되는 경우
        var r17 = sol.minEatingSpeed(new int[]{999_999_999, 1_000_000_000}, 4);
        Test.check("r17", r17, 500_000_000);

// 18. 시간 합산 시 int 오버플로 가능
        var r18 = sol.minEatingSpeed(new int[]{1_000_000_000, 1_000_000_000, 1_000_000_000}, 1_000_000_000);
        Test.check("r18", r18, 4);

// 19. 바나나 총합이 h를 1 초과하는 경우
        var r19 = sol.minEatingSpeed(new int[]{1, 1_000_000_000}, 1_000_000_000);
        Test.check("r19", r19, 2);

// 20. 모든 더미의 크기가 같고 올림 연산이 필요한 경우
        var r20 = sol.minEatingSpeed(new int[]{8, 8, 8, 8, 8}, 11);
        Test.check("r20", r20, 4);

// 21. 이분 탐색의 정답이 중간에 존재하는 경우
        var r21 = sol.minEatingSpeed(new int[]{9, 9, 9}, 8);
        Test.check("r21", r21, 5);

// 22. 최대 배열 길이, 모든 원소 최댓값, h 최솟값
        int[] maxPiles = new int[10_000];
        Arrays.fill(maxPiles, 1_000_000_000);
        Supplier<Integer> r22 = () -> sol.minEatingSpeed(maxPiles, 10_000);
        Test.check("r22", r22, 1_000_000_000);

// 23. 최대 배열 길이, h가 piles.length보다 1 큰 경우
        Supplier<Integer> r23 = () -> sol.minEatingSpeed(maxPiles, 10_001);
        Test.check("r23", r23, 1_000_000_000);

// 24. 최대 배열 길이, 모든 더미에 정확히 2시간 사용 가능
        Supplier<Integer> r24 = () -> sol.minEatingSpeed(maxPiles, 20_000);
        Test.check("r24", r24, 500_000_000);

// 25. 최대 배열 길이, 최대 h, 큰 누적 시간 계산
        Supplier<Integer> r25 = () -> sol.minEatingSpeed(maxPiles, 1_000_000_000);
        Test.check("r25", r25, 10_000);

// 26. 최대 배열 길이, 모든 원소 최솟값
        int[] minPiles = new int[10_000];
        Arrays.fill(minPiles, 1);
        Supplier<Integer> r26 = () -> sol.minEatingSpeed(minPiles, 1_000_000_000);
        Test.check("r26", r26, 1);

// 27. 최대 배열 길이, 오름차순, h 최솟값
        int[] ascendingPiles = new int[10_000];
        for (int i = 0; i < ascendingPiles.length; i++) ascendingPiles[i] = i + 1;
        Supplier<Integer> r27 = () -> sol.minEatingSpeed(ascendingPiles, 10_000);
        Test.check("r27", r27, 10_000);

// 28. 최대 배열 길이, 오름차순, 중간 시간 범위
        Supplier<Integer> r28 = () -> sol.minEatingSpeed(ascendingPiles, 20_000);
        Test.check("r28", r28, 3_334);

// 29. 최대 배열 길이, 최소/최대 원소가 번갈아 등장
        int[] alternatingPiles = new int[10_000];
        for (int i = 0; i < alternatingPiles.length; i++) alternatingPiles[i] = i % 2 == 0 ? 1 : 1_000_000_000;
        Supplier<Integer> r29 = () -> sol.minEatingSpeed(alternatingPiles, 15_000);
        Test.check("r29", r29, 500_000_000);

// 30. 최대 배열 길이, 최소/최대 원소 교대, 최대 h
        Supplier<Integer> r30 = () -> sol.minEatingSpeed(alternatingPiles, 1_000_000_000);
        Test.check("r30", r30, 5_001);
    }
}
