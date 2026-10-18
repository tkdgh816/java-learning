package part8.drill.dijkstra;

import java.util.List;

public interface DijkstraTemplate {
    long[] dijkstra(List<List<Edge>> graph, int start);
}
