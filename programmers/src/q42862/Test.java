package q42862;

import java.util.*;

// 체육복
// https://school.programmers.co.kr/learn/courses/30/lessons/42862
public class Test {
    public static void main(String[] args) {
        Solution sol = new Solution();
        int r1 = sol.solution(5, new int[]{2, 4}, new int[]{1, 3, 5});
        int r2 = sol.solution(5, new int[]{2, 4}, new int[]{3});
        int r3 = sol.solution(3, new int[]{3}, new int[]{1});

        System.out.println(r1);
        System.out.println(r2);
        System.out.println(r3);
    }
}

class Solution {
    public int solution(int n, int[] lost, int[] reserve) {

        boolean[] extras = new boolean[n];
        Set<Integer> lostSet = new HashSet<>();
        for (int l : lost) {
            lostSet.add(l);
        }

        // 자기 자신에게 빌려주는 경우 제외
        for (int r : reserve) {
            if (lostSet.remove(r)) {
                continue;
            }
            extras[r - 1] = true;
        }

        List<boolean[]> situations = new ArrayList<>();
        situations.add(extras);

        // 분실한 학생에 대해 이전 상황들을 기반으로 상황들을 만들어서 전체 상황 목록에 추가
        for (int l : lostSet) {
            situations = situations.stream()
                    .flatMap(s -> getSituations(l, s).stream())
                    .toList();
        }

        int max = n - lostSet.size();;
        for (var situation : situations) {
            // 각 상황들에 대해서 맨 처음 여분 상황과 비교하여
            // 빌려준 횟수를 카운팅
            int rentCount = 0;
            for (int i = 0; i < n; i++) {
                if (extras[i] && !situation[i]) {
                    rentCount++;
                }
            }

            // 체육복을 가진 학생 수는
            // 전체 학생 수에서 체육복이 없는 학생 수를 뺀 값임
            int result = n - (lostSet.size() - rentCount);
            max = Math.max(max, result);
        }

        return max;
    }

    // 여분 학생 상황이 주어질 때 분실한 학생이 빌리고 난 후
    // 변경된 여분 학생 상황들을 다시 반환
    private List<boolean[]> getSituations(int lost, boolean[] extra) {
        List<boolean[]> extras = new ArrayList<>();

        // 왼쪽에게서 빌림
        if (lost > 1 && extra[lost - 2]) {
            var temp = extra.clone();
            temp[lost - 2] = false;
            extras.add(temp);
        }

        // 오른쪽에게서 빌림
        if (lost < extra.length && extra[lost]) {
            var temp = extra.clone();
            temp[lost] = false;
            extras.add(temp);
        }

        // 빌리지 않음
        extras.add(extra);

        return extras;
    }
}