package part7.drill.mst;

import codingtest.hw1017.part7.drill.dsu.DSUTemplate;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class Main {
    static void main(String[] args) {
        int n = 6;

        List<KruskalEdge> kruskalEdges = new ArrayList<>(List.of(
                new KruskalEdge(0, 1, 4),
                new KruskalEdge(0, 2, 2),
                new KruskalEdge(1, 2, 1),
                new KruskalEdge(1, 3, 5),
                new KruskalEdge(2, 3, 8),
                new KruskalEdge(2, 4, 10),
                new KruskalEdge(3, 4, 2),
                new KruskalEdge(3, 5, 6),
                new KruskalEdge(4, 5, 3)
        ));

        List<List<PrimEdge>> primGraph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            primGraph.add(new ArrayList<>());
        }

        for (KruskalEdge edge : kruskalEdges) {
            primGraph.get(edge.from()).add(new PrimEdge(edge.to(), edge.weight()));
            primGraph.get(edge.to()).add(new PrimEdge(edge.from(), edge.weight()));
        }

        KruskalTemplate kr = new Kruskal();
        PrimTemplate pr = new Prim();

        System.out.println(kr.kruskal(n, kruskalEdges)); // 13
        System.out.println(pr.prim(primGraph, 0));  // 13
    }
}

class Kruskal implements KruskalTemplate {
    @Override
    public long kruskal(int n, List<KruskalEdge> edges) {
        edges.sort(Comparator.comparing(KruskalEdge::weight));
        DSU dsu = new DSU(n);

        long result = 0;
        int selected = 0;

        for (KruskalEdge edge : edges) {
            if (!dsu.union(edge.from(), edge.to())) {
                continue;
            }

            result += edge.weight();
            selected++;

            if (selected == n - 1) {
                break;
            }
        }

        if (selected != n - 1) {
            return -1;
        }

        return result;
    }

    private static class DSU implements DSUTemplate {
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

        @Override
        public int find(int x) {
            if (parent[x] == x) {
                return x;
            }

            return parent[x] = find(parent[x]);
        }

        @Override
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
            size[rootA] += rootB;

            return true;
        }

        @Override
        public boolean connected(int a, int b) {
            return find(a) == find(b);
        }
    }
}

class Prim implements PrimTemplate {
    @Override
    public long prim(List<List<PrimEdge>> graph, int start) {
        int result = 0;
        boolean[] visited = new boolean[graph.size()];
        int count = 0;

        PriorityQueue<PrimEdge> pq = new PriorityQueue<>(Comparator.comparing(PrimEdge::weight));
        pq.offer(new PrimEdge(start, 0));

        while (!pq.isEmpty()) {
            PrimEdge current = pq.poll();
            if (visited[current.to()]) {
                continue;
            }

            visited[current.to()] = true;
            result += current.weight();
            count++;

            for (PrimEdge edge : graph.get(current.to())) {
                if (!visited[edge.to()]) {
                    pq.offer(edge);
                }
            }
        }

        return count == graph.size() ? result : -1;
    }
}
