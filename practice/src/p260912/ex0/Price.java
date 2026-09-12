package p260912.ex0;

import java.util.ArrayList;

public class Price {
    public static void main(String[] args) {
        int[] prices = {5000, 12000, 8000, 20000, 10000};
        int discountThreshold = 10000;
        int discountAmount = 1000;
        int discounted = 0;

        ArrayList<Integer> list = new ArrayList<>();
        for (int price : prices) {
            if (price >= discountThreshold) {
                list.add(price - discountAmount);
                discounted++;
            } else {
                list.add(price);
            }
        }

        int sum = 0;
        for(Integer element : list){
            sum += element;
        }

        System.out.println("최종 가격: " + list);
        System.out.println("할인 상품: " + discounted);
        System.out.println("전체 합계: " + sum);
    }
}
