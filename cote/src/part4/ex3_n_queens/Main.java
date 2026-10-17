package part4.ex3_n_queens;

// 수업자료 36페이지: N-Queens
// https://leetcode.com/problems/n-queens/description/

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// n x n 체스판에 n개의 queen을 놓아야 하므로
// 하나의 행 또는 열에는 반드시 하나의 queen이 존재해야 함
class Solution {
    private int n;

    // 각 행(row)에서 queen이 놓인 열(col)의 인덱스
    // int[1] = 2의 의미: 1행에서 queen이 놓인 위치는 2열임
    private int[] queenColNums;

    // col, 이 값이 같은 셀들은 같은 열에 있음
    private boolean[] cols;

    // (row - col) + (n - 1), 이 값이 같은 셀들은 같은 우하향 대각선상에 있음
    // 따라서 이 값을 인덱스로 하는 배열의 원소가 true라면
    // 이미 대각선상에 queen이 선점하고 있다는 것을 알 수 있음
    // (n - 1)을 더한 이유는 인덱스가 음수이면 안 되기 때문임
    // 예시:
    // | 2 1 0 |
    // | 3 2 1 |
    // | 4 3 2 |
    // 3 x 3에서 위와 같이 구성할 수 있고
    // (0, 0)에 queen이 놓여 있다고 하면 diagDR[2] = true이므로
    // (1, 1), (2, 2)에 queen이 놓일 수 있는 경우를 배제할 수 있음
    private boolean[] diagDR;

    // (row + col), 이 값이 같은 셀들은 같은 우상향 대각선상에 있음
    private boolean[] diagUR;

    private List<List<String>> result;

    public List<List<String>> solveNQueens(int n) {
        this.n = n;
        queenColNums = new int[n];
        cols = new boolean[n];
        diagDR = new boolean[2 * n - 1];
        diagUR = new boolean[2 * n - 1];
        result = new ArrayList<>();

        place(0);
        return result;
    }

    private void place(int row) {
        if (row == n) {
            List<String> board = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                char[] rowChars = new char[n];
                Arrays.fill(rowChars, '.');
                rowChars[queenColNums[i]] = 'Q';
                board.add(new String(rowChars));
            }
            result.add(board);
            return;
        }

        for (int col = 0; col < n; col++) {
            int dr = (row - col) + (n - 1);
            int ur = row + col;
            if (cols[col] || diagDR[dr] || diagUR[ur]) {
                continue;
            }

            queenColNums[row] = col;
            cols[col] = diagDR[dr] = diagUR[ur] = true;
            place(row + 1);
            cols[col] = diagDR[dr] = diagUR[ur] = false;
        }
    }
}

public class Main {
    static void main(String[] args) {
        Solution sol = new Solution();

        System.out.println(sol.solveNQueens(1));
        System.out.println(sol.solveNQueens(2));
        System.out.println(sol.solveNQueens(3));
        System.out.println(sol.solveNQueens(4));
        System.out.println(sol.solveNQueens(5));
    }
}
