package part7.drill.topological_sort;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class Main {
    static void main(String[] args) {
        int n = 7;

        int[][] edges = {
                {0, 3}, {0, 4},
                {1, 3}, {1, 5},
                {3, 2}, {4, 2},
                {5, 6}, {2, 6},
                {4, 5}
        };

        List<List<Integer>> graph = new ArrayList<>();
        int[] indegree = new int[n];

        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int[] edge : edges) {
            int from = edge[0];
            int to = edge[1];

            graph.get(from).add(to);
            indegree[to]++;
        }

        TopologicalSort ts = new TopologicalSort();
        List<Integer> result = ts.topologicalSort(graph, indegree);
        System.out.println(result);
    }
}

class TopologicalSort implements TopologicalSortTemplate {
    @Override
    public List<Integer> topologicalSort(List<List<Integer>> graph, int[] indegree) {
        List<Integer> result = new ArrayList<>();

        Queue<Integer> queue = new ArrayDeque<>();
        for (int i = 0; i < graph.size(); i++) {
            if (indegree[i] == 0) {
                queue.offer(i);
            }
        }

        while (!queue.isEmpty()) {
            int v = queue.poll();
            result.add(v);

            for (int c : graph.get(v)) {
                if (--indegree[c] == 0) {
                    queue.offer(c);
                }
            }
        }

        if (result.size() != graph.size()) {
            return List.of();
        }

        return result;
    }
}