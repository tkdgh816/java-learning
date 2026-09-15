package p260914.ex2;

public class OrderLine {
    private String name;
    private int price;
    private int quantity;

    public OrderLine(String name, int price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public int amount() {
        return price * quantity;
    }

    public boolean changeQuantity(int next) {
        if (next >= 1 && next <= 100) {
            quantity = next;
            return true;
        }

        throw new IllegalArgumentException("수량은 1에서 100 사이여야 함");
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }
}