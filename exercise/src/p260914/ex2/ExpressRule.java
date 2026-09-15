package p260914.ex2;

public class ExpressRule extends ShippingRule {
    @Override
    public int fee(int subtotal) {
        return super.fee(subtotal) + 2000;
    }
}
