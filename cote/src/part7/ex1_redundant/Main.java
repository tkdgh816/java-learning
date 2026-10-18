package part7.ex1_redundant;

// 수업자료 53페이지: Redundant Connection
// https://leetcode.com/problems/redundant-connection/description/

import helpers.Test;

class Solution {
    public int[] findRedundantConnection(int[][] edges) {
        DSU dsu = new DSU(edges.length);

        for (int[] edge : edges) {
            if (!dsu.union(edge[0], edge[1])) {
                return edge;
            }
        }

        throw new IllegalStateException();
    }
}

class DSU {
    private final int[] parent;
    private final int[] size;

    DSU(int n) {
        parent = new int[n + 1];
        size = new int[n + 1];

        for (int i = 1; i <= n; i++) {
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
}

public class Main {
    static void main(String[] args) {
        Solution sol = new Solution();

// 공식 예제 1
        var r1 = sol.findRedundantConnection(new int[][]{{1, 2}, {1, 3}, {2, 3}});
        Test.check("r1", r1, new int[]{2, 3});

// 공식 예제 2
        var r2 = sol.findRedundantConnection(new int[][]{{1, 2}, {2, 3}, {3, 4}, {1, 4}, {1, 5}});
        Test.check("r2", r2, new int[]{1, 4});

// 삼각형 - 간선 입력 순서 변경
        var r3 = sol.findRedundantConnection(new int[][]{{2, 3}, {1, 3}, {1, 2}});
        Test.check("r3", r3, new int[]{1, 2});

// 4개 노드로 구성된 사이클
        var r4 = sol.findRedundantConnection(new int[][]{{1, 2}, {2, 3}, {3, 4}, {1, 4}});
        Test.check("r4", r4, new int[]{1, 4});

// 사이클 완성 이후 다른 간선이 추가되는 경우
        var r5 = sol.findRedundantConnection(new int[][]{{1, 2}, {2, 3}, {1, 3}, {3, 4}, {4, 5}});
        Test.check("r5", r5, new int[]{1, 3});

// 긴 경로의 중간에서 사이클이 발생하는 경우
        var r6 = sol.findRedundantConnection(new int[][]{{1, 2}, {2, 3}, {3, 4}, {4, 5}, {2, 5}, {5, 6}, {6, 7}});
        Test.check("r6", r6, new int[]{2, 5});

// 사이클이 이미 존재하고 마지막에 컴포넌트가 연결되는 경우
        var r7 = sol.findRedundantConnection(new int[][]{{1, 2}, {2, 3}, {1, 3}, {4, 5}, {5, 6}, {3, 4}});
        Test.check("r7", r7, new int[]{1, 3});

// 모든 노드가 하나의 사이클을 이루는 경우
        var r8 = sol.findRedundantConnection(new int[][]{{1, 2}, {2, 3}, {3, 4}, {4, 5}, {5, 6}, {1, 6}});
        Test.check("r8", r8, new int[]{1, 6});

// 여러 갈래로 분기한 경로가 연결되면서 사이클이 생기는 경우
        var r9 = sol.findRedundantConnection(new int[][]{{1, 2}, {1, 3}, {2, 4}, {3, 5}, {4, 5}, {5, 6}});
        Test.check("r9", r9, new int[]{4, 5});

// 서로 떨어진 컴포넌트들이 마지막 간선으로 연결되는 경우
        var r10 = sol.findRedundantConnection(new int[][]{{1, 2}, {1, 3}, {2, 3}, {4, 5}, {5, 6}, {6, 7}, {3, 4}});
        Test.check("r10", r10, new int[]{2, 3});

// 노드 번호 순서와 경로 순서가 다른 경우
        var r11 = sol.findRedundantConnection(new int[][]{{3, 4}, {2, 4}, {1, 2}, {1, 5}, {3, 5}});
        Test.check("r11", r11, new int[]{3, 5});

// 첫 번째 간선이 사이클의 일부인 경우
        var r12 = sol.findRedundantConnection(new int[][]{{1, 5}, {1, 2}, {2, 3}, {3, 4}, {4, 5}});
        Test.check("r12", r12, new int[]{4, 5});

// 높은 번호의 노드들 사이에서 먼저 사이클이 발생하는 경우
        var r13 = sol.findRedundantConnection(new int[][]{{4, 5}, {3, 4}, {3, 5}, {1, 2}, {2, 3}});
        Test.check("r13", r13, new int[]{3, 5});

// 사이클에 속하지 않는 가지가 여러 개 있는 경우
        var r14 = sol.findRedundantConnection(new int[][]{{1, 4}, {2, 4}, {2, 3}, {3, 4}, {4, 5}, {5, 6}});
        Test.check("r14", r14, new int[]{3, 4});

// 10개 노드가 하나의 큰 사이클을 이루는 경우
        var r15 = sol.findRedundantConnection(new int[][]{{1, 2}, {2, 3}, {3, 4}, {4, 5}, {5, 6}, {6, 7}, {7, 8}, {8, 9}, {9, 10}, {1, 10}});
        Test.check("r15", r15, new int[]{1, 10});

// 앞부분에서 사이클이 발생하고 뒷부분에는 긴 경로가 이어지는 경우
        var r16 = sol.findRedundantConnection(new int[][]{{1, 2}, {2, 3}, {3, 4}, {1, 4}, {4, 5}, {5, 6}, {6, 7}, {7, 8}, {8, 9}, {9, 10}});
        Test.check("r16", r16, new int[]{1, 4});

// 별 모양 그래프에 간선 하나가 추가되는 경우
        var r17 = sol.findRedundantConnection(new int[][]{{1, 2}, {1, 3}, {1, 4}, {1, 5}, {1, 6}, {1, 7}, {1, 8}, {1, 9}, {8, 9}});
        Test.check("r17", r17, new int[]{8, 9});
    }
}