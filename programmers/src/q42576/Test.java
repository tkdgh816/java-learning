package q42576;

import java.util.HashMap;
import java.util.Map;

// 완주하지 못한 선수
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
        // 참가자들 중 이름을 기준으로 몇 번 등장했는지 매핑(동명이인일 경우가 있으므로)
        Map<String, Integer> countsByName = new HashMap<>();
        for (String person : participant) {
            countsByName.put(person, countsByName.getOrDefault(person, 0) + 1);
        }

        // 완주한 사람들을 순회하며 등장 횟수 명단에서 일치하는 참가자의 횟수에서 1 차감
        for (String person : completion) {
            countsByName.put(person, countsByName.get(person) - 1);
        }

        // 완주하지 못한 사람들의 이름은 등장 횟수 명단에서 미처 차감되지 못하였으므로
        // 0보다 큰 value를 가진 엔트리들일 것임
        // 문제 조건에서 완주하지 못한 사람은 한 명뿐이었으므로
        // 0보다 큰 value를 가진 엔트리를 찾아서 키 값을 반환하면 됨
        return countsByName.entrySet().stream()
                .filter(entry -> entry.getValue() > 0)
                .findFirst()
                .orElseThrow()
                .getKey();
    }
}
