package part10.ex1_gym_uniform;

// 수업자료 60페이지: 체육복
// https://school.programmers.co.kr/learn/courses/30/lessons/42862

import helpers.Test;

import java.util.*;
import java.util.stream.Collectors;

// 도난과 여벌이 모두 발생한 학생들을 우선 처리(도난, 대여 목록에서 삭제)
// 빌려주는 학생 목록에서 앞 번호부터 시작하여
// 도난당한 학생이 앞 혹은 뒤에만 있다면 그 학생에게 빌려주고,
// 앞뒤로 도난당한 학생이 모두 있다면 앞 학생에게 우선 빌려줌

// 빌려주는 학생을 앞에서부터 순회해서 앞쪽 학생부터 우선 빌려준다면
// 뒤쪽 학생을 선택하는 것보다 항상 전체 대여 횟수에서 손해가 없으므로 최적해를 유지할 수 있음
// 뒤쪽 학생에게 우선 빌려준다면 도난당한 앞쪽 학생은 더이상 빌릴 기회가 없어짐
class Solution {
    public int solution(int n, int[] lost, int[] reserve) {
        Set<Integer> lostSet = Arrays.stream(lost)
                .boxed()
                .collect(Collectors.toCollection(HashSet::new));

        // 빌려주는 학생을 앞에서부터 순회하기 위해서 TreeSet 사용
        Set<Integer> reserveSet = Arrays.stream(reserve)
                .boxed()
                .collect(Collectors.toCollection(TreeSet::new));

        // 도난과 여벌이 모두 발생한 학생
        // reserveSet에 변동이 생기므로
        // reserveSet이 아닌 reserve를 순회함
        for (int r : reserve) {
            if (lostSet.remove(r)) {
                reserveSet.remove(r);
            }
        }

        for (int r : reserveSet) {
            // 앞 학생에게 우선 빌려줌
            if (lostSet.remove(r - 1)) {
                continue;
            }

            // 뒤 학생에게 빌려줌
            lostSet.remove(r + 1);
        }

        return n - lostSet.size();
    }
}

public class Main {
    static void main(String[] args) {
        Solution sol = new Solution();
// 1. 기본 예제 1
        var r1 = sol.solution(5, new int[]{2, 4}, new int[]{1, 3, 5});
        Test.check("r1", r1, 5);

// 2. 기본 예제 2
        var r2 = sol.solution(5, new int[]{2, 4}, new int[]{3});
        Test.check("r2", r2, 4);

// 3. 기본 예제 3
        var r3 = sol.solution(3, new int[]{3}, new int[]{1});
        Test.check("r3", r3, 2);

// 4. 도난과 여벌이 일부 겹치고 나머지 학생은 대여 가능한 경우
        var r4 = sol.solution(5, new int[]{2, 4}, new int[]{2, 3});
        Test.check("r4", r4, 5);

// 5. 도난당한 여벌 학생이 다른 학생에게 빌려줄 수 없는 경우
        var r5 = sol.solution(5, new int[]{2, 3}, new int[]{3, 4});
        Test.check("r5", r5, 4);

// 6. 양쪽에서 각각 체육복을 빌려주는 경우
        var r6 = sol.solution(4, new int[]{2, 3}, new int[]{1, 4});
        Test.check("r6", r6, 4);

// 7. 도난 학생 수가 여벌 학생 수보다 많은 경우
        var r7 = sol.solution(7, new int[]{2, 4, 6}, new int[]{3, 5});
        Test.check("r7", r7, 6);

// 8. 모든 학생이 도난당하고 일부만 여벌 체육복이 있는 경우
        var r8 = sol.solution(5, new int[]{1, 2, 3, 4, 5}, new int[]{2, 4});
        Test.check("r8", r8, 2);

// 9. 최대 학생 수 30명, 연속적인 대여가 필요한 경우
        var r9 = sol.solution(30, new int[]{2, 4, 6, 8, 10, 12, 14, 16, 18, 20, 22, 24, 26, 28, 30}, new int[]{1, 3, 5, 7, 9, 11, 13, 15, 17, 19, 21, 23, 25, 27, 29});
        Test.check("r9", r9, 30);

// 10. 뒤쪽 학생에게 우선 빌려주면 최적해를 놓치는 경우
        var r10 = sol.solution(4, new int[]{2, 4}, new int[]{1, 3});
        Test.check("r10", r10, 4);

// 11. 도난 및 여벌 배열이 모두 정렬되지 않은 경우
        var r11 = sol.solution(8, new int[]{5, 2, 7}, new int[]{6, 1, 4});
        Test.check("r11", r11, 8);

// 12. 최소 학생 수, 모든 학생이 도난당하고 한 명만 여벌이 있는 경우
        var r12 = sol.solution(2, new int[]{1, 2}, new int[]{1});
        Test.check("r12", r12, 1);

// 13. HashSet에서 도난 학생이 오름차순으로 순회되지 않을 수 있는 경우
        var r13 = sol.solution(18, new int[]{17, 15}, new int[]{18, 16});
        Test.check("r13", r13, 18);

// 14. HashSet에서 여벌 학생이 오름차순으로 순회되지 않을 수 있는 경우
        var r14 = sol.solution(18, new int[]{16, 18}, new int[]{17, 15});
        Test.check("r14", r14, 18);

// 15. 최대 학생 수에서 HashSet 순회 순서와 연쇄적 대여를 함께 검증하는 경우
        var r15 = sol.solution(30, new int[]{18, 20, 16, 14, 12, 10}, new int[]{19, 17, 15, 13, 11, 9});
        Test.check("r15", r15, 30);
    }
}
