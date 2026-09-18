package q68644;

import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;

// 두 개 뽑아서 더하기
// https://school.programmers.co.kr/learn/courses/30/lessons/68644
public class Test {
    public static void main(String[] args) {
        Solution sol = new Solution();
        System.out.println(Arrays.toString(sol.solution(new int[]{2, 1, 3, 4, 1})));
        System.out.println(Arrays.toString(sol.solution(new int[]{5, 0, 2, 7})));
    }
}

class Solution {
    public int[] solution(int[] numbers) {
        Set<Integer> set = new TreeSet<>();
//        Set<Integer> set = new TreeSet<>(Comparator.naturalOrder());
        for (int i = 0; i < numbers.length; i++) {
            for (int j = i + 1; j < numbers.length; j++) {
                set.add(numbers[i] + numbers[j]);
            }
        }

        return set.stream()
                .mapToInt(Integer::intValue)
                .toArray();
    }
}