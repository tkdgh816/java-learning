package part6.ex2_islands;

// 수업자료 61페이지: Number of Islands
// https://leetcode.com/problems/number-of-islands/description/

import helpers.Test;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

// 직관적으로
class Solution1 {
    public int numIslands(char[][] grid) {
        int islandCount = 0;

        int m = grid.length;
        int n = grid[0].length;
        boolean[][] visited = new boolean[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '0' || visited[i][j]) {
                    continue;
                }

                Deque<Cell> stack = new ArrayDeque<>();
                stack.push(new Cell(i, j));
                visited[i][j] = true;

                while (!stack.isEmpty()) {
                    Cell cell = stack.pop();
                    int row = cell.row;
                    int col = cell.col;

                    // left
                    if (row > 0) {
                        if (!visited[row - 1][col] && grid[row - 1][col] == '1') {
                            stack.push(new Cell(row - 1, col));
                            visited[row - 1][col] = true;
                        }
                    }

                    // right
                    if (row < m - 1) {
                        if (!visited[row + 1][col] && grid[row + 1][col] == '1') {
                            stack.push(new Cell(row + 1, col));
                            visited[row + 1][col] = true;
                        }
                    }

                    // up
                    if (col > 0) {
                        if (!visited[row][col - 1] && grid[row][col - 1] == '1') {
                            stack.push(new Cell(row, col - 1));
                            visited[row][col - 1] = true;
                        }
                    }

                    // down
                    if (col < n - 1) {
                        if (!visited[row][col + 1] && grid[row][col + 1] == '1') {
                            stack.push(new Cell(row, col + 1));
                            visited[row][col + 1] = true;
                        }
                    }
                }

                islandCount++;
            }
        }
        return islandCount;
    }

    record Cell(int row, int col) {
    }
}

class Solution2 {
    public int numIslands(char[][] grid) {
        int islandCount = 0;

        int m = grid.length;
        int n = grid[0].length;
        boolean[][] visited = new boolean[m][n];

        // 위아래로 볼 것
        // rowDelta[0]colDelta[0]: left, [1][1]: right, [2][2]: up, [3][3]: down
        int[] rowDelta = {-1, 1, 0, 0};
        int[] colDelta = {0, 0, 1, -1};

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '0' || visited[i][j]) {
                    continue;
                }

                Deque<Cell> stack = new ArrayDeque<>();
                stack.push(new Cell(i, j));
                visited[i][j] = true;

                while (!stack.isEmpty()) {
                    Cell cell = stack.pop();
                    int row = cell.row;
                    int col = cell.col;

                    for (int d = 0; d < rowDelta.length; d++) {
                        int nextRow = row + rowDelta[d];
                        int nextCol = col + colDelta[d];

                        if (nextRow < 0 || nextRow >= m) {
                            continue;
                        }
                        if (nextCol < 0 || nextCol >= n) {
                            continue;
                        }
                        if (visited[nextRow][nextCol]) {
                            continue;
                        }
                        if (grid[nextRow][nextCol] == '0') {
                            continue;
                        }

                        stack.push(new Cell(nextRow, nextCol));
                        visited[nextRow][nextCol] = true;
                    }
                }

                islandCount++;
            }
        }
        return islandCount;
    }

    record Cell(int row, int col) {
    }
}

public class Main {
    static void main(String[] args) {
//        Solution1 sol = new Solution1();
        Solution2 sol = new Solution2();

        // ── 공식 예제 ──
        var r1 = sol.numIslands(new char[][]{
                {'1', '1', '1', '1', '0'},
                {'1', '1', '0', '1', '0'},
                {'1', '1', '0', '0', '0'},
                {'0', '0', '0', '0', '0'}
        });
        Test.check("r1", r1, 1);

        var r2 = sol.numIslands(new char[][]{
                {'1', '1', '0', '0', '0'},
                {'1', '1', '0', '0', '0'},
                {'0', '0', '1', '0', '0'},
                {'0', '0', '0', '1', '1'}
        });
        Test.check("r2", r2, 3);

// ── 경계값 ──
        var r3 = sol.numIslands(new char[][]{{'1'}});
        Test.check("r3", r3, 1);

        var r4 = sol.numIslands(new char[][]{{'0'}});
        Test.check("r4", r4, 0);

        var r5 = sol.numIslands(new char[][]{
                {'0', '0', '0'},
                {'0', '0', '0'},
                {'0', '0', '0'}
        });
        Test.check("r5", r5, 0);

        var r6 = sol.numIslands(new char[][]{
                {'1', '1', '1', '1'},
                {'1', '1', '1', '1'},
                {'1', '1', '1', '1'}
        });
        Test.check("r6", r6, 1);

// ── 한 줄 / 한 열 ──
        var r7 = sol.numIslands(new char[][]{
                {'1', '0', '1', '1', '0', '1'}
        });
        Test.check("r7", r7, 3);

        var r8 = sol.numIslands(new char[][]{
                {'1'},
                {'1'},
                {'0'},
                {'1'}
        });
        Test.check("r8", r8, 2);

// ── 연결 판정 (대각선은 연결 아님) ──
        var r9 = sol.numIslands(new char[][]{
                {'1', '0', '0'},
                {'0', '1', '0'},
                {'0', '0', '1'}
        });
        Test.check("r9", r9, 3);

        var r10 = sol.numIslands(new char[][]{
                {'1', '0', '1'},
                {'0', '1', '0'},
                {'1', '0', '1'}
        });
        Test.check("r10", r10, 5);

// ── 구멍이 있는 섬 ──
        var r11 = sol.numIslands(new char[][]{
                {'1', '1', '1'},
                {'1', '0', '1'},
                {'1', '1', '1'}
        });
        Test.check("r11", r11, 1);

// 고리 안쪽에 따로 떨어진 섬
        var r12 = sol.numIslands(new char[][]{
                {'1', '1', '1', '1', '1'},
                {'1', '0', '0', '0', '1'},
                {'1', '0', '1', '0', '1'},
                {'1', '0', '0', '0', '1'},
                {'1', '1', '1', '1', '1'}
        });
        Test.check("r12", r12, 2);

// ── 가장자리 ──
        var r13 = sol.numIslands(new char[][]{
                {'1', '0', '0', '1'},
                {'0', '0', '0', '0'},
                {'1', '0', '0', '1'}
        });
        Test.check("r13", r13, 4);

// ── 경로가 길게 꺾이는 섬 (뱀 모양) ──
        var r14 = sol.numIslands(new char[][]{
                {'1', '1', '1', '1', '1'},
                {'0', '0', '0', '0', '1'},
                {'1', '1', '1', '1', '1'},
                {'1', '0', '0', '0', '0'},
                {'1', '1', '1', '1', '1'}
        });
        Test.check("r14", r14, 1);

// 아래쪽에서 합쳐지는 U자 모양
        var r15 = sol.numIslands(new char[][]{
                {'1', '0', '1'},
                {'1', '0', '1'},
                {'1', '1', '1'}
        });
        Test.check("r15", r15, 1);

// ── 최대 크기 (300 x 300) ──
// 전부 '1': 재귀 DFS면 StackOverflowError가 날 수 있는 케이스
        char[][] big1 = new char[300][300];
        for (char[] row : big1) Arrays.fill(row, '1');
        var r16 = sol.numIslands(big1);
        Test.check("r16", r16, 1);

// 체스판 무늬: (i + j)가 짝수인 칸만 '1' → 모두 서로 떨어진 섬
        char[][] big2 = new char[300][300];
        for (int i = 0; i < 300; i++)
            for (int j = 0; j < 300; j++)
                big2[i][j] = ((i + j) % 2 == 0) ? '1' : '0';
        var r17 = sol.numIslands(big2);
        Test.check("r17", r17, 45000);
    }
}
