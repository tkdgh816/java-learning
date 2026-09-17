package assignments.p03;

import java.util.List;

public class EvenNumberSum {
    public static void main(String[] args) {
        List<Integer> numbers = List.of(3, 6, 1, 8, 5, 2);
        int total = 0;
        for (int number : numbers) {
            if (number % 2 == 0) {
                total += number;
            }
        }
        System.out.println("짝수 합계: " + total);

        int total2 = numbers.stream()
                .filter(n -> n % 2 == 0)
                .mapToInt(Integer::intValue)
                .sum();
        System.out.println("짝수 합계: " + total2);
    }
}