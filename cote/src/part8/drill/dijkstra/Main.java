package part8.drill.dijkstra;

import java.util.*;

public class Main {
    static void main(String[] args) {
        List<List<Edge>> graph = new ArrayList<>();

        for (int i = 0; i < 6; i++) {
            graph.add(new ArrayList<>());
        }

        graph.get(0).add(new Edge(1, 10));
        graph.get(0).add(new Edge(2, 3));
        graph.get(2).add(new Edge(1, 1));
        graph.get(1).add(new Edge(3, 2));
        graph.get(2).add(new Edge(3, 8));
        graph.get(2).add(new Edge(4, 2));
        graph.get(4).add(new Edge(3, 2));

        DijkstraTemplate d = new Dijkstra();
        System.out.println(Arrays.toString(d.dijkstra(graph, 0)));  // [0, 4, 3, 6, 5, INF]
    }
}

class Dijkstra implements DijkstraTemplate {
    static final long INF = Long.MAX_VALUE;

    @Override
    public long[] dijkstra(List<List<Edge>> graph, int start) {
        long[] distances = new long[graph.size()];
        Arrays.fill(distances, INF);
        distances[start] = 0;

        PriorityQueue<State> pq = new PriorityQueue<>(Comparator.comparingLong(State::distance));
        pq.offer(new State(start, distances[start]));

        while (!pq.isEmpty()) {
            State current = pq.poll();
            if (current.distance > distances[current.node()]) {
                continue;
            }

            for (Edge edge : graph.get(current.node())) {
                long distance = current.distance + edge.weight();
                if (distance >= distances[edge.to()]) {
                    continue;
                }
                distances[edge.to()] = distance;
                pq.offer(new State(edge.to(), distance));
            }
        }

        return distances;
    }

    private record State(int node, long distance) {

    }
}