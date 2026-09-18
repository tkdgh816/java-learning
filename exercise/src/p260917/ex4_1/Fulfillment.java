package p260917.ex4_1;

import p260917.ex2.DeliveryPolicy;
import p260917.ex2.PickupPolicy;
import p260917.ex2.ShippingPolicy;

public enum Fulfillment {
    // 실습 또는 과제 안내에 나온 상수를 직접 선언하세요.
    PICKUP(new PickupPolicy()),
    DELIVERY(new DeliveryPolicy());

    private final ShippingPolicy shippingPolicy;

    Fulfillment(ShippingPolicy shippingPolicy) {
        this.shippingPolicy = shippingPolicy;
    }

    public ShippingPolicy createPolicy() {
        return shippingPolicy;
    }
}
