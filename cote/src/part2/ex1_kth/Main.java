package part2.ex1_kth;

// 수업자료 107페이지: K번째수
// https://school.programmers.co.kr/learn/courses/30/lessons/42748

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Solution1 sol = new Solution1();
        var r1 = sol.solution(new int[] {1, 5, 2, 6, 3, 7, 4}, new int[][] {{2, 5, 3}, {4, 4, 1}, {1, 7, 3}});
        System.out.println(Arrays.toString(r1));
    }
}

class Solution1 {
    public int[] solution(int[] array, int[][] commands) {
        int[] results = new int[commands.length];
        for(int i = 0; i < commands.length; i++){
            int[] command = commands[i];
            int[] sliced = Arrays.copyOfRange(array, command[0] - 1, command[1]);
            Arrays.sort(sliced);
            results[i] = sliced[command[2] - 1];
        }
        return results;
    }
}