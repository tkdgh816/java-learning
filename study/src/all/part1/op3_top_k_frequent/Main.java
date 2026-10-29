package all.part1.op3_top_k_frequent;

// https://leetcode.com/problems/top-k-frequent-elements/description/

import helpers.Test;

import java.util.*;
import java.util.function.Supplier;
import java.util.stream.IntStream;

class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.merge(num, 1, Integer::sum);
        }

        return map.entrySet().stream()
                .sorted((e1, e2) -> Integer.compare(e2.getValue(), e1.getValue()))
                .limit(k)
                .mapToInt(Map.Entry::getKey)
                .toArray();
    }
}

public class Main {
    static void main(String[] args) {
        Solution sol = new Solution();

        // 1. 기본 예제 1
        var r1 = sorted(sol.topKFrequent(new int[]{1, 1, 1, 2, 2, 3}, 2));
        Test.check("r1", r1, new int[]{1, 2});

        // 2. 기본 예제 2 - 원소가 하나
        var r2 = sorted(sol.topKFrequent(new int[]{1}, 1));
        Test.check("r2", r2, new int[]{1});

        // 3. 기본 예제 3 - 빈도가 같은 최상위 원소
        var r3 = sorted(sol.topKFrequent(new int[]{1, 2, 1, 2, 1, 2, 3, 1, 3, 2}, 2));
        Test.check("r3", r3, new int[]{1, 2});

        // 4. 음수와 양수가 섞인 경우
        var r4 = sorted(sol.topKFrequent(new int[]{-1, -1, -1, -1, 2, 2, 2, -3, -3, 0}, 2));
        Test.check("r4", r4, new int[]{-1, 2});

        // 5. 원소의 최솟값과 최댓값
        var r5 = sorted(sol.topKFrequent(new int[]{-10000, -10000, -10000, 10000, 10000, 0}, 2));
        Test.check("r5", r5, new int[]{-10000, 10000});

        // 6. 모든 원소가 서로 다르고 k가 고유 원소 개수와 같은 경우
        var r6 = sorted(sol.topKFrequent(new int[]{-3, 0, 4, 9}, 4));
        Test.check("r6", r6, new int[]{-3, 0, 4, 9});

        // 7. k = 1이고 차순위 원소들의 빈도가 같은 경우
        var r7 = sorted(sol.topKFrequent(new int[]{4, 4, 4, 2, 2, 3, 3}, 1));
        Test.check("r7", r7, new int[]{4});

        // 8. 상위 원소끼리 빈도가 같은 경우
        var r8 = sorted(sol.topKFrequent(new int[]{5, 5, 5, 7, 7, 7, 1, 1, 2, 2, 9}, 2));
        Test.check("r8", r8, new int[]{5, 7});

        // 9. 빈도가 같은 상위 원소 3개를 모두 선택
        var r9 = sorted(sol.topKFrequent(new int[]{0, 0, 0, -1, -1, -1, 1, 1, 1, 2, 2, -2}, 3));
        Test.check("r9", r9, new int[]{-1, 0, 1});

        // 10. 모든 고유 원소를 반환하며 빈도에 동률이 있는 경우
        var r10 = sorted(sol.topKFrequent(new int[]{8, 8, 8, 7, 7, 7, 6, 6, 5}, 4));
        Test.check("r10", r10, new int[]{5, 6, 7, 8});

        // 11. 최대 입력 크기 - 모든 원소가 동일
        int[] nums11 = new int[100_000];
        Arrays.fill(nums11, 7);
        Supplier<int[]> r11 = () -> sorted(sol.topKFrequent(nums11, 1));
        Test.check("r11", r11, new int[]{7});

        // 12. 최대 입력 크기 - 두 원소의 빈도가 근소하게 차이
        int[] nums12 = new int[100_000];
        Arrays.fill(nums12, 0, 50_001, -10000);
        Arrays.fill(nums12, 50_001, 100_000, 10000);
        Supplier<int[]> r12 = () -> sorted(sol.topKFrequent(nums12, 1));
        Test.check("r12", r12, new int[]{-10000});

        // 13. 최대 입력 크기 - 고유 원소 20001개, k = 1
        int[] nums13 = new int[100_000];
        for (int i = 0; i < nums13.length; i++) nums13[i] = i < 20_001 ? i - 10_000 : 0;
        Supplier<int[]> r13 = () -> sorted(sol.topKFrequent(nums13, 1));
        Test.check("r13", r13, new int[]{0});

        // 14. 최대 입력 크기 - 고유 원소 20001개, k = 20001
        int[] nums14 = new int[100_000];
        for (int i = 0; i < nums14.length; i++) nums14[i] = (i % 20_001) - 10_000;
        Supplier<int[]> r14 = () -> sorted(sol.topKFrequent(nums14, 20_001));
        Test.mark("r14", r14, IntStream.rangeClosed(-10_000, 10_000).toArray());

        // 15. 최대 입력 크기 - 고유 원소 20001개, k = 10000
        int[] nums15 = new int[100_000];
        for (int i = 0; i < nums15.length; i++) nums15[i] = i < 89_999 ? (i % 10_000) - 10_000 : i - 89_999;
        Supplier<int[]> r15 = () -> sorted(sol.topKFrequent(nums15, 10_000));
        Test.mark("r15", r15, IntStream.range(-10_000, 0).toArray());
    }


    private static int[] sorted(int[] array) {
        Arrays.sort(array);
        return array;
    }
}
