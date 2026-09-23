package miniproject.cafereport.domain;

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

    public static Order create(String menu, int price, int quantity) throws InvalidOrderException {
        if (menu == null || menu.isBlank()) {
            throw new InvalidOrderException("메뉴명이 비어 있음");
        }

        if (price <= 0) {
            throw new InvalidOrderException("가격은 0보다 커야 함");
        }

        if (quantity <= 0) {
            throw new InvalidOrderException("수량은 0보다 커야 함");
        }

        return new Order(menu, price, quantity);
    }
}
