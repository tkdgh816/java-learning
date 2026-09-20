package miniproject.cafereport;

public class SalesSummary {
    private int totalQuantity;
    private long totalAmount;

    public int getTotalQuantity() {
        return totalQuantity;
    }

    public long getTotalAmount() {
        return totalAmount;
    }

    public void add(Order order) {
        totalQuantity += order.getQuantity();
        totalAmount += order.getAmount();
    }

    public void add(SalesSummary other) {
        totalQuantity += other.totalQuantity;
        totalAmount += other.totalAmount;
    }
}
