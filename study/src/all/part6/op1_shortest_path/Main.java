package all.part6.op1_shortest_path;

// https://leetcode.com/problems/shortest-path-in-a-grid-with-obstacles-elimination/description/

import helpers.Test;

import java.util.*;
import java.util.function.Supplier;

class Solution {
    private int[][] grid;
    private int m, n;
    private int min;

    public int shortestPath(int[][] grid, int k) {
        this.grid = grid;
        this.m = grid.length;
        this.n = grid[0].length;
        this.min = Integer.MAX_VALUE;

        List<Point> points = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    points.add(new Point(i, j));
                }
            }
        }
        points.sort(Comparator.comparingInt(Point::row).thenComparingInt(Point::col));
        combination(points, new ArrayList<>(), k, 0);
        return min == Integer.MAX_VALUE ? -1 : min;
    }

    private int shortestPath(Point start, Point end) {
        boolean[][] visited = new boolean[m][n];

        int[] rowDelta = {-1, 1, 0, 0};
        int[] colDelta = {0, 0, -1, 1};

        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{start.row, start.col, 0});
        visited[0][0] = true;

        while (!queue.isEmpty()) {
            int[] cell = queue.poll();
            int row = cell[0];
            int col = cell[1];
            int step = cell[2];

            // 목적지 도착
            if (row == end.row && col == end.col) {
                return step;
            }

            for (int i = 0; i < rowDelta.length; i++) {
                int nextRow = row + rowDelta[i];
                int nextCol = col + colDelta[i];

                if (nextRow < start.row || nextRow > end.row) {
                    continue;
                }
                if (nextCol < start.col || nextCol > end.col) {
                    continue;
                }
                if (visited[nextRow][nextCol]) {
                    continue;
                }
                if ((nextRow < end.row || nextCol < end.col) && grid[nextRow][nextCol] == 1) {
                    continue;
                }

                queue.offer(new int[]{nextRow, nextCol, step + 1});
                visited[nextRow][nextCol] = true;
            }
        }

        return 0;
    }

    private void combination(List<Point> points, List<Integer> selectedIndexes, int r, int startIndex) {
        if (selectedIndexes.size() == r) {
            List<Point> selected = new ArrayList<>();
            for (int i : selectedIndexes) {
                selected.add(points.get(i));
            }

            selected.addFirst(new Point(0, 0));
            selected.addLast(new Point(m - 1, n - 1));

            int distance = 0;
            for (int i = 0; i <= r; i++) {
                Point next = selected.get(i + 1);
                int d = shortestPath(selected.get(i), selected.get(i + 1));

                if (d == 0) {
                    return;
                }
                distance += d;
            }

            if (distance > 0) {
                min = Math.min(min, distance);
            }
            return;
        }

        for (int i = startIndex; i < points.size(); i++) {
            selectedIndexes.add(i);
            combination(points, selectedIndexes, r, i + 1);
            selectedIndexes.removeLast();
        }
    }


    private record Point(int row, int col) {
    }
}

public class Main {
    static void main(String[] args) {
        Solution sol = new Solution();
        // 1. 기본 예제 1
        var r1 = sol.shortestPath(new int[][]{{0, 0, 0}, {1, 1, 0}, {0, 0, 0}, {0, 1, 1}, {0, 0, 0}}, 1);
        Test.check("r1", r1, 6);

// 2. 기본 예제 2 - 장애물 제거 횟수 부족
        var r2 = sol.shortestPath(new int[][]{{0, 1, 1}, {1, 1, 1}, {1, 0, 0}}, 1);
        Test.check("r2", r2, -1);

// 3. 최소 크기 - 시작점과 도착점이 동일
        var r3 = sol.shortestPath(new int[][]{{0}}, 1);
        Test.check("r3", r3, 0);

// 4. 한 행 - 장애물이 없는 경우
        var r4 = sol.shortestPath(new int[][]{{0, 0, 0, 0, 0}}, 1);
        Test.check("r4", r4, 4);

// 5. 한 행 - 장애물 제거 횟수 부족
        var r5 = sol.shortestPath(new int[][]{{0, 1, 1, 1, 0}}, 2);
        Test.check("r5", r5, -1);

// 6. 한 행 - 장애물을 정확히 제거할 수 있는 경우
        var r6 = sol.shortestPath(new int[][]{{0, 1, 1, 1, 0}}, 3);
        Test.check("r6", r6, 4);

// 7. 한 열 - 장애물 제거 횟수 부족
        var r7 = sol.shortestPath(new int[][]{{0}, {1}, {1}, {0}}, 1);
        Test.check("r7", r7, -1);

// 8. 한 열 - 장애물을 정확히 제거할 수 있는 경우
        var r8 = sol.shortestPath(new int[][]{{0}, {1}, {1}, {0}}, 2);
        Test.check("r8", r8, 3);

// 9. 시작점의 모든 인접 칸이 장애물
        var r9 = sol.shortestPath(new int[][]{{0, 1}, {1, 0}}, 1);
        Test.check("r9", r9, 2);

// 10. 장애물로 둘러싸인 구조 - 제거 횟수 부족
        var r10 = sol.shortestPath(new int[][]{{0, 1, 0}, {1, 1, 1}, {0, 1, 0}}, 1);
        Test.check("r10", r10, -1);

// 11. 동일 구조 - 제거 횟수 충분
        var r11 = sol.shortestPath(new int[][]{{0, 1, 0}, {1, 1, 1}, {0, 1, 0}}, 2);
        Test.check("r11", r11, 4);

// 12. 도착점에 접근하려면 장애물 제거가 필요
        var r12 = sol.shortestPath(new int[][]{{0, 0, 0}, {0, 0, 1}, {0, 1, 0}}, 1);
        Test.check("r12", r12, 4);

// 13. 장애물이 전혀 없는 정사각형
        var r13 = sol.shortestPath(new int[][]{{0, 0, 0}, {0, 0, 0}, {0, 0, 0}}, 1);
        Test.check("r13", r13, 4);

// 14. 장애물 제거가 부족해 긴 우회 경로를 선택
        var r14 = sol.shortestPath(new int[][]{
                {0, 1, 0, 0, 0},
                {0, 1, 0, 1, 0},
                {0, 0, 0, 1, 1},
                {1, 0, 1, 1, 0}
        }, 1);
        Test.check("r14", r14, 11);

// 15. 동일 구조 - 장애물 제거 횟수를 늘리면 최단 경로 단축
        var r15 = sol.shortestPath(new int[][]{
                {0, 1, 0, 0, 0},
                {0, 1, 0, 1, 0},
                {0, 0, 0, 1, 1},
                {1, 0, 1, 1, 0}
        }, 2);
        Test.check("r15", r15, 7);

// 16. 동일 좌표에 서로 다른 제거 횟수로 도착하는 상태
        var r16 = sol.shortestPath(new int[][]{
                {0, 0, 0, 1},
                {1, 0, 1, 1},
                {1, 0, 1, 1},
                {1, 0, 1, 0}
        }, 1);
        Test.check("r16", r16, 6);

// 17. 단순한 2차원 visited로는 탐색이 실패할 수 있는 구조
        var r17 = sol.shortestPath(new int[][]{
                {0, 0, 0, 0, 0},
                {1, 0, 0, 1, 0},
                {1, 1, 1, 1, 1},
                {1, 0, 0, 1, 0}
        }, 1);
        Test.check("r17", r17, 7);

// 18. 복잡한 장애물 배치 - 우회 경로 선택
        var r18 = sol.shortestPath(new int[][]{
                {0, 1, 1, 1, 0},
                {1, 1, 0, 0, 0},
                {0, 1, 0, 0, 0},
                {0, 1, 0, 1, 0},
                {0, 0, 0, 1, 0}
        }, 1);
        Test.check("r18", r18, 12);

// 19. 복잡한 미로 - 긴 우회 경로
        var r19 = sol.shortestPath(new int[][]{
                {0, 1, 1, 0, 0, 0},
                {0, 1, 1, 0, 1, 0},
                {1, 1, 0, 0, 1, 0},
                {0, 0, 1, 0, 1, 0},
                {0, 0, 1, 0, 1, 0},
                {1, 0, 0, 0, 1, 0}
        }, 1);
        Test.check("r19", r19, 20);

// 20. 동일 미로 - 제거 횟수 증가로 경로 단축
        var r20 = sol.shortestPath(new int[][]{
                {0, 1, 1, 0, 0, 0},
                {0, 1, 1, 0, 1, 0},
                {1, 1, 0, 0, 1, 0},
                {0, 0, 1, 0, 1, 0},
                {0, 0, 1, 0, 1, 0},
                {1, 0, 0, 0, 1, 0}
        }, 2);
        Test.check("r20", r20, 10);

// 21. 최대 크기 - 장애물이 전혀 없는 경우
        Supplier<Integer> r21 = () -> sol.shortestPath(new int[40][40], 1);
        Test.check("r21", r21, 78);

// 22. 최대 크기 - 모든 내부 칸이 장애물이고 제거 횟수가 부족
        Supplier<Integer> r22 = () -> {
            int[][] grid = new int[40][40];
            for (int[] row : grid) Arrays.fill(row, 1);
            grid[0][0] = grid[39][39] = 0;
            return sol.shortestPath(grid, 76);
        };
        Test.check("r22", r22, -1);

// 23. 최대 크기 - 모든 내부 칸이 장애물이고 제거 횟수가 정확히 충분
        Supplier<Integer> r23 = () -> {
            int[][] grid = new int[40][40];
            for (int[] row : grid) Arrays.fill(row, 1);
            grid[0][0] = grid[39][39] = 0;
            return sol.shortestPath(grid, 77);
        };
        Test.check("r23", r23, 78);

// 24. 최대 크기 및 최대 k - 제거 횟수가 매우 충분
        Supplier<Integer> r24 = () -> {
            int[][] grid = new int[40][40];
            for (int[] row : grid) Arrays.fill(row, 1);
            grid[0][0] = grid[39][39] = 0;
            return sol.shortestPath(grid, 1600);
        };
        Test.check("r24", r24, 78);

// 25. 최대 크기 - 체커보드 장애물, 제거 횟수 부족
        Supplier<Integer> r25 = () -> {
            int[][] grid = new int[40][40];
            for (int i = 0; i < 40; i++) {
                for (int j = 0; j < 40; j++) grid[i][j] = (i + j) % 2;
            }
            return sol.shortestPath(grid, 38);
        };
        Test.check("r25", r25, -1);

// 26. 최대 크기 - 동일한 체커보드에서 제거 횟수가 충분
        Supplier<Integer> r26 = () -> {
            int[][] grid = new int[40][40];
            for (int i = 0; i < 40; i++) {
                for (int j = 0; j < 40; j++) grid[i][j] = (i + j) % 2;
            }
            return sol.shortestPath(grid, 39);
        };
        Test.check("r26", r26, 78);

// 27. 최대 크기 - 가로 장벽이 반복되는 구조, 제거 횟수 부족
        Supplier<Integer> r27 = () -> {
            int[][] grid = new int[40][40];
            for (int i = 1; i < 40; i += 2) Arrays.fill(grid[i], 1);
            grid[39][39] = 0;
            return sol.shortestPath(grid, 18);
        };
        Test.check("r27", r27, -1);

// 28. 최대 크기 - 동일한 장벽 구조에서 제거 횟수가 충분
        Supplier<Integer> r28 = () -> {
            int[][] grid = new int[40][40];
            for (int i = 1; i < 40; i += 2) Arrays.fill(grid[i], 1);
            grid[39][39] = 0;
            return sol.shortestPath(grid, 19);
        };
        Test.check("r28", r28, 78);

// 29. 최대 크기 - 지그재그 미로, 장거리 탐색
        Supplier<Integer> r29 = () -> {
            int[][] grid = new int[40][40];
            for (int i = 1; i < 38; i += 2) {
                Arrays.fill(grid[i], 1);
                grid[i][i % 4 == 1 ? 39 : 0] = 0;
            }
            return sol.shortestPath(grid, 1);
        };
        Test.check("r29", r29, 702);

// 30. 최대 크기 - 동일한 지그재그 미로, 제거 횟수 2
        Supplier<Integer> r30 = () -> {
            int[][] grid = new int[40][40];
            for (int i = 1; i < 38; i += 2) {
                Arrays.fill(grid[i], 1);
                grid[i][i % 4 == 1 ? 39 : 0] = 0;
            }
            return sol.shortestPath(grid, 2);
        };
        Test.check("r30", r30, 624);

// 31. 최대 크기 - 동일한 지그재그 미로, 충분한 제거 횟수
        Supplier<Integer> r31 = () -> {
            int[][] grid = new int[40][40];
            for (int i = 1; i < 38; i += 2) {
                Arrays.fill(grid[i], 1);
                grid[i][i % 4 == 1 ? 39 : 0] = 0;
            }
            return sol.shortestPath(grid, 10);
        };
        Test.check("r31", r31, 78);
    }
}
