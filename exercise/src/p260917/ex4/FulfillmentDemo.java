package p260917.ex4;

import p260917.ex2.*;

public class FulfillmentDemo {
    public static void main(String[] args) {
        // 문제를 풀고, 실습 안내의 실행 예시를 옮겨 결과를 확인하세요.
        Fulfillment fulfillment = Fulfillment.DELIVERY;
        int subtotal = 13000;

        ShippingPolicy policy = switch (fulfillment) {
            case Fulfillment.PICKUP -> new PickupPolicy();
            case Fulfillment.DELIVERY -> new DeliveryPolicy();
        };

        System.out.println("mode=" + fulfillment);
        System.out.println("total=" + ShippingDemo.total(subtotal, policy));
    }
}