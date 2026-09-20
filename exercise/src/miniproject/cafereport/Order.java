package miniproject.cafereport;

public class Order {
    private final String menu;
    private final int price;
    private final int quantity;

    private Order(String menu, int price, int quantity) {
        this.menu = menu;
        this.price = price;
        this.quantity = quantity;
    }

    public String getMenu() {
        return menu;
    }

    public int getQuantity() {
        return quantity;
    }

    public long getAmount() {
        return (long) price * quantity;
    }

    public static Order create(String menu, int price, int quantity) {
        if (menu == null || menu.isBlank()) {
            return null;
        }

        if (price <= 0) {
            return null;
        }

        if (quantity <= 0) {
            return null;
        }

        return new Order(menu, price, quantity);
    }
}
