package all.part7.op1_disjoint_set;

// https://judge.u-aizu.ac.jp/onlinejudge/description.jsp?id=DSL_1_A&lang=en

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int q = Integer.parseInt(st.nextToken());

        StringBuilder sb = new StringBuilder();
        DSU dsu = new DSU(n);
        for (int i = 0; i < q; i++) {
            st = new StringTokenizer(br.readLine());
            int action = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            boolean r = switch (action) {
                case 0 -> dsu.union(a, b);
                case 1 -> dsu.connected(a, b);
                default -> throw new IllegalStateException();
            };

            sb.append(r ? "1" : "0").append(System.lineSeparator());
        }

        System.out.println(sb);
    }
}

class DSU {
    private final int[] parent;
    private final int[] size;

    DSU(int n) {
        parent = new int[n];
        size = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            size[i] = 0;
        }
    }

    public int find(int x) {
        if (parent[x] == x) {
            return x;
        }

        return parent[x] = find(parent[x]);
    }

    public boolean union(int a, int b) {
        int rootA = find(a), rootB = find(b);

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

    public boolean connected(int a, int b) {
        return find(a) == find(b);
    }
}