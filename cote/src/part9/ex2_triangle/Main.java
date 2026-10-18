package part9.ex2_triangle;

// 수업자료 37페이지: 정수 삼각형
// https://school.programmers.co.kr/learn/courses/30/lessons/43105

// i행 j번째 위치한 요소(값 e[i][j])에 도착했을 때까지 거쳐간 숫자들의 합의 최댓값을 a[i][j]라고 하면
// a[i][j] = MAX(a[i-1][j-1], a[i-1][j]) + e[i][j]

import helpers.Test;

import java.util.Arrays;

class Solution {
    public int solution(int[][] triangle) {
        int n = triangle.length;

        for (int i = 1; i < n; i++) {
            // 양 끝단
            triangle[i][0] += triangle[i - 1][0];
            triangle[i][i] += triangle[i - 1][i - 1];

            // 중간
            for (int j = 1; j < i; j++) {
                triangle[i][j] += Math.max(triangle[i - 1][j - 1], triangle[i - 1][j]);
            }
        }

        return Arrays.stream(triangle[n - 1]).max().orElseThrow();
    }
}

public class Main {
    static void main(String[] args) {
        Solution sol = new Solution();

// 공식 예제 1
        var r1 = sol.solution(new int[][]{
                {7},
                {3, 8},
                {8, 1, 0},
                {2, 7, 4, 4},
                {4, 5, 2, 6, 5}
        });
        Test.check("r1", r1, 30);

// 높이가 1인 삼각형
        var r2 = sol.solution(new int[][]{
                {5}
        });
        Test.check("r2", r2, 5);

// 높이가 1이고 값이 0인 경우
        var r3 = sol.solution(new int[][]{
                {0}
        });
        Test.check("r3", r3, 0);

// 높이가 2인 삼각형
        var r4 = sol.solution(new int[][]{
                {3},
                {4, 7}
        });
        Test.check("r4", r4, 10);

// 모든 행의 값이 증가하는 삼각형
        var r5 = sol.solution(new int[][]{
                {1},
                {2, 3},
                {4, 5, 6}
        });
        Test.check("r5", r5, 10);

// 모든 값이 0인 경우
        var r6 = sol.solution(new int[][]{
                {0},
                {0, 0},
                {0, 0, 0},
                {0, 0, 0, 0}
        });
        Test.check("r6", r6, 0);

// 모든 값이 1인 경우
        var r7 = sol.solution(new int[][]{
                {1},
                {1, 1},
                {1, 1, 1},
                {1, 1, 1, 1},
                {1, 1, 1, 1, 1}
        });
        Test.check("r7", r7, 5);

// 가장 왼쪽 경로가 최적인 경우
        var r8 = sol.solution(new int[][]{
                {9},
                {8, 1},
                {7, 0, 1},
                {6, 0, 0, 2}
        });
        Test.check("r8", r8, 30);

// 가장 오른쪽 경로가 최적인 경우
        var r9 = sol.solution(new int[][]{
                {4},
                {1, 9},
                {2, 1, 8},
                {3, 4, 5, 7}
        });
        Test.check("r9", r9, 28);

// 탐욕적으로 큰 숫자를 선택하면 실패하는 경우
        var r10 = sol.solution(new int[][]{
                {5},
                {9, 1},
                {1, 1, 100}
        });
        Test.check("r10", r10, 106);

// 두 부모 경로 중 더 큰 누적합을 선택해야 하는 경우
        var r11 = sol.solution(new int[][]{
                {7},
                {1, 8},
                {2, 20, 1},
                {1, 1, 1, 1}
        });
        Test.check("r11", r11, 36);

// 중간에 큰 값이 있고 나머지는 0인 경우
        var r12 = sol.solution(new int[][]{
                {5},
                {0, 0},
                {0, 100, 0},
                {0, 0, 0, 0}
        });
        Test.check("r12", r12, 105);

// 마지막 행의 가운데에서 최대 합이 결정되는 경우
        var r13 = sol.solution(new int[][]{
                {5},
                {8, 7},
                {1, 20, 1},
                {1, 1, 50, 1},
                {1, 1, 1, 1, 1}
        });
        Test.check("r13", r13, 84);

// 서로 다른 경로의 큰 숫자를 동시에 선택할 수 없는 경우
        var r14 = sol.solution(new int[][]{
                {1},
                {100, 1},
                {1, 1, 100},
                {1, 1, 1, 1}
        });
        Test.check("r14", r14, 103);

// 다양한 값이 섞인 6단계 삼각형
        var r15 = sol.solution(new int[][]{
                {6},
                {3, 8},
                {5, 2, 4},
                {1, 9, 7, 2},
                {8, 1, 3, 9, 5},
                {2, 7, 4, 1, 6, 8}
        });
        Test.check("r15", r15, 40);

// 마지막 단계의 선택에 따라 최적 경로가 달라지는 경우
        var r16 = sol.solution(new int[][]{
                {2},
                {3, 4},
                {99, 1, 1},
                {5, 100, 5, 1}
        });
        Test.check("r16", r16, 204);

// 최대 합을 만드는 경로가 여러 개인 경우
        var r17 = sol.solution(new int[][]{
                {4},
                {4, 4},
                {4, 4, 4},
                {4, 4, 4, 4}
        });
        Test.check("r17", r17, 16);

// 모든 값이 최댓값인 삼각형
        var r18 = sol.solution(new int[][]{
                {9999},
                {9999, 9999},
                {9999, 9999, 9999},
                {9999, 9999, 9999, 9999},
                {9999, 9999, 9999, 9999, 9999},
                {9999, 9999, 9999, 9999, 9999, 9999}
        });
        Test.check("r18", r18, 59994);

// 최대 높이 500 - 모든 값이 9999인 경우
        int[][] triangle19 = new int[500][];
        for (int i = 0; i < 500; i++) {
            triangle19[i] = new int[i + 1];
            Arrays.fill(triangle19[i], 9999);
        }
        var r19 = sol.solution(triangle19);
        Test.check("r19", r19, 4999500);

// 최대 높이 500 - 다양한 값이 반복되는 경우
        int[][] triangle20 = new int[500][];
        for (int i = 0; i < 500; i++) {
            triangle20[i] = new int[i + 1];

            for (int j = 0; j <= i; j++) {
                triangle20[i][j] = (i * 31 + j * 17) % 10000;
            }
        }
        var r20 = sol.solution(triangle20);
        Test.check("r20", r20, 2766503);
    }
}
