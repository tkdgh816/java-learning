package p260917.ex6;

public class QuantityInput {
    public static int parseQuantity(String text) throws InvalidQuantityException {
        int quantity = Integer.parseInt(text);
        // TODO: quantity가 1부터 100까지의 범위를 벗어나면
        // InvalidQuantityException("수량은 1~100입니다")을 던지세요.
        if (quantity < 1 || quantity > 100) {
            throw new InvalidQuantityException("수량은 1~100입니다");
        }
        return quantity;
    }

    public static void main(String[] args) {
        String[] inputs = {"2", "abc", "0", "100"};
        int total = 0;
        for (String input : inputs) {
            try {
                int quantity = parseQuantity(input);
                total += quantity;
                System.out.println("OK " + quantity);
            } catch (NumberFormatException e) {
                System.out.println("NOT_NUMBER");
                e.printStackTrace();
            } catch (InvalidQuantityException e) {
                System.out.println("OUT_OF_RANGE");
                e.printStackTrace();
            }
        }
        System.out.println("total=" + total);
    }
}
