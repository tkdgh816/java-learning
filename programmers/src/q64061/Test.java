package q64061;

import java.util.ArrayDeque;
import java.util.Deque;

// https://school.programmers.co.kr/learn/courses/30/lessons/64061
public class Test {
    public static void main(String[] args) {
        Solution sol = new Solution();
        var result1 = sol.solution(
                new int[][]{{0, 0, 0, 0, 0}, {0, 0, 1, 0, 3}, {0, 2, 5, 0, 1}, {4, 2, 4, 4, 2}, {3, 5, 1, 3, 1}},
                new int[]{1, 5, 3, 5, 1, 2, 1, 4});
        System.out.println(result1);
    }
}

class Solution {
    public int solution(int[][] board, int[] moves) {
        int result = 0;

        Deque<Integer> stack = new ArrayDeque<>();

        for (int move : moves) {
            // 뽑은 인형 값
            int value = pickUp(board, move);

            if (value == 0) {
                continue;
            }

            // 스택이 비어 있지 않고 가장 위 값과 뽑은 값이 일치하면
            // 스택에서 꺼내고 결과에 2 더함(사라진 인형 개수가 2개이므로)
            if (!stack.isEmpty()) {
                if (stack.peek() == value) {
                    stack.pop();
                    result += 2;
                    continue;
                }
            }

            stack.push(value);
        }

        return result;
    }

    // [0][0] [0][1] [0][2] ...
    // [1][0] [1][1] [1][2] ...
    // [2][0] [2][1] [2][2] ...
    //  ...    ...    ...   ...
    //  1열     2열    3열   ...

    // move열 board[0][move-1] ~ board[N-1][move-1]의 가장 위에서 꺼냄
    private int pickUp(int[][] board, int move) {
        for (int i = 0; i < board.length; i++) {
            int value = board[i][move - 1];
            if (value > 0) {
                board[i][move - 1] = 0;
                return value;
            }
        }

        return 0;
    }
}