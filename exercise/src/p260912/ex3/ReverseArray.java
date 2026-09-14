package p260912.ex3;

public class ReverseArray {
    public static void main(String[] args) {
        // 실습 A-1: 배열을 만들고 마지막 값부터 출력하세요.
        int[] arr = {10, 20, 30, 40, 50};
        for (int i = arr.length - 1; i >= 0; i--) {
            System.out.print(arr[i] + " ");
        }
    }
}