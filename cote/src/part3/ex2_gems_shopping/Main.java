package part3.ex2_gems_shopping;

// 수업자료 25페이지: 보석 쇼핑
// https://school.programmers.co.kr/learn/courses/30/lessons/67258

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class Solution1 {
    public int[] solution(String[] gems) {
        long count = Arrays.stream(gems).distinct().count();
        Map<String, Integer> map = new HashMap<>();

        int[] answer = new int[]{1, gems.length};

        // 초깃값
        map.put(gems[0], 1);
        int left = 0;
        int right = 0;

        while (true) {
            // 최소 길이 구간 구하기
            if (map.size() == count && right - left < answer[1] - answer[0]) {
                answer[0] = left + 1;
                answer[1] = right + 1;
            }

            // 오른쪽 확장, 왼쪽 축소
            if (map.size() < count) {
                right++;
                // 오른쪽 끝에 닿으면 종료
                if (right == gems.length) {
                    break;
                }
                map.merge(gems[right], 1, Integer::sum);
            } else {
                map.merge(gems[left], -1, Integer::sum);
                if (map.get(gems[left]) == 0) {
                    map.remove(gems[left]);
                }
                left++;
            }
        }

        return answer;
    }
}

public class Main {
    static void main(String[] args) {
        Solution1 sol = new Solution1();

        var r1 = sol.solution(new String[]{"DIA", "RUBY", "RUBY", "DIA", "DIA", "EMERALD", "SAPPHIRE", "DIA"});
        System.out.println("r1: " + Arrays.toString(r1)); // [3, 7]

        var r2 = sol.solution(new String[]{"AA", "AB", "AC", "AA", "AC"});
        System.out.println("r2: " + Arrays.toString(r2)); // [1, 3]

        var r3 = sol.solution(new String[]{"XYZ", "XYZ", "XYZ"});
        System.out.println("r3: " + Arrays.toString(r3)); // [1, 1]

        var r4 = sol.solution(new String[]{"ZZZ", "YYY", "NNNN", "YYY", "BBB"});
        System.out.println("r4: " + Arrays.toString(r4)); // [1, 5]

        var r5 = sol.solution(new String[]{"A"});
        System.out.println("r5: " + Arrays.toString(r5)); // [1, 1]

        var r6 = sol.solution(new String[]{"A", "B", "C"});
        System.out.println("r6: " + Arrays.toString(r6)); // [1, 3]

        var r7 = sol.solution(new String[]{"A", "A", "B", "B", "C", "C", "A"});
        System.out.println("r7: " + Arrays.toString(r7)); // [2, 5]

        var r8 = sol.solution(new String[]{"A", "B", "A", "C", "B", "C"});
        System.out.println("r8: " + Arrays.toString(r8)); // [2, 4]

        var r9 = sol.solution(new String[]{"A", "B", "C", "A", "B", "C"});
        System.out.println("r9: " + Arrays.toString(r9)); // [1, 3]

        var r10 = sol.solution(new String[]{"A", "B", "B", "B", "C", "A", "C", "B"});
        System.out.println("r10: " + Arrays.toString(r10)); // [4, 6]
    }
}