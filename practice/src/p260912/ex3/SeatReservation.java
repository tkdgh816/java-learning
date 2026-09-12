package p260912.ex3;

import java.util.Arrays;
import java.util.Scanner;

public class SeatReservation {
    public static void main(String[] args) {
        // 실습 A-3: 1차원 배열로 좌석 5개의 예약 상태를 관리하세요.
        int[] seats = new int[5];
        int count = 0;

        Scanner scanner = new Scanner(System.in);
        while (true) {
            int input = scanner.nextInt();
            if (input == 0) {
                break;
            }

            if (input >= 1 && input <= 5) {
                int index = input - 1;
                if (seats[index] == 0) {
                    seats[index] = 1;
                    count++;
                    System.out.println("예약했습니다");
                } else {
                    System.out.println("이미 예약된 좌석입니다");
                }
            } else {
                System.out.println("없는 좌석입니다");
            }
        }
        System.out.println("좌석: " + Arrays.toString(seats));
        System.out.println("예약 수: " + count);
    }
}