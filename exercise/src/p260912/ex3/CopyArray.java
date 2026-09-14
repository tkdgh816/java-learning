package p260912.ex3;

import java.util.Arrays;

public class CopyArray {
    public static void main(String[] args) {
        // 실습 A-2: 새 배열에 값을 복사한 뒤 복사본의 첫 가격만 바꾸세요.
        int[] prices = {1000, 2000, 3000};
//        int[] copy = Arrays.copyOf(prices, prices.length);
        int[] copy = prices;
        copy[0] += 500;
        System.out.println("원본: " + Arrays.toString(prices));
        System.out.println("복사본: " + Arrays.toString(copy));
    }
}