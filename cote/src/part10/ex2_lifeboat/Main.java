package part10.ex2_lifeboat;

// 수업자료 61페이지: 구명보트
// https://school.programmers.co.kr/learn/courses/30/lessons/42885

import helpers.Test;

import java.util.Arrays;

// 현재 가장 무거운 사람을 보트에 먼저 탑승시킨다면
// 제한 무게를 넘지 않도록 하는 가벼운 사람 누구를 추가하든
// 최적해에 영향을 주지 않음
// 그 이유는 가장 무거운 사람과 함께 탈 수 있는 정도로 가벼운 사람이라면
// 그 사람은 남아 있는 다른 어떤 사람과도 함께 탈 수 있기 때문임

class Solution {
    public int solution(int[] people, int limit) {
        int count = 0;

        Arrays.sort(people);
        int left = 0, right = people.length - 1;

        // 가장 무거운 사람과 함께 탑승 가능한 가장 가벼운 사람을 태우면
        // 포인터를 옮겨 다음 라운드에서 이미 탑승한 양끝단의 무겁거나 가벼운 사람들을 배제하기 용이함
        while (left <= right) {
            if (left < right && people[left] + people[right] <= limit) {
                left++;
            }
            right--;
            count++;
        }

        return count;
    }
}

public class Main {
    static void main(String[] args) {
        Solution sol = new Solution();

// 1. 기본 예제 1
        var r1 = sol.solution(new int[]{70, 50, 80, 50}, 100);
        Test.check("r1", r1, 3);

// 2. 기본 예제 2
        var r2 = sol.solution(new int[]{70, 80, 50}, 100);
        Test.check("r2", r2, 3);

// 3. 최소 인원 1명
        var r3 = sol.solution(new int[]{40}, 41);
        Test.check("r3", r3, 1);

// 4. 두 사람의 무게 합이 제한과 정확히 일치하는 경우
        var r4 = sol.solution(new int[]{40, 40}, 80);
        Test.check("r4", r4, 1);

// 5. 두 사람의 무게 합이 제한을 1 초과하는 경우
        var r5 = sol.solution(new int[]{40, 41}, 80);
        Test.check("r5", r5, 2);

// 6. 세 사람의 무게 합이 제한 이하여도 최대 2명만 탑승 가능한 경우
        var r6 = sol.solution(new int[]{40, 40, 40}, 120);
        Test.check("r6", r6, 2);

// 7. 홀수 인원이 모두 동일한 무게인 경우
        var r7 = sol.solution(new int[]{40, 40, 40, 40, 40}, 80);
        Test.check("r7", r7, 3);

// 8. 어느 두 사람도 함께 탈 수 없는 경우
        var r8 = sol.solution(new int[]{60, 61, 62, 63}, 100);
        Test.check("r8", r8, 4);

// 9. 가벼운 사람끼리 먼저 태우면 최적해를 놓치는 경우
        var r9 = sol.solution(new int[]{40, 50, 50, 60}, 100);
        Test.check("r9", r9, 2);

// 10. 무거운 사람은 혼자 타고 가벼운 사람끼리 탑승하는 경우
        var r10 = sol.solution(new int[]{40, 40, 80, 90}, 100);
        Test.check("r10", r10, 3);

// 11. 정렬되지 않은 입력에서 적절히 짝짓는 경우
        var r11 = sol.solution(new int[]{59, 40, 41, 60}, 100);
        Test.check("r11", r11, 2);

// 12. 가장 가벼운 사람도 다른 사람과 함께 탈 수 없는 경우
        var r12 = sol.solution(new int[]{40, 61, 61, 61}, 100);
        Test.check("r12", r12, 4);

// 13. 여러 쌍을 최적으로 배치해야 하는 경우
        var r13 = sol.solution(new int[]{40, 40, 50, 50, 60, 60}, 100);
        Test.check("r13", r13, 3);

// 14. 무게 제한에 근접한 여러 사람을 배치하는 경우
        var r14 = sol.solution(new int[]{119, 120, 121, 40, 40}, 160);
        Test.check("r14", r14, 3);

// 15. 최대 무게 제한 240인 경우
        var r15 = sol.solution(new int[]{40, 200, 239}, 240);
        Test.check("r15", r15, 2);

// 16. 내림차순으로 주어진 입력에서 일부만 짝지을 수 있는 경우
        var r16 = sol.solution(new int[]{90, 80, 60, 50, 40}, 100);
        Test.check("r16", r16, 4);

// 17. 최대 인원 50,000명이 모두 2명씩 탑승 가능한 경우
        var r17 = sol.solution(java.util.stream.IntStream.generate(() -> 40).limit(50_000).toArray(), 80);
        Test.check("r17", r17, 25000);

// 18. 최대 인원 50,000명이 모두 혼자 탑승해야 하는 경우
        var r18 = sol.solution(java.util.stream.IntStream.generate(() -> 60).limit(50_000).toArray(), 100);
        Test.check("r18", r18, 50000);
    }
}
