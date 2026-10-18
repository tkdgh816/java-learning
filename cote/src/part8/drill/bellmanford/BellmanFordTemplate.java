package part8.drill.bellmanford;

import java.util.List;

public interface BellmanFordTemplate {
    long[] bellmanFord(int n, List<Edge> edges, int start );
}
