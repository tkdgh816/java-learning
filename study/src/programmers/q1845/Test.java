package programmers.q1845;

import java.util.HashSet;
import java.util.Set;

// 폰켓몬
// https://school.programmers.co.kr/learn/courses/30/lessons/1845
public class Test {
    public static void main(String[] args) {
        Solution sol = new Solution();
        System.out.println(sol.solution(new int[]{3, 1, 2, 3}));
        System.out.println(sol.solution(new int[]{3, 3, 3, 2, 2, 4}));
        System.out.println(sol.solution(new int[]{3, 3, 3, 2, 2, 2}));
    }
}

class Solution {
    public int solution(int[] nums) {
        Set<Integer> numberSet = new HashSet<Integer>();
        for (int num : nums) {
            numberSet.add(num);
        }

//        Set<Integer> numberSet = Arrays.stream(nums)
//                .boxed()
//                .collect(Collectors.toSet());

        return Math.min(nums.length / 2, numberSet.size());
    }
}