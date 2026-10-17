package part7.drill.mst;

import java.util.ArrayList;
import java.util.List;

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
        return 0;
    }
}

class Prim implements PrimTemplate {
    @Override
    public long prim(List<List<PrimEdge>> graph, int start) {
        return 0;
    }
}
