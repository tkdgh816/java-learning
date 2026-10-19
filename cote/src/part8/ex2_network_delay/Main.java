package part8.ex2_network_delay;

// 수업자료 44페이지: Network Delay Time
// https://leetcode.com/problems/network-delay-time/description/

import helpers.Test;

import java.util.*;

// Bellman-Ford
class Solution1 {
    private static final int INF = Integer.MAX_VALUE;

    public int networkDelayTime(int[][] times, int n, int k) {
        int[] distances = new int[n + 1];
        Arrays.fill(distances, INF);
        distances[k] = 0;

        for (int i = 0; i < n; i++) {
            boolean updated = false;

            for (int[] time : times) {
                if (distances[time[0]] == INF) {
                    continue;
                }

                int distance = distances[time[0]] + time[2];
                if (distance >= distances[time[1]]) {
                    continue;
                }
                distances[time[1]] = distance;
                updated = true;

                if (i == n - 1) {
                    return -1;
                }
            }

            if (!updated) {
                break;
            }
        }

        int result = -1;
        for (int i = 1; i <= n; i++) {
            result = Math.max(result, distances[i]);
        }
        return result == INF ? -1 : result;
    }
}

// Dijkstra
class Solution2 {
    private static final int INF = Integer.MAX_VALUE;

    public int networkDelayTime(int[][] times, int n, int k) {
        // 인접 리스트 Edge(int[]): int[0] = to, int[1] = weight(time)
        List<List<int[]>> graph = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int[] time : times) {
            graph.get(time[0] - 1).add(new int[]{time[1] - 1, time[2]});
        }

        int start = k - 1;
        int[] distances = new int[n];
        Arrays.fill(distances, INF);
        distances[start] = 0;

        PriorityQueue<State> pq = new PriorityQueue<>(Comparator.comparing(State::distance));
        pq.offer(new State(start, distances[start]));

        while (!pq.isEmpty()) {
            State current = pq.poll();

            if (distances[current.node] < current.distance) {
                continue;
            }

            for (int[] edge : graph.get(current.node)) {
                int distance = distances[current.node] + edge[1];
                if (distances[edge[0]] <= distance) {
                    continue;
                }
                distances[edge[0]] = distance;
                pq.offer(new State(edge[0], distance));
            }
        }

        int max = Arrays.stream(distances).max().orElseThrow();
        return max == INF ? -1 : max;
    }

    private record State(int node, int distance) {

    }
}

public class Main {
    static void main(String[] args) {
//        Solution1 sol = new Solution1();
        Solution2 sol = new Solution2();

// 1. 기본 예제 1
        var r1 = sol.networkDelayTime(new int[][]{{2, 1, 1}, {2, 3, 1}, {3, 4, 1}}, 4, 2);
        Test.check("r1", r1, 2);

// 2. 기본 예제 2
        var r2 = sol.networkDelayTime(new int[][]{{1, 2, 1}}, 2, 1);
        Test.check("r2", r2, 1);

// 3. 기본 예제 3: 도달 불가능
        var r3 = sol.networkDelayTime(new int[][]{{1, 2, 1}}, 2, 2);
        Test.check("r3", r3, -1);

// 4. 직접 경로보다 우회 경로가 빠른 경우
        var r4 = sol.networkDelayTime(new int[][]{{1, 2, 2}, {1, 3, 8}, {2, 3, 2}, {3, 4, 1}, {2, 4, 10}}, 4, 1);
        Test.check("r4", r4, 5);

// 5. 일직선으로 연결된 그래프
        var r5 = sol.networkDelayTime(new int[][]{{1, 2, 5}, {2, 3, 5}, {3, 4, 5}, {4, 5, 5}}, 5, 1);
        Test.check("r5", r5, 20);

// 6. 사이클이 존재하는 그래프
        var r6 = sol.networkDelayTime(new int[][]{{1, 2, 1}, {2, 3, 1}, {3, 1, 1}, {3, 4, 2}}, 4, 1);
        Test.check("r6", r6, 4);

// 7. 연결되지 않은 컴포넌트가 존재
        var r7 = sol.networkDelayTime(new int[][]{{1, 2, 1}, {3, 4, 1}, {4, 3, 1}}, 4, 1);
        Test.check("r7", r7, -1);

// 8. 간접 경로가 더 짧은 경우
        var r8 = sol.networkDelayTime(new int[][]{{1, 2, 1}, {2, 3, 1}, {1, 3, 5}, {3, 4, 1}}, 4, 1);
        Test.check("r8", r8, 3);

// 9. 이미 발견한 노드의 최단 거리가 갱신되는 경우
        var r9 = sol.networkDelayTime(new int[][]{{1, 2, 10}, {1, 3, 1}, {3, 2, 1}, {2, 4, 1}, {3, 4, 20}}, 4, 1);
        Test.check("r9", r9, 3);

// 10. 여러 최단 경로의 거리가 동일한 경우
        var r10 = sol.networkDelayTime(new int[][]{{1, 2, 4}, {1, 3, 2}, {2, 4, 3}, {3, 4, 5}, {4, 5, 1}, {2, 5, 100}}, 5, 1);
        Test.check("r10", r10, 8);

// 11. 시작 노드가 중간 번호인 경우
        var r11 = sol.networkDelayTime(new int[][]{{3, 1, 3}, {3, 4, 1}, {4, 2, 2}, {2, 5, 7}, {1, 5, 2}}, 5, 3);
        Test.check("r11", r11, 5);

// 12. 최대 간선 가중치가 연속되는 경우
        var r12 = sol.networkDelayTime(new int[][]{{1, 2, 100}, {2, 3, 100}, {3, 4, 100}, {4, 5, 100}}, 5, 1);
        Test.check("r12", r12, 400);

// 13. 양방향 간선이 존재하고 마지막 노드에서 시작
        var r13 = sol.networkDelayTime(new int[][]{{1, 2, 1}, {2, 1, 1}, {2, 3, 2}, {3, 2, 2}, {3, 4, 3}, {4, 3, 3}}, 4, 4);
        Test.check("r13", r13, 6);

// 14. 시작 노드에서 다른 모든 노드로 직접 연결
        var r14 = sol.networkDelayTime(new int[][]{{1, 2, 1}, {1, 3, 1}, {1, 4, 1}, {1, 5, 1}, {1, 6, 1}}, 6, 1);
        Test.check("r14", r14, 1);

// 15. 우선순위 큐에 동일 노드의 여러 거리 정보가 쌓이는 경우
        var r15 = sol.networkDelayTime(new int[][]{{1, 2, 10}, {1, 3, 1}, {3, 2, 1}, {2, 4, 1}, {3, 4, 100}, {4, 5, 1}}, 5, 1);
        Test.check("r15", r15, 4);

// 16. 간선 방향을 반대로 해석하면 오답이 발생하는 경우
        var r16 = sol.networkDelayTime(new int[][]{{2, 1, 1}, {3, 1, 1}, {4, 1, 1}}, 4, 1);
        Test.check("r16", r16, -1);
    }
}
