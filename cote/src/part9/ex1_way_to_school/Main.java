package part9.ex1_way_to_school;

// 수업자료 36페이지: 등굣길
// https://school.programmers.co.kr/learn/courses/30/lessons/42898

import helpers.Test;

import java.util.function.Supplier;

// (i, j)까지 가는 최단 경로는 (i-1, j), (i, j-1)까지 가는 최단 경로들의 합
// a[i][j] = a[i-1][j] + a[i][j-1] (mod k)

class Solution {
    public int solution(int m, int n, int[][] puddles) {
        int[][] distances = new int[n][m];
        distances[0][0] = 1;

        // 잠긴 곳은 최단경로 수를 -1로 설정하고 경로 합산에 배제
        for (int[] puddle : puddles) {
            distances[puddle[1] - 1][puddle[0] - 1] = -1;
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                // 현재 칸이 잠긴 경우
                if (distances[i][j] == -1) {
                    continue;
                }

                // 위 칸이 존재하고 잠기지 않은 경우 합산
                if (i > 0 && distances[i - 1][j] != -1) {
                    distances[i][j] += distances[i - 1][j];
                }

                // 왼쪽 칸이 존재하고 잠기지 않은 경우 합산
                if (j > 0 && distances[i][j - 1] != -1) {
                    distances[i][j] += distances[i][j - 1];
                }

                // 나머지로 갱신
                distances[i][j] %= 1_000_000_007;
            }
        }

        return distances[n - 1][m - 1];
    }
}

public class Main {
    static void main(String[] args) {
        Solution sol = new Solution();

// 공식 예제 1
        var r1 = sol.solution(4, 3, new int[][]{{2, 2}});
        Test.check("r1", r1, 4);

// 최소 크기 격자 - 웅덩이가 없는 경우
        var r2 = sol.solution(2, 2, new int[][]{});
        Test.check("r2", r2, 2);

// 최소 크기 격자 - 아래쪽에 웅덩이가 있는 경우
        var r3 = sol.solution(2, 2, new int[][]{{1, 2}});
        Test.check("r3", r3, 1);

// 최소 크기 격자 - 오른쪽에 웅덩이가 있는 경우
        var r4 = sol.solution(2, 2, new int[][]{{2, 1}});
        Test.check("r4", r4, 1);

// 가로 한 줄로만 이루어진 격자
        var r5 = sol.solution(6, 1, new int[][]{});
        Test.check("r5", r5, 1);

// 세로 한 줄로만 이루어진 격자
        var r6 = sol.solution(1, 6, new int[][]{});
        Test.check("r6", r6, 1);

// 가로 한 줄에서 경로가 막힌 경우
        var r7 = sol.solution(6, 1, new int[][]{{4, 1}});
        Test.check("r7", r7, 0);

// 세로 한 줄에서 경로가 막힌 경우
        var r8 = sol.solution(1, 6, new int[][]{{1, 3}});
        Test.check("r8", r8, 0);

// 3x3 격자에 웅덩이가 없는 경우
        var r9 = sol.solution(3, 3, new int[][]{});
        Test.check("r9", r9, 6);

// 3x3 격자 중앙에 웅덩이가 있는 경우
        var r10 = sol.solution(3, 3, new int[][]{{2, 2}});
        Test.check("r10", r10, 2);

// 출발점에서 이동할 수 있는 두 방향이 모두 막힌 경우
        var r11 = sol.solution(3, 3, new int[][]{{2, 1}, {1, 2}});
        Test.check("r11", r11, 0);

// 도착점으로 진입할 수 있는 두 방향이 모두 막힌 경우
        var r12 = sol.solution(3, 3, new int[][]{{2, 3}, {3, 2}});
        Test.check("r12", r12, 0);

// 가로와 세로 크기가 다르고 한쪽 가장자리에 웅덩이가 있는 경우
        var r13 = sol.solution(4, 3, new int[][]{{3, 1}});
        Test.check("r13", r13, 7);

// 두 개의 웅덩이를 우회해야 하는 경우
        var r14 = sol.solution(5, 4, new int[][]{{2, 2}, {4, 3}});
        Test.check("r14", r14, 7);

// 서로 다른 위치의 웅덩이를 우회하는 경우
        var r15 = sol.solution(4, 5, new int[][]{{2, 4}, {4, 2}});
        Test.check("r15", r15, 19);

// L자 형태로 웅덩이가 배치된 경우
        var r16 = sol.solution(5, 5, new int[][]{{2, 2}, {2, 3}, {3, 2}});
        Test.check("r16", r16, 10);

// 특정 열의 대부분이 막혀 경로가 하나만 남는 경우
        var r17 = sol.solution(6, 5, new int[][]{{2, 1}, {2, 2}, {2, 3}, {2, 4}});
        Test.check("r17", r17, 1);

// 대각선 방향으로 8개의 웅덩이가 배치된 경우
        var r18 = sol.solution(10, 10, new int[][]{{2, 2}, {3, 3}, {4, 4}, {5, 5}, {6, 6}, {7, 7}, {8, 8}, {9, 9}});
        Test.check("r18", r18, 2860);

// 최대 크기 격자 - 웅덩이가 없는 경우
        Supplier<Integer> r19 = () -> sol.solution(100, 100, new int[][]{});
        Test.check("r19", r19, 690285631);

// 최대 크기 격자 - 웅덩이 10개가 존재하는 경우
        Supplier<Integer> r20 = () -> sol.solution(100, 100, new int[][]{{2, 2}, {3, 5}, {5, 3}, {10, 10}, {25, 40}, {40, 25}, {50, 50}, {60, 70}, {70, 60}, {99, 99}});
        Test.check("r20", r20, 859906741);
    }
}
