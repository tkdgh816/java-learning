package assignments.p02;

import java.util.Scanner;

public class ParkingFee {
    public static void main(String[] args) {
        // 과제 1: 주차 요금 계산하기
        // 입력, 계산, 출력을 직접 작성하세요.

        System.out.print("주차 시간(분): ");
        Scanner scanner = new Scanner(System.in);
        int parkingTime = scanner.nextInt();
        int fee = 0;

        if (parkingTime > 30) {
            double unit = Math.ceil((parkingTime - 30) / 10.0);
            fee = (int) unit * 500;
        }

        fee = Math.min(fee, 10000);
        System.out.println("주차 요금: " + fee + "원");
    }
}
