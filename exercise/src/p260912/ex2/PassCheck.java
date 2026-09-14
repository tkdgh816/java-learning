package p260912.ex2;

import java.util.Scanner;

public class PassCheck {
    public static void main(String[] args) {
        // 실습 C-2: 두 점수로 합격 판정하기
        // 입력, 계산, 출력을 직접 작성하세요.
        Scanner scanner = new Scanner(System.in);
        int first = scanner.nextInt();
        int second = scanner.nextInt();

        double average = (first + second) / 2.0;
        System.out.println("평균: " + average);

        if (average >= 60 && first >= 40 && second >= 40) {
            System.out.println("PASS");
        } else {
            System.out.println("FAIL");
        }
    }
}