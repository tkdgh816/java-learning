package assignments.p03;

import java.util.List;

public class LongWordCount {
    public static void main(String[] args) {
        List<String> words = List.of("java", "if", "stream", "for", "lambda");
        int count = 0;
        for (String word : words) {
            if (word.length() >= 5) {
                count++;
            }
        }
        System.out.println("긴 단어: " + count);

        long count2 = words.stream()
                .filter(word -> word.length() >= 5)
                .count();
        System.out.println("긴 단어: " + count2);
    }
}