package assignments.p03;

import java.util.List;

public class AdjustedScoreSummary {
    public static void main(String[] args) {
        List<Integer> scores = List.of(-10, 45, 60, 80, 98, 100, 120);
        int count = 0;
        int total = 0;
        for (int score : scores) {
            if (score < 0 || score > 100) {
                continue;
            }
            int adjusted = score + 5;
            if (adjusted > 100) {
                adjusted = 100;
            }
            count++;
            total += adjusted;
        }
        System.out.println("유효한 점수: " + count);
        System.out.println("보정 합계: " + total);

        long count2 = scores.stream()
                .filter(s -> s >= 0 && s <= 100)
                .count();
        long total2 = scores.stream()
                .filter(s -> s >= 0 && s <= 100)
                .mapToInt(s -> Math.min(s + 5, 100))
                .sum();

        System.out.println("유효한 점수: " + count2);
        System.out.println("보정 합계: " + total2);
    }
}
