package p260917.ex2;

public class DeliveryPolicy implements ShippingPolicy {
    // 인터페이스를 구현하고 필요한 메서드를 직접 작성하세요.
    @Override
    public int fee(int subtotal) {
        if (subtotal >= 30000) {
            return 0;
        }
        return 3000;
    }
}
