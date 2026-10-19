package part7.ex3_min_cost;

// 수업자료 55페이지: Min Cost to Connect All Points
// https://leetcode.com/problems/min-cost-to-connect-all-points/description/

import helpers.Test;

import java.util.*;
import java.util.function.Supplier;

// Prim
class Solution1 {
    public int minCostConnectPoints(int[][] points) {
        int n = points.length;
        boolean[] visited = new boolean[n];
        int totalCost = 0;

        PriorityQueue<Edge> pq = new PriorityQueue<>(Comparator.comparingInt(Edge::cost));
        pq.offer(new Edge(0, 0));

        int count =0;
        while (!pq.isEmpty() && count < n) {
            Edge current = pq.poll();
            if (visited[current.to()]) {
                continue;
            }

            visited[current.to()] = true;
            totalCost += current.cost;
            count++;

            for (int i = 0; i < n; i++) {
                if (visited[i]) {
                    continue;
                }
                Edge next = new Edge(i, calculateCost(points[current.to()], points[i]));
                pq.offer(next);
            }
        }

        return totalCost;
    }

    private int calculateCost(int[] point1, int[] point2) {
        return Math.abs(point2[0] - point1[0]) + Math.abs(point2[1] - point1[1]);
    }

    private record Edge(int to, int cost) {
    }
}

// Kruskal
class Solution2 {
    public int minCostConnectPoints(int[][] points) {
        int n = points.length;
        List<Edge> edges = new ArrayList<>();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 1; j < n; j++) {
                edges.add(new Edge(i, j, calculateCost(points[i], points[j])));
            }
        }

        edges.sort(Comparator.comparingInt(Edge::cost));

        DSU dsu = new DSU(n);
        int totalCost = 0;

        for (Edge edge : edges) {
            if (!dsu.union(edge.u(), edge.v())) {
                continue;
            }

            totalCost += edge.cost;
        }

        return totalCost;
    }

    private int calculateCost(int[] point1, int[] point2) {
        return Math.abs(point2[0] - point1[0]) + Math.abs(point2[1] - point1[1]);
    }

    private record Edge(int u, int v, int cost) {
    }

    private static class DSU {
        private final int[] parent;
        private final int[] size;

        DSU(int n) {
            parent = new int[n];
            size = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                size[i] = 1;
            }
        }

        public int find(int x) {
            if (parent[x] == x) {
                return x;
            }

            return parent[x] = find(parent[x]);
        }

        public boolean union(int a, int b) {
            int rootA = find(a), rootB = find(b);

            if (rootA == rootB) {
                return false;
            }

            if (size[rootA] < size[rootB]) {
                int temp = rootA;
                rootA = rootB;
                rootB = temp;
            }

            parent[rootB] = rootA;
            size[rootA] += size[rootB];

            return true;
        }

        public boolean connected(int a, int b) {
            return find(a) == find(b);
        }
    }
}

public class Main {
    static void main(String[] args) {
        Solution1 sol = new Solution1();
//        Solution2 sol = new Solution2();

// 1. 기본 예제 1
        var r1 = sol.minCostConnectPoints(new int[][]{{0, 0}, {2, 2}, {3, 10}, {5, 2}, {7, 0}});
        Test.check("r1", r1, 20);

// 2. 기본 예제 2
        var r2 = sol.minCostConnectPoints(new int[][]{{3, 12}, {-2, 5}, {-4, 1}});
        Test.check("r2", r2, 18);

// 3. 점이 하나만 존재하는 경우
        var r3 = sol.minCostConnectPoints(new int[][]{{0, 0}});
        Test.check("r3", r3, 0);

// 4. 점이 두 개만 존재하는 경우
        var r4 = sol.minCostConnectPoints(new int[][]{{0, 0}, {3, 4}});
        Test.check("r4", r4, 7);

// 5. 모든 점이 x축에 일렬로 배치된 경우
        var r5 = sol.minCostConnectPoints(new int[][]{{0, 0}, {2, 0}, {5, 0}, {9, 0}});
        Test.check("r5", r5, 9);

// 6. 모든 점이 y축에 일렬로 배치된 경우
        var r6 = sol.minCostConnectPoints(new int[][]{{0, -5}, {0, -1}, {0, 2}, {0, 10}});
        Test.check("r6", r6, 15);

// 7. 정사각형의 네 꼭짓점
        var r7 = sol.minCostConnectPoints(new int[][]{{0, 0}, {0, 2}, {2, 0}, {2, 2}});
        Test.check("r7", r7, 6);

// 8. 음수와 양수가 섞인 대각선 배치
        var r8 = sol.minCostConnectPoints(new int[][]{{-3, -3}, {-1, -1}, {2, 2}, {4, 4}});
        Test.check("r8", r8, 14);

// 9. 중앙의 점을 경유하는 연결이 유리한 경우
        var r9 = sol.minCostConnectPoints(new int[][]{{1, 1}, {1, 4}, {4, 1}, {4, 4}, {2, 2}});
        Test.check("r9", r9, 11);

// 10. 좌표가 최대 범위에 위치한 두 점
        var r10 = sol.minCostConnectPoints(new int[][]{{-1000000, -1000000}, {1000000, 1000000}});
        Test.check("r10", r10, 4000000);

// 11. 모든 간선의 맨해튼 거리가 동일한 삼각형
        var r11 = sol.minCostConnectPoints(new int[][]{{0, 0}, {1, 1}, {2, 0}});
        Test.check("r11", r11, 4);

// 12. 3x3 격자 형태로 배치된 경우
        var r12 = sol.minCostConnectPoints(new int[][]{{0, 0}, {0, 1}, {0, 2}, {1, 0}, {1, 1}, {1, 2}, {2, 0}, {2, 1}, {2, 2}});
        Test.check("r12", r12, 8);

// 13. 양수와 음수 좌표가 불규칙하게 분포한 경우
        var r13 = sol.minCostConnectPoints(new int[][]{{-2, -3}, {-1, 4}, {3, -1}, {5, 3}, {0, 0}});
        Test.check("r13", r13, 20);

// 14. 멀리 떨어진 두 그룹을 연결해야 하는 경우
        var r14 = sol.minCostConnectPoints(new int[][]{{0, 0}, {1, 0}, {2, 0}, {100, 0}, {101, 0}, {102, 0}});
        Test.check("r14", r14, 102);

// 15. 더 짧은 연결 간선을 반복해서 갱신해야 하는 경우
        var r15 = sol.minCostConnectPoints(new int[][]{{0, 0}, {0, 10}, {1, 1}, {1, 9}, {2, 5}});
        Test.check("r15", r15, 14);

// 16. 중앙의 점에서 네 꼭짓점으로 연결하는 경우
        var r16 = sol.minCostConnectPoints(new int[][]{{0, 0}, {2, 0}, {0, 2}, {2, 2}, {1, 1}});
        Test.check("r16", r16, 8);

// 17. 좌표가 큰 점 하나만 존재하는 경우
        var r17 = sol.minCostConnectPoints(new int[][]{{1000000, -1000000}});
        Test.check("r17", r17, 0);

// 18. 최대 범위의 좌표가 십자 형태로 배치된 경우
        var r18 = sol.minCostConnectPoints(new int[][]{{-1000000, 0}, {1000000, 0}, {0, -1000000}, {0, 1000000}, {0, 0}});
        Test.check("r18", r18, 4000000);

// 정점 수가 많을 때 (그래프에서 모든 두 정점은 이어져 있어야 하므로 Kruskal, Prim의 시간 비교)

// 19. 정점 100개가 x축에 일정한 간격으로 배치된 경우
        var points19 = new int[100][2];
        for (int i = 0; i < 100; i++) points19[i] = new int[]{i * 3, 0};
        Supplier<Integer> r19 = () -> sol.minCostConnectPoints(points19);
        Test.check("r19", r19, 297);

// 20. 정점 500개가 y축에 일정한 간격으로 배치된 경우
        var points20 = new int[500][2];
        for (int i = 0; i < 500; i++) points20[i] = new int[]{0, i * 2};
        Supplier<Integer> r20 = () -> sol.minCostConnectPoints(points20);
        Test.check("r20", r20, 998);

// 21. 정점 999개가 음수와 양수 좌표에 걸쳐 배치된 경우
        var points21 = new int[999][2];
        for (int i = 0; i < 999; i++) points21[i] = new int[]{0, i - 499};
        Supplier<Integer> r21 = () -> sol.minCostConnectPoints(points21);
        Test.check("r21", r21, 998);

// 22. 최대 정점 1000개가 대각선으로 배치된 경우
        var points22 = new int[1000][2];
        for (int i = 0; i < 1000; i++) points22[i] = new int[]{i, i};
        Supplier<Integer> r22 = () -> sol.minCostConnectPoints(points22);
        Test.check("r22", r22, 1998);

// 23. 최대 정점 1000개가 25x40 격자로 배치된 경우
        var points23 = new int[1000][2];
        for (int i = 0; i < 1000; i++) points23[i] = new int[]{i % 40, i / 40};
        Supplier<Integer> r23 = () -> sol.minCostConnectPoints(points23);
        Test.check("r23", r23, 999);

// 24. 최대 정점 1000개가 가로와 세로 간격이 다른 격자로 배치된 경우
        var points24 = new int[1000][2];
        for (int i = 0; i < 1000; i++) points24[i] = new int[]{(i % 40) * 3, (i / 40) * 7};
        Supplier<Integer> r24 = () -> sol.minCostConnectPoints(points24);
        Test.check("r24", r24, 3093);

// 25. 최대 정점 1000개가 좌표 양 끝의 두 그룹으로 분리된 경우
        var points25 = new int[1000][2];
        for (int i = 0; i < 1000; i++) points25[i] = new int[]{i < 500 ? -1000000 + i : 999501 + i - 500, 0};
        Supplier<Integer> r25 = () -> sol.minCostConnectPoints(points25);
        Test.check("r25", r25, 2000000);

// 26. 최대 정점 1000개가 멀리 떨어진 두 수평선에 번갈아 배치된 경우
        var points26 = new int[1000][2];
        for (int i = 0; i < 1000; i++) points26[i] = new int[]{i, i % 2 == 0 ? 0 : 1000000};
        Supplier<Integer> r26 = () -> sol.minCostConnectPoints(points26);
        Test.check("r26", r26, 1001997);

// 27. 최대 정점 1000개가 입력 순서와 관계없이 x축에 무작위 순서로 배치된 경우
        var points27 = new int[1000][2];
        for (int i = 0; i < 1000; i++) points27[i] = new int[]{(i * 37) % 1000, 0};
        Supplier<Integer> r27 = () -> sol.minCostConnectPoints(points27);
        Test.check("r27", r27, 999);

// 28. 최대 정점 1000개가 2차원 평면에 불규칙하게 분포한 경우
        var points28 = new int[1000][2];
        for (int i = 0; i < 1000; i++) points28[i] = new int[]{(i * 37) % 1000, (i * 83) % 1000};
        Supplier<Integer> r28 = () -> sol.minCostConnectPoints(points28);
        Test.check("r28", r28, 39960);
    }
}
