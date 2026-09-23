package miniproject.cafereport.domain;

import java.util.*;

public class Branch {
    private final String name;
    private final Map<String, SalesSummary> salesByMenu = new LinkedHashMap<>();
    private int totalQuantity;
    private long totalAmount;
    private int orderCount;

    public Branch(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void addOrder(Order order) {
        SalesSummary salesSummary = salesByMenu.computeIfAbsent(order.getMenu(), menu -> new SalesSummary());
        salesSummary.add(order);

        totalQuantity += order.getQuantity();
        totalAmount += order.getAmount();
        orderCount++;
    }

    public Map<String, SalesSummary> getSalesByMenu() {
        return Collections.unmodifiableMap(salesByMenu);
    }

    public int getOrderCount() {
        return orderCount;
    }

    public int getTotalQuantity() {
        return totalQuantity;
    }

    public long getTotalAmount() {
        return totalAmount;
    }
}
