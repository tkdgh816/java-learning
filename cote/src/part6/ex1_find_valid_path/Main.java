package part6.ex1_find_valid_path;

// 수업자료 60페이지: Find if Path Exists in Graph
// https://leetcode.com/problems/find-if-path-exists-in-graph/description/

import helpers.Test;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.function.Supplier;

// 인접 리스트 + DFS -> 최대 정점 수 2 * 10^5, 최대 간선 수 2 * 10^5 공간복잡도 O(V+E)
class Solution1 {
    public boolean validPath(int n, int[][] edges, int source, int destination) {
        // 인접 리스트 만들기
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }

        boolean[] visited = new boolean[n];
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(source);
        visited[source] = true;

        // DFS로 탐색
        // 탐색 중 목적지 발견하면 true 반환
        while (!stack.isEmpty()) {
            int node = stack.pop();
            if (node == destination) {
                return true;
            }

            for (int connected : graph.get(node)) {
                if (!visited[connected]) {
                    stack.push(connected);
                    visited[connected] = true;
                }
            }
        }

        return false;
    }
}

// Union-Find
class Solution2 {
    public boolean validPath(int n, int[][] edges, int source, int destination) {
        DSU dsu = new DSU(n);
        for (int[] edge : edges) {
            dsu.union(edge[0], edge[1]);
        }

        return dsu.connected(source, destination);
    }

    static class DSU {
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
            int rootA = find(a);
            int rootB = find(b);

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


// 인접 행렬 + DFS -> 최대 정점 수 2 * 10^5이고 공간복잡도 O(V^2)이므로 부적합
class Solution3 {
    public boolean validPath(int n, int[][] edges, int source, int destination) {
        // 인접 행렬 만들기
        boolean[][] graph = new boolean[n][n];
        for (int[] edge : edges) {
            graph[edge[0]][edge[1]] = true;
            graph[edge[1]][edge[0]] = true;
        }

        // DFS로 탐색
        // 탐색 중 목적지 발견하면 true 반환
        boolean[] visited = new boolean[n];
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(source);
        visited[source] = true;

        while (!stack.isEmpty()) {
            int node = stack.pop();
            if (node == destination) {
                return true;
            }

            for (int i = 0; i < n; i++) {
                if (graph[node][i] && !visited[i]) {
                    stack.push(i);
                    visited[i] = true;
                }
            }
        }

        return false;
    }
}

public class Main {
    static void main(String[] args) {
//        Solution1 sol = new Solution1();
        Solution2 sol = new Solution2();

// 1. 기본 예제: 직접 연결된 경로 존재
        var r1 = sol.validPath(3, new int[][]{{0, 1}, {1, 2}, {2, 0}}, 0, 2);
        Test.check("r1", r1, true);

// 2. 기본 예제: 서로 다른 연결 요소
        var r2 = sol.validPath(6, new int[][]{{0, 1}, {0, 2}, {3, 5}, {5, 4}, {4, 3}}, 0, 5);
        Test.check("r2", r2, false);

// 3. 정점이 하나이고 간선이 없는 경우
        var r3 = sol.validPath(1, new int[][]{}, 0, 0);
        Test.check("r3", r3, true);

// 4. 간선이 없지만 출발점과 도착점이 같은 경우
        var r4 = sol.validPath(5, new int[][]{}, 2, 2);
        Test.check("r4", r4, true);

// 5. 간선이 없고 출발점과 도착점이 다른 경우
        var r5 = sol.validPath(5, new int[][]{}, 0, 4);
        Test.check("r5", r5, false);

// 6. 두 정점이 직접 연결된 경우
        var r6 = sol.validPath(2, new int[][]{{0, 1}}, 0, 1);
        Test.check("r6", r6, true);

// 7. 무방향 그래프에서 간선 방향을 반대로 탐색
        var r7 = sol.validPath(2, new int[][]{{0, 1}}, 1, 0);
        Test.check("r7", r7, true);

// 8. 일직선으로 연결된 그래프
        var r8 = sol.validPath(6, new int[][]{{0, 1}, {1, 2}, {2, 3}, {3, 4}, {4, 5}}, 0, 5);
        Test.check("r8", r8, true);

// 9. 일직선 그래프에서 중간 연결이 끊어진 경우
        var r9 = sol.validPath(6, new int[][]{{0, 1}, {1, 2}, {3, 4}, {4, 5}}, 0, 5);
        Test.check("r9", r9, false);

// 10. 여러 경로 중 하나를 통해 도달할 수 있는 경우
        var r10 = sol.validPath(6, new int[][]{{0, 1}, {0, 2}, {1, 3}, {2, 4}, {4, 5}, {5, 3}}, 0, 3);
        Test.check("r10", r10, true);

// 11. 순환 구조가 포함된 그래프
        var r11 = sol.validPath(5, new int[][]{{0, 1}, {1, 2}, {2, 0}, {2, 3}, {3, 4}}, 0, 4);
        Test.check("r11", r11, true);

// 12. 서로 분리된 두 순환 그래프
        var r12 = sol.validPath(6, new int[][]{{0, 1}, {1, 2}, {2, 0}, {3, 4}, {4, 5}, {5, 3}}, 0, 5);
        Test.check("r12", r12, false);

// 13. 두 순환 그래프가 간선 하나로 연결된 경우
        var r13 = sol.validPath(6, new int[][]{{0, 1}, {1, 2}, {2, 0}, {3, 4}, {4, 5}, {5, 3}, {2, 3}}, 0, 5);
        Test.check("r13", r13, true);

// 14. 출발 정점이 고립된 경우
        var r14 = sol.validPath(5, new int[][]{{1, 2}, {2, 3}, {3, 4}}, 0, 4);
        Test.check("r14", r14, false);

// 15. 도착 정점이 고립된 경우
        var r15 = sol.validPath(5, new int[][]{{0, 1}, {1, 2}, {2, 3}}, 0, 4);
        Test.check("r15", r15, false);

// 16. 모든 정점이 하나의 중심 정점에 연결된 경우
        var r16 = sol.validPath(6, new int[][]{{0, 1}, {0, 2}, {0, 3}, {0, 4}, {0, 5}}, 1, 5);
        Test.check("r16", r16, true);

// 17. 여러 분기가 있지만 도착점에는 도달할 수 없는 경우
        var r17 = sol.validPath(8, new int[][]{{0, 1}, {0, 2}, {1, 3}, {2, 4}, {3, 5}, {4, 5}, {6, 7}}, 0, 7);
        Test.check("r17", r17, false);

// 18. 간선 입력 순서와 탐색 경로가 일치하지 않는 경우
        var r18 = sol.validPath(7, new int[][]{{5, 6}, {3, 4}, {1, 2}, {4, 5}, {0, 1}, {2, 3}}, 6, 0);
        Test.check("r18", r18, true);

// 19. 최대 크기의 일직선 그래프
        int n19 = 200_000;
        int[][] edges19 = new int[n19 - 1][2];

        for (int i = 0; i < n19 - 1; i++) {
            edges19[i] = new int[]{i, i + 1};
        }

        Supplier<Boolean> r19 = () -> sol.validPath(n19, edges19, 0, n19 - 1);
        Test.check("r19", r19, true);

// 20. 최대 크기에서 두 연결 요소로 분리된 그래프
        int n20 = 200_000;
        int[][] edges20 = new int[n20 - 2][2];

        int index = 0;

// 첫 번째 연결 요소: 0부터 99,999까지
        for (int i = 0; i < 99_999; i++) {
            edges20[index++] = new int[]{i, i + 1};
        }

// 두 번째 연결 요소: 100,000부터 199,999까지
        for (int i = 100_000; i < 199_999; i++) {
            edges20[index++] = new int[]{i, i + 1};
        }

        Supplier<Boolean> r20 = () -> sol.validPath(n20, edges20, 0, n20 - 1);
        Test.check("r20", r20, false);
    }
}
