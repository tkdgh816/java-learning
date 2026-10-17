package part7.drill.mst;

import java.util.List;

public interface PrimTemplate {
    long prim(List<List<PrimEdge>> graph, int start);
}
