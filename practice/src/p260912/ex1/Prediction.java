package p260912.ex1;

public class Prediction {
    public static void main(String[] args) {
        // 실행 전: 아래 여섯 출력의 예상값을 적으세요.
        // 3
        // 3.0
        // 3.5
        // false
        // 5
        // -2147483648
        System.out.println(7 / 2);
        System.out.println((double) (7 / 2));
        System.out.println((double) 7 / 2);
        int divisor = 0;
        System.out.println(divisor != 0 && 10 / divisor > 1);
        int count = 2;
        System.out.println(count++ + count);
        int max = Integer.MAX_VALUE;
        System.out.println(max + 1);
    }
}