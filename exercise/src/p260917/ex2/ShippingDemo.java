package p260917.ex2;

public class ShippingDemo {
    public static void main(String[] args) {
        // 문제를 풀고, 실습 안내의 실행 예시를 옮겨 결과를 확인하세요.
        int[] subtotals = {13000, 29999, 30000, 30001};

        for(int subtotal : subtotals) {
            System.out.println("pickup=" + total(subtotal, new PickupPolicy()));
            System.out.println("delivery=" + total(subtotal, new DeliveryPolicy()));
            System.out.println();
        }
    }

    public static int total(int subtotal, ShippingPolicy policy) {
        return subtotal + policy.fee(subtotal);
    }
}
