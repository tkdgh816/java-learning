package q42862;

import java.util.Arrays;
import java.util.Set;

// 체육복
// https://school.programmers.co.kr/learn/courses/30/lessons/42862
public class Test {
    public static void main(String[] args) {
        Solution sol = new Solution();
    }
}

class Solution {
    public int solution(int n, int[] lost, int[] reserve) {
        boolean[] extras = new boolean[n];

        for (int r : reserve) {
            extras[r - 1] = true;
        }

        int answer = 0;
        return answer;
    }

    private void borrowLeft(Set<Integer> lost, Set<Integer> reserve) {

    }

    private void borrowRight(Set<Integer> lost, Set<Integer> reserve) {

    }
}