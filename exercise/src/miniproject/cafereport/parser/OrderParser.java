package miniproject.cafereport.parser;

import miniproject.cafereport.domain.*;

public class OrderParser {
    // 한 줄을 해석해서 Order 객체를 생성, 잘못된 줄이면 null 반환
    public static Order parse(String line) {
        String[] words = line.split(",", -1);

        if (words.length != 3) {
            return null;
        }

        try {
            String menu = words[0].strip();
            int price = Integer.parseInt(words[1].strip());
            int quantity = Integer.parseInt(words[2].strip());
            return Order.create(menu, price, quantity);
        } catch (NumberFormatException e) {
            return null;
        } catch (InvalidOrderException e) {
            return null;
        }
    }
}
