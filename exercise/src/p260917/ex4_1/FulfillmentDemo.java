package p260917.ex4_1;

import p260917.ex2.DeliveryPolicy;
import p260917.ex2.PickupPolicy;
import p260917.ex2.ShippingDemo;
import p260917.ex2.ShippingPolicy;

public class FulfillmentDemo {
    public static void main(String[] args) {
        // 문제를 풀고, 실습 안내의 실행 예시를 옮겨 결과를 확인하세요.
        Fulfillment fulfillment = Fulfillment.DELIVERY;
        int subtotal = 13000;

        ShippingPolicy policy = fulfillment.createPolicy();

        System.out.println("mode=" + fulfillment);
        System.out.println("total=" + ShippingDemo.total(subtotal, policy));
    }
}