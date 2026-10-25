package week_1024.ex1_range_sum;

// Range Sum Query - Immutable
// https://leetcode.com/problems/range-sum-query-immutable/description/

class NumArray {
    private final int[] prefixSum;

    public NumArray(int[] nums) {
        prefixSum = new int[nums.length + 1];
        for (int i = 0; i < nums.length; i++) {
            prefixSum[i + 1] = prefixSum[i] + nums[i];
        }
    }

    public int sumRange(int left, int right) {
        return prefixSum[right + 1] - prefixSum[left];
    }
}


public class Main {
    static void main(String[] args) {
        NumArray numArray = new NumArray(new int[]{-2, 0, 3, -5, 2, -1});
        System.out.println(numArray.sumRange(0, 2)); // return (-2) + 0 + 3 = 1
        System.out.println(numArray.sumRange(2, 5)); // return 3 + (-5) + 2 + (-1) = -1
        System.out.println(numArray.sumRange(0, 5)); // return (-2) + 0 + 3 + (-5) + 2 + (-1) = -3
    }
}
