package p260912.ex3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WordCounts {
    public static void main(String[] args) {
        // 실습 C-3: 단어별 등장 횟수를 구하고 알파벳순으로 출력하세요.
        String[] words = {"java", "git", "java", "array", "git", "java"};
        Map<String, Integer> map = new HashMap<>();

        for(String word : words){
            map.put(word, map.getOrDefault(word, 0) + 1);
        }

        List<String> keys = new ArrayList<>(map.keySet());
        Collections.sort(keys);
        for(String key : keys){
            System.out.println(key + "=" + map.get(key));
        }
        System.out.println("종류: " + map.size());
    }
}
