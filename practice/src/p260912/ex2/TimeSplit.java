package p260912.ex2;

import java.util.Scanner;

public class TimeSplit {
    public static void main(String[] args) {
        // 실습 C-1: 초를 분과 초로 나누기
        // 입력, 계산, 출력을 직접 작성하세요.

        Scanner scanner = new Scanner(System.in);
        int value = scanner.nextInt();

        int minute = value / 60;
        int second = value % 60;

        System.out.println(minute + "분 " + second + "초");
    }
}