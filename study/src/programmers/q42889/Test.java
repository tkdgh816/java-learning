package programmers.q42889;

import java.util.Arrays;

// https://school.programmers.co.kr/learn/courses/30/lessons/42889
public class Test {
    public static void main(String[] args) {
        Solution sol = new Solution();
        System.out.println(Arrays.toString(sol.solution(5, new int[]{2, 1, 2, 6, 2, 4, 3, 3})));
        System.out.println(Arrays.toString(sol.solution(4, new int[]{4, 4, 4, 4, 4})));
    }
}

class Solution {
    public int[] solution(int N, int[] stages) {
        Stage[] list = new Stage[N];

        int[] failures = new int[N];
        for (int stage : stages) {
            if (stage - 1 < N) {
                failures[stage - 1]++;
            }
        }

        int reached = stages.length;
        for (int i = 0; i < N; i++) {
            Stage stage = new Stage(i + 1, reached, failures[i]);

            list[i] = stage;

            reached -= failures[i];
        }

        Arrays.sort(list);

        return Arrays.stream(list).mapToInt(Stage::getNumber).toArray();
    }
}

class Stage implements Comparable<Stage> {
    private final int number;
    private final int attemptCount;
    private final int failureCount;

    Stage(int number, int attemptCount, int failureCount) {
        this.number = number;
        this.attemptCount = attemptCount;
        this.failureCount = failureCount;
    }

    public int getNumber() {
        return number;
    }

    @Override
    public int compareTo(Stage other) {
        long m1 = (long) this.failureCount * other.attemptCount;
        long m2 = (long) other.failureCount * this.attemptCount;

        int comparison = Long.compare(m2, m1);
        if (comparison == 0) {
            return Integer.compare(this.number, other.number);
        }

        return comparison;
    }
}