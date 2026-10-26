package week2.ex2_finding_primes;

// 소수 찾기
// https://school.programmers.co.kr/learn/courses/30/lessons/42839

import java.util.*;

class Solution {
    public int solution(String numbers) {
        int count = 0;

        char[] chars = numbers.toCharArray();
        Set<Integer> set = new HashSet<>();

        for (int i = 1; i <= chars.length; i++) {
            int[] path = new int[i];
            boolean[] visited = new boolean[chars.length];
            permutationDfs(chars, path, visited, 0, set);
        }

        count = (int) set.stream()
                .filter(this::isPrimeNumber)
                .count();

        return count;
    }

    private boolean isPrimeNumber(int number) {
        if (number <= 1) {
            return false;
        }
        if (number == 2) {
            return true;
        }
        int sqrt = (int) Math.sqrt(number);
        for (int i = 2; i <= sqrt; i ++) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }

    private void permutationDfs(char[] arr, int[] path, boolean[] visited, int depth, Set<Integer> set) {
        if (depth == path.length) {
            StringBuilder sb = new StringBuilder();
            for (int p : path) {
                sb.append(arr[p]);
            }
            set.add(Integer.parseInt(sb.toString()));
            return;
        }

        for (int i = 0; i < arr.length; i++) {
            if (visited[i]) {
                continue;
            }

            visited[i] = true;
            path[depth] = i;

            permutationDfs(arr, path, visited, depth + 1, set);

            visited[i] = false;
        }
    }
}

public class Main {
    static void main(String[] args) {
        Solution sol = new Solution();
        System.out.println(sol.solution("011"));
    }
}
