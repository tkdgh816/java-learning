package all.part5.op3_stepping_stones;

// https://school.programmers.co.kr/learn/courses/30/lessons/43236

//  [2, 14, 11, 21, 17]
// 0 2 11 14 17 21 25
//   2 9  3  3  4  4

import java.util.Arrays;

class Solution {
    public int solution(int distance, int[] rocks, int n) {
        Arrays.sort(rocks);
        int[] distances = new int[n + 1];
        distances[0] = rocks[0];
        for (int i = 1; i < rocks.length; i++) {
            distances[i] = rocks[i + 1] - rocks[i];
        }
        distances[rocks.length] = distance - rocks[rocks.length - 1];

        int answer = 0;
        return answer;
    }
}

public class Main {
}
