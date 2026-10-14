package part0.ex1_incomplete;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

// 수업자료 33페이지: 완주하지 못한 선수
// https://school.programmers.co.kr/learn/courses/30/lessons/42576

public class Main {
    static void main(String[] args) {
        Solution2 sol = new Solution2();

        String r1 = sol.solution(new String[]{"leo", "kiki", "eden"}, new String[]{"eden", "kiki"});
        String r2 = sol.solution(new String[]{"marina", "josipa", "nikola", "vinko", "filipa"}, new String[]{"josipa", "filipa", "marina", "nikola"});
        String r3 = sol.solution(new String[]{"mislav", "stanko", "mislav", "ana"}, new String[]{"stanko", "ana", "mislav"});

        System.out.println(r1);
        System.out.println(r2);
        System.out.println(r3);
    }
}

// 해시
class Solution1 {
    public String solution(String[] participant, String[] completion) {
        Map<String, Integer> countsByName = new HashMap<>();

        Arrays.stream(participant)
                .forEach(name -> countsByName.merge(name, 1, Integer::sum));
        Arrays.stream(completion)
                .forEach(name -> countsByName.merge(name, 1, (a, b) -> a - b));

        return countsByName.entrySet().stream()
                .filter(entry -> entry.getValue() > 0)
                .findFirst()
                .orElseThrow()
                .getKey();
    }
}

// 정렬
class Solution2 {
    public String solution(String[] participant, String[] completion) {
        Arrays.sort(participant);
        Arrays.sort(completion);

        for(int i = 0 ; i < completion.length; i++){
            if(!participant[i].equals(completion[i])){
                return participant[i];
            }
        }

        return participant[participant.length - 1];
    }
}