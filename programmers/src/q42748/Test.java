package q42748;

import java.util.Arrays;

// K번째수
// https://school.programmers.co.kr/learn/courses/30/lessons/42748
public class Test {
    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] array = {1, 5, 2, 6, 3, 7, 4};
        int[][] commands = {{2, 5, 3}, {4, 4, 1}, {1, 7, 3}};
        System.out.println(Arrays.toString(sol.solution(array, commands)));
    }
}

class Solution {
    public int[] solution(int[] array, int[][] commands) {
        int[] answer = new int[commands.length];
        for (int i = 0; i < commands.length; i++) {
            int[] command = commands[i];
            answer[i] = sort(slice(array, command[0], command[1]))[command[2] - 1];
        }
        return answer;
    }

    // 주어진 배열을 [start, end) 범위로 자를 배열을 반환
    private int[] slice(int[] array, int start, int end) {
        int length = end - start + 1;
        int[] sliced = new int[length];
        for (int i = 0; i < length; i++) {
            sliced[i] = array[start - 1 + i];
        }
        return sliced;

// 자바 표준 라이브러리 사용
//        return java.util.Arrays.copyOfRange(array, start - 1, end);
    }

    // int 배열을 오름차순으로 정렬
    private int[] sort(int[] array) {
        for (int i = 0; i < array.length; i++) {
            int change = i;
            // i+1번째 항목부터 끝까지 순회하며 최솟값을 가진 인덱스 찾음
            for (int j = i + 1; j < array.length; j++) {
                if (array[j] < array[change]) {
                    change = j;
                }
            }

            // 최솟값을 가진 인덱스가 i보다 크다면 두 원소의 위치를 교환
            if (change != i) {
                int temp = array[i];
                array[i] = array[change];
                array[change] = temp;
            }
        }

        return array;

// 자바 표준 라이브러리 사용
//        java.util.Arrays.sort(array);
//        return array;
    }
}