package part7.drill.dsu;

public class Main {
    static void main(String[] args) {
        DSUTemplate dsu = new DSU(7);

        dsu.union(0, 1);
        dsu.union(1, 2);

        System.out.println(dsu.connected(0, 2)); // true
        System.out.println(dsu.connected(0, 3)); // false

        dsu.union(3, 4);
        dsu.union(2, 4);

        System.out.println(dsu.connected(1, 3)); // true

        dsu.union(5, 6);

        System.out.println(dsu.connected(4, 6)); // false
        System.out.println(dsu.union(0, 2));     // false

        dsu.union(4, 6);

        System.out.println(dsu.connected(0, 5)); // true
    }
}

class DSU implements DSUTemplate {
    int[] parent;
    int[] size;

    DSU(int n) {
        parent = new int[n];
        size = new int[n];

        for (int i = 0; i < n; i++) {
            parent[i] = i;
            size[i] = 1;
        }
    }

    @Override
    public int find(int x) {
        if (parent[x] == x) {
            return x;
        }

        return parent[x] = find(parent[x]);
    }

    @Override
    public boolean union(int a, int b) {
        int rootA = find(a);
        int rootB = find(b);

        if (rootA == rootB) {
            return false;
        }

        if (size[rootA] < size[rootB]) {
            int temp = rootA;
            rootA = rootB;
            rootB = temp;
        }

        parent[rootB] = rootA;
        size[rootA] += size[rootB];

        return true;
    }

    @Override
    public boolean connected(int a, int b) {
        return find(a) == find(b);
    }
}