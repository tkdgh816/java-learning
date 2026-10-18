package part8.ex1_longest_node;

// 수업자료 43페이지: 가장 먼 노드
// https://school.programmers.co.kr/learn/courses/30/lessons/49189

import helpers.Test;

import java.util.*;
import java.util.function.Supplier;

class Solution {
    public int solution(int n, int[][] edge) {
        List<List<Integer>> graph = new ArrayList<>(n + 1);
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int[] e : edge) {
            graph.get(e[0]).add(e[1]);
            graph.get(e[1]).add(e[0]);
        }

        // bfs로 1이 시작점인 최단 경로 배열 만들기
        int[] distances = new int[n + 1];
        Arrays.fill(distances, -1);
        distances[1] = 0;

        Queue<Integer> queue = new ArrayDeque<>();
        queue.offer(1);

        while (!queue.isEmpty()) {
            int current = queue.poll();

            for (int next : graph.get(current)) {
                if (distances[next] != -1) {
                    continue;
                }

                distances[next] = distances[current] + 1;
                queue.offer(next);
            }

        }

        // 최대 거리의 노드 개수 구하기
        int max = 0;
        int count = 0;

        for (int i = 0; i <= n; i++) {
            if (distances[i] > max) {
                max = distances[i];
                count = 1;
            } else if (distances[i] == max) {
                count++;
            }
        }

        return count;
    }
}

public class Main {
    static void main(String[] args) {
        Solution sol = new Solution();

// 공식 예제 1
        var r1 = sol.solution(6, new int[][]{{3, 6}, {4, 3}, {3, 2}, {1, 3}, {1, 2}, {2, 4}, {5, 2}});
        Test.check("r1", r1, 3);

// 최소 노드 개수
        var r2 = sol.solution(2, new int[][]{{1, 2}});
        Test.check("r2", r2, 1);

// 3개의 노드가 일자로 연결된 경우
        var r3 = sol.solution(3, new int[][]{{1, 2}, {2, 3}});
        Test.check("r3", r3, 1);

// 1번 노드를 중심으로 모든 노드가 연결된 별 모양 그래프
        var r4 = sol.solution(5, new int[][]{{1, 2}, {1, 3}, {1, 4}, {1, 5}});
        Test.check("r4", r4, 4);

// 모든 노드가 일자로 연결된 경우
        var r5 = sol.solution(5, new int[][]{{1, 2}, {2, 3}, {3, 4}, {4, 5}});
        Test.check("r5", r5, 1);

// 가장 먼 노드가 3개인 분기형 그래프
        var r6 = sol.solution(6, new int[][]{{1, 2}, {1, 3}, {2, 4}, {3, 5}, {3, 6}});
        Test.check("r6", r6, 3);

// 시작 부분에 사이클이 있고 뒤에 긴 경로가 이어지는 경우
        var r7 = sol.solution(6, new int[][]{{1, 2}, {2, 3}, {3, 1}, {3, 4}, {4, 5}, {5, 6}});
        Test.check("r7", r7, 1);

// 6개 노드가 하나의 사이클을 이루는 경우
        var r8 = sol.solution(6, new int[][]{{1, 2}, {2, 3}, {3, 4}, {4, 5}, {5, 6}, {6, 1}});
        Test.check("r8", r8, 1);

// 7개 노드가 하나의 사이클을 이루는 경우
        var r9 = sol.solution(7, new int[][]{{1, 2}, {2, 3}, {3, 4}, {4, 5}, {5, 6}, {6, 7}, {7, 1}});
        Test.check("r9", r9, 2);

// 여러 갈래의 트리에서 가장 깊은 노드가 2개인 경우
        var r10 = sol.solution(8, new int[][]{{1, 2}, {1, 3}, {2, 4}, {2, 5}, {3, 6}, {6, 7}, {6, 8}});
        Test.check("r10", r10, 2);

// 긴 경로에 지름길이 추가되어 최단 거리가 짧아지는 경우
        var r11 = sol.solution(6, new int[][]{{1, 2}, {2, 3}, {3, 4}, {4, 5}, {5, 6}, {1, 5}});
        Test.check("r11", r11, 3);

// 동일한 노드까지 여러 최단 경로가 존재하는 경우
        var r12 = sol.solution(7, new int[][]{{1, 2}, {1, 3}, {2, 4}, {3, 4}, {4, 5}, {5, 6}, {5, 7}});
        Test.check("r12", r12, 2);

// 간선 입력 순서가 뒤섞여 있는 경우
        var r13 = sol.solution(8, new int[][]{{7, 8}, {2, 5}, {5, 6}, {1, 3}, {3, 4}, {4, 7}, {6, 7}, {1, 2}});
        Test.check("r13", r13, 1);

// 서로 다른 분기에서 같은 최대 거리를 가지는 경우
        var r14 = sol.solution(8, new int[][]{{1, 2}, {2, 3}, {3, 4}, {1, 5}, {5, 6}, {6, 7}, {1, 8}});
        Test.check("r14", r14, 2);

// 여러 깊이의 분기가 존재하는 트리
        var r15 = sol.solution(10, new int[][]{{1, 2}, {1, 3}, {2, 4}, {2, 5}, {3, 6}, {3, 7}, {4, 8}, {5, 9}, {7, 10}});
        Test.check("r15", r15, 3);

// 모든 노드가 서로 연결된 완전 그래프
        var r16 = sol.solution(6, new int[][]{{1, 2}, {1, 3}, {1, 4}, {1, 5}, {1, 6}, {2, 3}, {2, 4}, {2, 5}, {2, 6}, {3, 4}, {3, 5}, {3, 6}, {4, 5}, {4, 6}, {5, 6}});
        Test.check("r16", r16, 5);

// 긴 경로 중간에 지름길이 추가되는 경우
        var r17 = sol.solution(8, new int[][]{{1, 2}, {2, 3}, {3, 4}, {4, 5}, {5, 6}, {6, 7}, {7, 8}, {4, 7}});
        Test.check("r17", r17, 2);

// 여러 경로가 합쳐졌다가 다시 분기하는 경우
        var r18 = sol.solution(10, new int[][]{{1, 2}, {1, 3}, {1, 4}, {2, 5}, {3, 5}, {4, 5}, {5, 6}, {5, 7}, {6, 8}, {7, 9}, {8, 10}, {9, 10}});
        Test.check("r18", r18, 1);

// 최대 노드 개수 - 별 모양 그래프
        int[][] edges19 = new int[19999][2];
        for (int i = 2; i <= 20000; i++) {
            edges19[i - 2] = new int[]{1, i};
        }
        Supplier<Integer> r19 = () -> sol.solution(20000, edges19);
        Test.check("r19", r19, 19999);

// 최대 노드 개수 - 일자형 그래프
        int[][] edges20 = new int[19999][2];
        for (int i = 1; i < 20000; i++) {
            edges20[i - 1] = new int[]{i, i + 1};
        }
        Supplier<Integer> r20 = () -> sol.solution(20000, edges20);
        Test.check("r20", r20, 1);
    }
}
