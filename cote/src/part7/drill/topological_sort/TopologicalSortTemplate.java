package part7.drill.topological_sort;

import java.util.List;

public interface TopologicalSortTemplate {
    List<Integer> topologicalSort(List<List<Integer>> graph, int[] indegree);
}
