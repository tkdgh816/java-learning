package q42748;

public class Test {
    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] array = {1, 5, 2, 6, 3, 7, 4};
        int[][] commands = {{2, 5, 3}, {4, 4, 1}, {1, 7, 3}};
        for (int answer : sol.solution(array, commands)) {
            System.out.print(answer + " ");
        }
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

    private int[] sort(int[] array) {
        for (int i = 0; i < array.length; i++) {
            int change = i;
            for (int j = i + 1; j < array.length; j++) {
                if (array[j] < array[change]) {
                    change = j;
                }
            }
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