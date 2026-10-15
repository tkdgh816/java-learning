package part5.ex1_find_position;

// 수업자료 16페이지: Find First and Last Position
// https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/

import java.util.Arrays;

// 시간 복잡도 최악의 경우 O(n) -> nums의 대부분이 target과 같은 경우
class Solution1 {
    public int[] searchRange(int[] nums, int target) {
        int start = 0;
        int end = nums.length - 1;

        while (start <= end) {
            int mid = start + (end - start) / 2;
            int cmp = Integer.compare(nums[mid], target);

            if (cmp == 0) {
                int lowerBound = start;
                int upperBound = end;

                while (lowerBound < mid && nums[lowerBound] != target) {
                    lowerBound++;
                }

                while (upperBound > mid && nums[upperBound] != target) {
                    upperBound--;
                }

                return new int[]{lowerBound, upperBound};
            } else if (cmp < 0) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }

        return new int[]{-1, -1};
    }
}

// 시간 복잡도 O(logn)
class Solution2 {
    public int[] searchRange(int[] nums, int target) {
        int start = 0;
        int end = nums.length - 1;
        int lowerBound = -1;
        while (start <= end) {
            int mid = start + (end - start) / 2;
            int cmp = Integer.compare(nums[mid], target);

            if (cmp < 0) {
                start = mid + 1;
            } else {
                if (cmp == 0) {
                    lowerBound = mid;
                }
                end = mid - 1;
            }
        }

        if (lowerBound < 0) {
            return new int[]{-1, -1};
        }

        start = 0;
        end = nums.length - 1;
        int upperBound = -1;
        while (start <= end) {
            int mid = start + (end - start) / 2;
            int cmp = Integer.compare(nums[mid], target);

            if (cmp > 0) {
                end = mid - 1;
            } else {
                if (cmp == 0) {
                    upperBound = mid;
                }
                start = mid + 1;
            }
        }

        return new int[]{lowerBound, upperBound};
    }
}

public class Main {
    static void main(String[] args) {
        Solution2 sol = new Solution2();
        var r1 = sol.searchRange(new int[]{5, 7, 7, 8, 8, 10}, 8);
        System.out.println(Arrays.toString(r1)); // [3, 4]

        var r2 = sol.searchRange(new int[]{5, 7, 7, 8, 8, 10}, 6);
        System.out.println(Arrays.toString(r2)); // [-1, -1]

        var r3 = sol.searchRange(new int[]{}, 0);
        System.out.println(Arrays.toString(r3)); // [-1, -1]

        var r4 = sol.searchRange(new int[]{1}, 1);
        System.out.println(Arrays.toString(r4)); // [0, 0]

        var r5 = sol.searchRange(new int[]{1}, 0);
        System.out.println(Arrays.toString(r5)); // [-1, -1]

        var r6 = sol.searchRange(new int[]{2, 2, 2, 2, 2}, 2);
        System.out.println(Arrays.toString(r6)); // [0, 4]

        var r7 = sol.searchRange(new int[]{1, 1, 2, 3, 4}, 1);
        System.out.println(Arrays.toString(r7)); // [0, 1]

        var r8 = sol.searchRange(new int[]{1, 2, 3, 4, 4}, 4);
        System.out.println(Arrays.toString(r8)); // [3, 4]

        var r9 = sol.searchRange(new int[]{1, 2, 3, 4, 5}, 1);
        System.out.println(Arrays.toString(r9)); // [0, 0]

        var r10 = sol.searchRange(new int[]{1, 2, 3, 4, 5}, 5);
        System.out.println(Arrays.toString(r10)); // [4, 4]

        var r11 = sol.searchRange(new int[]{1, 2, 2, 2, 2, 3, 4}, 2);
        System.out.println(Arrays.toString(r11)); // [1, 4]

        var r12 = sol.searchRange(new int[]{3, 5, 7, 9}, 1);
        System.out.println(Arrays.toString(r12)); // [-1, -1]

        var r13 = sol.searchRange(new int[]{3, 5, 7, 9}, 10);
        System.out.println(Arrays.toString(r13)); // [-1, -1]

        var r14 = sol.searchRange(new int[]{1, 3, 5, 7}, 4);
        System.out.println(Arrays.toString(r14)); // [-1, -1]

        var r15 = sol.searchRange(new int[]{-10, -5, -5, -5, 0, 2}, -5);
        System.out.println(Arrays.toString(r15)); // [1, 3]

        var r16 = sol.searchRange(
                new int[]{-1_000_000_000, -1_000_000_000, 0,
                        1_000_000_000, 1_000_000_000},
                1_000_000_000
        );
        System.out.println(Arrays.toString(r16)); // [3, 4]
    }
}
