package part8.drill.floydwarshall;

import java.util.Arrays;

public class Main {
    static final long INF = Long.MAX_VALUE;

    static void main(String[] args) {
        long[][] graph = {
                {0, 4, 10, INF},
                {INF, 0, -2, INF},
                {INF, INF, 0, 3},
                {INF, 1, INF, 0}
        };

        FloydWarshallTemplate fw = new FloydWarshall();
        System.out.println(Arrays.toString(fw.floydWarshall(graph)));
        // [[0, 4, 2, 5], [INF, 0, -2, 1], [INF, 4, 0, 3], [INF, 1, -1, 0]]
    }
}

class FloydWarshall implements FloydWarshallTemplate {
    @Override
    public long[][] floydWarshall(long[][] graph) {
        return new long[0][];
    }
}