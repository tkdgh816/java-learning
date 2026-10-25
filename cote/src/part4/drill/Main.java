package part4.drill;

public class Main {
    static void main(String[] args) {
        String[] arr = {"A", "B", "C", "D"};
        int r = 3; // 선택할 원소 개수

        Permutation permutation = new Permutation();
        permutation.permutation(arr, r);

        System.out.println();

        permutation.permutationWithRepetition(arr, r);

        System.out.println();

        Combination combination = new Combination();
        combination.combination(arr, r);

        System.out.println();
        combination.combinationWithRepetition(arr, r);
    }
}
