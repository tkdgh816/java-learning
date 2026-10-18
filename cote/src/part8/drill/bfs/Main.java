package part8.drill.bfs;

import java.util.*;

public class Main {
    static void main(String[] args) {
        List<List<Integer>> graph = new ArrayList<>();

        for (int i = 0; i < 6; i++) {
            graph.add(new ArrayList<>());
        }

        graph.get(0).add(1);
        graph.get(0).add(2);
        graph.get(1).add(3);
        graph.get(3).add(4);
        graph.get(2).add(4);

        BFSTemplate b = new BFS();
        System.out.println(Arrays.toString(b.bfs(graph, 0)));   // [0, 1, 1, 2, 2, -1]
    }
}

class BFS implements BFSTemplate {
    @Override
    public int[] bfs(List<List<Integer>> graph, int start) {
        int[] distances = new int[graph.size()];
        Arrays.fill(distances, -1);

        Queue<Integer> queue = new ArrayDeque<>();
        queue.offer(start);
        distances[start] = 0;

        while (!queue.isEmpty()) {
            int current = queue.poll();

            for (var next : graph.get(current)) {
                if (distances[next] != -1) {
                    continue;
                }

                queue.offer(next);
                distances[next] = distances[current] + 1;
            }
        }

        return distances;
    }
}
