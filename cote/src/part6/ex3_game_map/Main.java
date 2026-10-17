package part6.ex3_game_map;

// 수업자료 62페이지: 게임 맵 최단거리
// https://school.programmers.co.kr/learn/courses/30/lessons/1844

import helpers.Test;

import java.util.ArrayDeque;
import java.util.Queue;

// BFS 사용 (BFS는 너비 우선 탐색이므로 최단 거리 발견 시 바로 종료 가능)
class Solution {
    public int solution(int[][] maps) {
        int m = maps.length;
        int n = maps[0].length;
        boolean[][] visited = new boolean[m][n];

        int[] rowDelta = {-1, 1, 0, 0};
        int[] colDelta = {0, 0, -1, 1};

        Queue<int[]> queue = new ArrayDeque<>();
        // 배열 저장 순서: 0 -> row(행), 1 -> col(열), 2 -> step(밟은 칸의 개수)
        queue.offer(new int[]{0, 0, 1});
        visited[0][0] = true;

        while (!queue.isEmpty()) {
            int[] cell = queue.poll();
            int row = cell[0];
            int col = cell[1];

            // 현재 위치까지 밟은 칸의 개수
            int step = cell[2];

            // 목적지 도착
            if (row == m - 1 && col == n - 1) {
                return step;
            }

            for (int i = 0; i < rowDelta.length; i++) {
                int nextRow = row + rowDelta[i];
                int nextCol = col + colDelta[i];

                if (nextRow < 0 || nextRow >= m) {
                    continue;
                }
                if (nextCol < 0 || nextCol >= n) {
                    continue;
                }
                if (visited[nextRow][nextCol]) {
                    continue;
                }
                if (maps[nextRow][nextCol] == 0) {
                    continue;
                }

                queue.offer(new int[]{nextRow, nextCol, step + 1});
                visited[nextRow][nextCol] = true;
            }
        }

        return -1;
    }
}

public class Main {
    static void main(String[] args) {
        Solution sol = new Solution();

        // 1. 공식 예제 1 (최단거리 11)
        var r1 = sol.solution(new int[][]{
                {1, 0, 1, 1, 1},
                {1, 0, 1, 0, 1},
                {1, 0, 1, 1, 1},
                {1, 1, 1, 0, 1},
                {0, 0, 0, 0, 1}});
        Test.check("r1", r1, 11);

        // 2. 공식 예제 2 (도달 불가)
        var r2 = sol.solution(new int[][]{
                {1, 0, 1, 1, 1},
                {1, 0, 1, 0, 1},
                {1, 0, 1, 1, 1},
                {1, 1, 1, 0, 0},
                {0, 0, 0, 0, 1}});
        Test.check("r2", r2, -1);

        // 3. 최소 크기 2x2 (장애물 없음)
        // 시작점에서 도착점까지 2번 이동, 지나간 칸은 3개
        var r3 = sol.solution(new int[][]{
                {1, 1},
                {1, 1}});
        Test.check("r3", r3, 3);

        // 4. 최소 크기 2x2 (도달 불가)
        // 대각선 이동은 허용되지 않음
        var r4 = sol.solution(new int[][]{
                {1, 0},
                {0, 1}});
        Test.check("r4", r4, -1);

        // 5. 가로 한 줄 (오른쪽으로만 이동)
        var r5 = sol.solution(new int[][]{
                {1, 1, 1, 1, 1}});
        Test.check("r5", r5, 5);

        // 6. 세로 한 줄 (아래쪽으로만 이동)
        var r6 = sol.solution(new int[][]{
                {1},
                {1},
                {1},
                {1},
                {1}});
        Test.check("r6", r6, 5);

        // 7. 직사각형 2x3 (장애물 없음)
        // 최단거리 = 행의 수 + 열의 수 - 1
        var r7 = sol.solution(new int[][]{
                {1, 1, 1},
                {1, 1, 1}});
        Test.check("r7", r7, 4);

        // 8. 긴 우회 경로 (최단거리 14)
        // 오른쪽 끝까지 이동한 뒤 왼쪽으로 돌아가야 함
        var r8 = sol.solution(new int[][]{
                {1, 1, 1, 1},
                {0, 0, 0, 1},
                {1, 1, 1, 1},
                {1, 0, 0, 0},
                {1, 1, 1, 1}});
        Test.check("r8", r8, 14);

        // 9. 직사각형 4x4 (장애물로 경로 제한)
        // 왼쪽 영역에서 아래로 이동한 뒤 오른쪽으로 진입
        var r9 = sol.solution(new int[][]{
                {1, 1, 0, 1},
                {0, 1, 0, 1},
                {1, 1, 0, 1},
                {1, 1, 1, 1}});
        Test.check("r9", r9, 7);

        // 10. 가로 방향 벽으로 완전히 분리된 경우
        // 시작점과 도착점이 서로 다른 영역에 존재
        var r10 = sol.solution(new int[][]{
                {1, 1, 1},
                {0, 0, 0},
                {1, 1, 1}});
        Test.check("r10", r10, -1);

        // 11. L자 형태의 유일한 이동 경로
        // 아래로 이동한 다음 오른쪽으로 이동
        var r11 = sol.solution(new int[][]{
                {1, 0, 0},
                {1, 0, 0},
                {1, 1, 1}});
        Test.check("r11", r11, 5);

        // 12. 중앙에 장애물이 있는 3x3
        // 위쪽 또는 아래쪽으로 우회 가능
        // 여러 최단 경로가 존재
        var r12 = sol.solution(new int[][]{
                {1, 1, 1},
                {1, 0, 1},
                {1, 1, 1}});
        Test.check("r12", r12, 5);

        // 13. 테두리를 따라 이동하는 5x5
        // 내부 장애물을 피해 바깥쪽으로 이동
        // 최단 경로가 여러 개 존재
        var r13 = sol.solution(new int[][]{
                {1, 1, 1, 1, 1},
                {1, 0, 0, 0, 1},
                {1, 0, 1, 0, 1},
                {1, 0, 0, 0, 1},
                {1, 1, 1, 1, 1}});
        Test.check("r13", r13, 9);

        // 14. 막다른 길이 존재하는 5x5
        // 중간에 잘못 진입하면 되돌아가야 함
        // BFS의 방문 처리 및 최단거리 탐색 확인
        var r14 = sol.solution(new int[][]{
                {1, 1, 1, 1, 1},
                {1, 0, 0, 0, 1},
                {1, 1, 1, 0, 1},
                {0, 0, 1, 0, 1},
                {1, 1, 1, 1, 1}});
        Test.check("r14", r14, 9);

        // 15. 시작점 주변에 막다른 경로가 존재
        // 위쪽 경로는 중간에 차단됨
        // 아래쪽으로 이동하여 목적지에 도달
        var r15 = sol.solution(new int[][]{
                {1, 1, 0, 1, 1},
                {0, 1, 0, 1, 0},
                {1, 1, 0, 1, 1},
                {1, 0, 0, 0, 1},
                {1, 1, 1, 1, 1}});
        Test.check("r15", r15, 11);

        // 16. 직사각형 5x6 (여러 경로와 장애물)
        // 오른쪽 테두리를 통한 최단 경로 존재
        // 다른 경로는 막다른 길로 이어짐
        var r16 = sol.solution(new int[][]{
                {1, 1, 1, 1, 1, 1},
                {1, 0, 0, 0, 0, 1},
                {1, 1, 1, 1, 0, 1},
                {0, 0, 0, 1, 0, 1},
                {1, 1, 1, 1, 1, 1}});
        Test.check("r16", r16, 10);

        // 17. 세로로 긴 4x2 직사각형
        // 장애물을 피해 지그재그 형태로 이동
        var r17 = sol.solution(new int[][]{
                {1, 0},
                {1, 1},
                {0, 1},
                {1, 1}});
        Test.check("r17", r17, 5);

        // 18. 세로로 긴 5x3 (중앙 장애물)
        // 오른쪽 또는 왼쪽 경로를 선택할 수 있음
        // 최단거리는 동일
        var r18 = sol.solution(new int[][]{
                {1, 1, 1},
                {1, 0, 1},
                {1, 0, 1},
                {1, 0, 1},
                {1, 1, 1}});
        Test.check("r18", r18, 7);

        // 19. 큰 입력
        var input = new int[100][];
        for (int i = 0; i < 100; i++) {
            input[i] = new int[100];
            for (int j = 0; j < 100; j++) input[i][j] = 1;
        }
        var r19 = sol.solution(input);
        Test.check("r19", r19, 199);
    }
}