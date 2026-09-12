package p260912.ex2;

import java.util.Scanner;

public class MultipleSum {
    public static void main(String[] args) {
        // 실습 C-3: 3의 배수 합계 구하기
        // 입력, 계산, 출력을 직접 작성하세요.
        Scanner scanner = new Scanner(System.in);
        int value = scanner.nextInt();

        int sum = 0;
        for (int i = 1; i <= value; i++) {
            if (i % 3 == 0) {
                sum += i;
            }
        }

        System.out.println("합계: " + sum);
    }
}
