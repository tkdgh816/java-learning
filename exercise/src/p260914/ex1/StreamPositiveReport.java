package p260914.ex1;

import java.util.List;

public class StreamPositiveReport {
    public static void main(String[] args) {
        // 스트림 실습 3: 양수의 개수, 제곱값, 제곱 합계를 구하세요.
        List<Integer> numbers = List.of(-3, -1, 0, 2, 4, 5);
        long count = numbers.stream()
                .filter(number -> number > 0)
                .count();
        System.out.println("양수 개수: " + count);

        numbers.stream()
                .filter(number -> number > 0)
                .mapToInt(number -> number * number)
                .forEach(System.out::println);

        int sum = numbers.stream()
                .filter(number -> number > 0)
                .mapToInt(number -> number * number)
                .sum();
        System.out.println("제곱 합계: " + sum);

    }
}
