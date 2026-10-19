package part8.drill.bellmanford;

import java.util.Arrays;
import java.util.List;

public class Main {
    static void main(String[] args) {
        List<Edge> edges = List.of(
                new Edge(0, 1, 4),
                new Edge(0, 2, 5),
                new Edge(1, 2, -2),
                new Edge(2, 3, 3),
                new Edge(1, 3, 4),
                new Edge(3, 4, -1),
                new Edge(5, 4, -100)
        );

        BellmanFordTemplate bf = new BellmanFord();
        System.out.println(Arrays.toString(bf.bellmanFord(6, edges, 0)));   // [0, 4, 2, 5, 4, INF]
    }
}

class BellmanFord implements BellmanFordTemplate {
    static final long INF = Long.MAX_VALUE;

    @Override
    public long[] bellmanFord(int n, List<Edge> edges, int start) {
        long[] distances = new long[n];
        Arrays.fill(distances, INF);
        distances[start] = 0;

        for (int i = 0; i < n; i++) {
            boolean updated = false;

            for (Edge edge : edges) {
                if (distances[edge.from()] == INF) {
                    continue;
                }

                long distance = distances[edge.from()] + edge.weight();

                if (distance >= distances[edge.to()]) {
                    continue;
                }
                distances[edge.to()] = distance;
                updated = true;

                if (i == n - 1) {
                    throw new IllegalStateException("음수 사이클 존재");
                }
            }

            if (!updated) {
                break;
            }
        }

        return distances;
    }
}