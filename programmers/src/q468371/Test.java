package q468371;

import java.util.Arrays;
import java.util.List;

// 노란불 신호등
// https://school.programmers.co.kr/learn/courses/30/lessons/468371
public class Test {
    public static void main(String[] args) {
        Solution sol = new Solution();
        System.out.println(sol.solution(new int[][]{{2, 1, 2}, {5, 1, 1}}));
        System.out.println(sol.solution(new int[][]{{2, 3, 2}, {3, 1, 3}, {2, 1, 1}}));
        System.out.println(sol.solution(new int[][]{{3, 3, 3}, {5, 4, 2}, {2, 1, 2}}));
        System.out.println(sol.solution(new int[][]{{1, 1, 4}, {2, 1, 3}, {3, 1, 2}, {4, 1, 1}}));
    }
}

class Solution {
    public int solution(int[][] signals) {
        int count = 0;
        List<boolean[]> yellows = Arrays.stream(signals)
                .map(this::getYellow)
                .toList();

        int maxCount = 1;
        for (int[] signal : signals) {
            maxCount = lcm(maxCount, signal[0] + signal[1] + signal[2]);
        }

        outer:
        while (count < maxCount) {
            for (boolean[] yellow : yellows) {
                if (!yellow[count % yellow.length]) {
                    count++;
                    continue outer;
                }
            }
            return count + 1;
        }

        return -1;
    }

    private boolean[] getYellow(int[] signal) {
        boolean[] yellow = new boolean[signal[0] + signal[1] + signal[2]];
        for (int i = signal[0]; i < signal[0] + signal[1]; i++) {
            yellow[i] = true;
        }

        return yellow;
    }

    private int gcd(int a, int b) {
        while (b != 0) {
            int temp = a % b;
            a = b;
            b = temp;
        }
        return a;
    }

    private int lcm(int a, int b) {
        return a / gcd(a, b) * b;
    }
}