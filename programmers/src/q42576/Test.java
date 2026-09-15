package q42576;

import java.util.HashMap;
import java.util.Map;

//https://school.programmers.co.kr/learn/courses/30/lessons/42576
public class Test {
    public static void main(String[] args) {
        Solution sol = new Solution();
        System.out.println(sol.solution(new String[]{"leo", "kiki", "eden"}, new String[]{"eden", "kiki"}));
        System.out.println(sol.solution(new String[]{"marina", "josipa", "nikola", "vinko", "filipa"}, new String[]{"josipa", "filipa", "marina", "nikola"}));
        System.out.println(sol.solution(new String[]{"mislav", "stanko", "mislav", "ana"}, new String[]{"stanko", "ana", "mislav"}));

    }
}

class Solution {
    public String solution(String[] participant, String[] completion) {
        Map<String, Integer> countsByName = new HashMap<>();
        for (String person : participant) {
            countsByName.put(person, countsByName.getOrDefault(person, 0) + 1);
        }

        for (String person : completion) {
            countsByName.put(person, countsByName.get(person) - 1);
        }

        return countsByName.entrySet().stream()
                .filter(entry -> entry.getValue() > 0)
                .findFirst()
                .orElseThrow()
                .getKey();
    }
}
