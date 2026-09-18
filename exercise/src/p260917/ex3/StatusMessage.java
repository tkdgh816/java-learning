package p260917.ex3;

public class StatusMessage {
    public static void main(String[] args) {
        // 문제를 풀고, 실습 안내의 실행 예시를 옮겨 결과를 확인하세요.
        OrderStatus status = OrderStatus.SHIPPED;
        switch(status){
            case OrderStatus.READY -> System.out.println("준비 중");
            case OrderStatus.SHIPPED -> System.out.println("배송 중");
            case OrderStatus.DELIVERED -> System.out.println("배송 완료");
        }
    }
}
