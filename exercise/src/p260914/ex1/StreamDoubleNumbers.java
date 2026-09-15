package p260914.ex1;
import java.util.List;

public class StreamDoubleNumbers {
    public static void main(String[] args) {
        // 스트림 실습 2: 각 값을 두 배로 바꿔 출력하고 원본도 확인하세요.
        List<Integer> numbers = List.of(3, 5, 7);
        numbers.stream()
                .map(number -> number * 2)
                .forEach(System.out::println);
        System.out.println("원본: " + numbers);
    }
}