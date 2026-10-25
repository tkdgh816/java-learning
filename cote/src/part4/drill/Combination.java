package part4.drill;

import java.util.*;

// C(n, r): n개 중에서 순서를 고려하지 않고 r개를 선택
// 조합, 중복조합
public class Combination {
    private String[] arr;
    private int n;
    private int r;

    void combination(String[] arr, int r) {
        this.arr = arr;
        this.n = arr.length;
        this.r = r;

        combination(new ArrayList<>(r), 0);
    }

    private void combination(List<Integer> selectedIndexes, int startIndex) {
        int selectedCount = selectedIndexes.size();
        if (selectedCount == r) {
            System.out.println(selectedIndexes.stream().map(i -> arr[i]).toList());
            return;
        }

        int remainingCount = r - selectedCount;
        for (int i = startIndex; i <= n - remainingCount; i++) {
            selectedIndexes.add(i);
            combination(selectedIndexes, i + 1);
            selectedIndexes.removeLast();
        }
    }

    void combinationWithRepetition(String[] arr, int r) {
        this.arr = arr;
        this.n = arr.length;
        this.r = r;
        combinationWithRepetition(new ArrayList<>(r), 0);
    }

    private void combinationWithRepetition(List<Integer> selectedIndexes, int startIndex) {
        if (selectedIndexes.size() == r) {
            System.out.println(selectedIndexes.stream().map(i -> arr[i]).toList());
            return;
        }

        for (int i = startIndex; i < n; i++) {
            selectedIndexes.add(i);
            combinationWithRepetition(selectedIndexes, i);
            selectedIndexes.removeLast();
        }
    }
}