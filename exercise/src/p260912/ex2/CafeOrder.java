package p260912.ex2;

import java.util.Scanner;

public class CafeOrder {
    public static void main(String[] args) {
        // 실습 C-4: 카페 주문 받기
        // 입력, 계산, 출력을 직접 작성하세요.
        Scanner scanner = new Scanner(System.in);
        int orderCount = 0;
        int total = 0;
        while (true) {
            int option = scanner.nextInt();
            if (option == 0) {
                break;
            }
            switch (option) {
                case 1 -> {
                    orderCount++;
                    total += 3000;
                }
                case 2 -> {
                    orderCount++;
                    total += 4000;
                }
                default -> System.out.println("없는 메뉴입니다.");
            }
        }

        System.out.println("주문 수: " + orderCount);
        System.out.println("총 금액: " + total + "원");
    }
}