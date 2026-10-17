package part7.drill.dsu;

public interface DSUTemplate {
    int find(int x);

    boolean union(int a, int b);

    boolean connected(int a, int b);
}