package part4.drill;

import java.util.ArrayList;
import java.util.List;

// P(n, r): n개 중에서 순서를 고려하여 r개를 선택
// 순열, 중복순열
public class Permutation {
    String[] arr;
    int n;
    int r;
    boolean[] visited;

    void permutation(String[] arr, int r) {
        this.arr = arr;
        this.n = arr.length;
        this.r = r;
        this.visited = new boolean[n];

        permutation(new ArrayList<>(r));
    }

    private void permutation(List<Integer> selectedIndexes) {
        if (selectedIndexes.size() == r) {
            System.out.println(selectedIndexes.stream().map(i -> arr[i]).toList());
            return;
        }

        for (int i = 0; i < n; i++) {
            if (visited[i]) {
                continue;
            }

            selectedIndexes.add(i);
            visited[i] = true;

            permutation(selectedIndexes);

            selectedIndexes.removeLast();
            visited[i] = false;
        }
    }

    void permutationWithRepetition(String[] arr, int r) {
        this.arr = arr;
        this.n = arr.length;
        this.r = r;

        permutationWithRepetition(new ArrayList<>(r));
    }

    private void permutationWithRepetition(List<Integer> selectedIndexes) {
        if (selectedIndexes.size() == r) {
            System.out.println(selectedIndexes.stream().map(i -> arr[i]).toList());
            return;
        }

        for (int i = 0; i < n; i++) {
            selectedIndexes.add(i);
            permutationWithRepetition(selectedIndexes);
            selectedIndexes.removeLast();
        }
    }
}
