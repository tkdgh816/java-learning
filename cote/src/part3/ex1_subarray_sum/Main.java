package part3.ex1_subarray_sum;

// 수업자료 24페이지: Subarray Sum Equals K
// https://leetcode.com/problems/subarray-sum-equals-k/

import java.util.HashMap;
import java.util.Map;

public class Main {
    static void main(String[] args) {
        Solution1 sol = new Solution1();

        var r1 = sol.subarraySum(new int[]{1, 1, 1}, 2);
        System.out.println("r1:" + r1); // 2

        var r2 = sol.subarraySum(new int[]{1, 2, 3}, 3);
        System.out.println("r2:" + r2); // 2

        var r3 = sol.subarraySum(new int[]{5}, 5);
        System.out.println("r3:" + r3); // 1

        var r4 = sol.subarraySum(new int[]{5}, 0);
        System.out.println("r4:" + r4); // 0

        var r5 = sol.subarraySum(new int[]{1, 2, 3}, 7);
        System.out.println("r5:" + r5); // 0

        var r6 = sol.subarraySum(new int[]{1, 2, 3}, 2);
        System.out.println("r6:" + r6); // 1

        var r7 = sol.subarraySum(new int[]{1, -1, 0}, 0);
        System.out.println("r7:" + r7); // 3

        var r8 = sol.subarraySum(new int[]{1, -1, 1, -1}, 0);
        System.out.println("r8:" + r8); // 4

        var r9 = sol.subarraySum(new int[]{-1, -1, 1}, 0);
        System.out.println("r9:" + r9); // 1

        // 5. k가 음수인 경우
        var r10 = sol.subarraySum(new int[]{-1, -1, 1}, -1);
        System.out.println("r10:" + r10); // 3

        var r11 = sol.subarraySum(new int[]{-1, -1, -1}, -2);
        System.out.println("r11:" + r11); // 2

        var r12 = sol.subarraySum(new int[]{-1, -2, -3}, -3);
        System.out.println("r12:" + r12); // 2

        // 6. 모든 원소가 0인 경우
        var r13 = sol.subarraySum(new int[]{0, 0, 0}, 0);
        System.out.println("r13:" + r13); // 6

        var r14 = sol.subarraySum(new int[]{0, 0, 0}, 1);
        System.out.println("r14:" + r14); // 0

        // 7. 동일한 누적 합이 여러 번 등장하는 경우
        var r15 = sol.subarraySum(new int[]{1, 2, 1, 2, 1}, 3);
        System.out.println("r15:" + r15); // 4

        var r16 = sol.subarraySum(new int[]{2, -2, 2, -2}, 0);
        System.out.println("r16:" + r16); // 4

        var r17 = sol.subarraySum(new int[]{1, -1, 0, 1, -1}, 0);
        System.out.println("r17:" + r17); // 7

        var r18 = sol.subarraySum(new int[]{3, 4, 7, 2, -3, 1, 4, 2}, 7);
        System.out.println("r18:" + r18); // 4

        var r19 = sol.subarraySum(new int[]{1, 2, 3, -3, 3}, 3);
        System.out.println("r19:" + r19); // 5

        var r20 = sol.subarraySum(new int[]{-2, -1, 2, 1}, 1);
        System.out.println("r20:" + r20); // 2

        var r21 = sol.subarraySum(new int[]{2, 2, 2}, 4);
        System.out.println("r21:" + r21); // 2

        var r22 = sol.subarraySum(new int[]{1, -1, 1}, 1);
        System.out.println("r22:" + r22); // 3

        var r23 = sol.subarraySum(new int[]{3, 1, 2, 1}, 3);
        System.out.println("r23:" + r23); // 3

        var r24 = sol.subarraySum(new int[]{1, 2, 3, 4, 5}, 9);
        System.out.println("r24:" + r24); // 2

        var r25 = sol.subarraySum(new int[]{1000, -1000, 1000, -1000}, 0);
        System.out.println("r25:" + r25); // 4
    }
}

// 시간 복잡도 O(n^2)
class Solution1 {
    public int subarraySum(int[] nums, int k) {
        int count = 0;

        // 누적합 구하기
        int[] prefix = new int[nums.length + 1];
        for (int i = 0; i < nums.length; i++) {
            prefix[i + 1] = prefix[i] + nums[i];
        }

        // 모든 누적합 쌍들에 대해서
        // 차가 k인 모든 쌍들의 개수 구하기
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j <= nums.length; j++) {
                int sum = prefix[j] - prefix[i];

                if (sum == k) {
                    count++;
                }
            }
        }

        return count;
    }
}

// 시간 복잡도 O(n)
class Solution2 {
    public int subarraySum(int[] nums, int k) {
        // 이전 누적합들
        Map<Integer, Integer> map = new HashMap<>();

        int prefixSum = 0;
        int count = 0;

        // 다음에서 계산할 순회의 현재 누적합 자체가
        // k가 되는 경우를 카운팅하기 위함
        map.put(0, 1);

        for (int num : nums) {
            // 현재 누적합 구함
            prefixSum += num;

            // 현재 누적합과의 차가 k인 이전 누적합들의 개수를 구함
            // 어떤 이전 인덱스로부터 현재 인덱스까지의 구간합이 k인 개수가 나옴
            count += map.getOrDefault(prefixSum - k, 0);

            // 현재 누적합을 추가함
            map.merge(prefixSum, 1, Integer::sum);
        }

        return count;
    }
}