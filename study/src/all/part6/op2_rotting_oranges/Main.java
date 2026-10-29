package all.part6.op2_rotting_oranges;

// https://leetcode.com/problems/rotting-oranges/description/

import helpers.Test;

import java.util.*;
import java.util.function.Supplier;

class Solution {
    public int orangesRotting(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        List<Point> points = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 2) {
                    points.add(new Point(i, j));
                }
            }
        }

        // UDLR
        int[] rowDelta = {-1, 1, 0, 0};
        int[] colDelta = {0, 0, -1, 1};

        int count = 0;
        while (true) {
            List<Point> rotting = new ArrayList<>();
            for (Point current : points) {
                for (int i = 0; i < 4; i++) {
                    int row = current.row() + rowDelta[i];
                    int col = current.col() + colDelta[i];

                    if (row < 0 || row >= m || col < 0 || col >= n) {
                        continue;
                    }

                    if (grid[row][col] == 1) {
                        rotting.add(new Point(row, col));
                        grid[row][col] = 2;
                    }
                }
            }

            if (rotting.isEmpty()) {
                break;
            }

            points = rotting;
            count++;
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    return -1;
                }
            }
        }

        return count;
    }

    private record Point(int row, int col) {

    }
}

public class Main {
    static void main(String[] args) {
        Solution sol = new Solution();

// 1. 기본 예제 1
        var r1 = sol.orangesRotting(new int[][]{{2, 1, 1}, {1, 1, 0}, {0, 1, 1}});
        Test.check("r1", r1, 4);

// 2. 기본 예제 2 - 도달할 수 없는 오렌지
        var r2 = sol.orangesRotting(new int[][]{{2, 1, 1}, {0, 1, 1}, {1, 0, 1}});
        Test.check("r2", r2, -1);

// 3. 기본 예제 3 - 신선한 오렌지가 없음
        var r3 = sol.orangesRotting(new int[][]{{0, 2}});
        Test.check("r3", r3, 0);

// 4. 최소 크기 - 빈 칸
        var r4 = sol.orangesRotting(new int[][]{{0}});
        Test.check("r4", r4, 0);

// 5. 최소 크기 - 신선한 오렌지만 존재
        var r5 = sol.orangesRotting(new int[][]{{1}});
        Test.check("r5", r5, -1);

// 6. 최소 크기 - 썩은 오렌지만 존재
        var r6 = sol.orangesRotting(new int[][]{{2}});
        Test.check("r6", r6, 0);

// 7. 한 번의 전파
        var r7 = sol.orangesRotting(new int[][]{{2, 1}});
        Test.check("r7", r7, 1);

// 8. 양방향 동시 전파
        var r8 = sol.orangesRotting(new int[][]{{1, 2, 1}});
        Test.check("r8", r8, 1);

// 9. 빈 칸에 의해 전파 차단
        var r9 = sol.orangesRotting(new int[][]{{1, 0, 2}});
        Test.check("r9", r9, -1);

// 10. 세로 방향 전파
        var r10 = sol.orangesRotting(new int[][]{{2}, {1}, {1}, {1}, {1}});
        Test.check("r10", r10, 4);

// 11. 가로 방향 전파
        var r11 = sol.orangesRotting(new int[][]{{2, 1, 1, 1, 1, 1, 1, 1, 1, 1}});
        Test.check("r11", r11, 9);

// 12. 일부만 전파되고 신선한 오렌지가 남는 경우
        var r12 = sol.orangesRotting(new int[][]{{2, 1, 0, 1, 0}});
        Test.check("r12", r12, -1);

// 13. 썩은 오렌지가 여러 개지만 전파 불가능
        var r13 = sol.orangesRotting(new int[][]{{2, 0, 1}, {0, 0, 0}, {1, 0, 2}});
        Test.check("r13", r13, -1);

// 14. 여러 썩은 오렌지에서 동시에 전파
        var r14 = sol.orangesRotting(new int[][]{{2, 1, 1, 1, 2}, {1, 1, 1, 1, 1}, {1, 1, 1, 1, 1}});
        Test.check("r14", r14, 4);

// 15. 장애물에 의해 우회 전파가 필요한 경우
        var r15 = sol.orangesRotting(new int[][]{
                {2, 1, 1, 1, 1},
                {0, 0, 0, 0, 1},
                {1, 1, 1, 1, 1},
                {1, 0, 0, 0, 0},
                {1, 1, 1, 1, 1}
        });
        Test.check("r15", r15, 16);

// 16. 최대 크기 - 한쪽 모서리에서 전체 전파
        Supplier<Integer> r16 = () -> sol.orangesRotting(new int[][]{
                {2, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1}
        });
        Test.check("r16", r16, 18);

// 17. 최대 크기 - 양쪽 모서리에서 동시 전파
        Supplier<Integer> r17 = () -> sol.orangesRotting(new int[][]{
                {2, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 2}
        });
        Test.check("r17", r17, 9);

// 18. 최대 크기 - 모든 오렌지가 신선한 경우
        Supplier<Integer> r18 = () -> {
            int[][] grid = new int[10][10];
            for (int[] row : grid) Arrays.fill(row, 1);
            return sol.orangesRotting(grid);
        };
        Test.check("r18", r18, -1);

// 19. 최대 크기 - 지그재그 경로를 통한 장시간 전파
        Supplier<Integer> r19 = () -> sol.orangesRotting(new int[][]{
                {2, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                {1, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                {1, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 1}
        });
        Test.check("r19", r19, 54);

// 20. 최대 크기 - 썩은 오렌지와 신선한 오렌지가 교대로 존재
        Supplier<Integer> r20 = () -> {
            int[][] grid = new int[10][10];
            for (int i = 0; i < 10; i++) {
                for (int j = 0; j < 10; j++) {
                    grid[i][j] = (i + j) % 2 == 0 ? 2 : 1;
                }
            }
            return sol.orangesRotting(grid);
        };
        Test.check("r20", r20, 1);

// 21. 최대 크기 - 모든 오렌지가 이미 썩은 경우
        Supplier<Integer> r21 = () -> {
            int[][] grid = new int[10][10];
            for (int[] row : grid) Arrays.fill(row, 2);
            return sol.orangesRotting(grid);
        };
        Test.check("r21", r21, 0);
    }
}
