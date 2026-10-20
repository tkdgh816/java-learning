package part10.ex3_speed_camera;

// 수업자료 62페이지: 단속카메라
// https://school.programmers.co.kr/learn/courses/30/lessons/42884

import helpers.Test;

import java.util.Arrays;
import java.util.Comparator;

// 목표: 단속 카메라를 최소한으로 설치

// 먼저 진입한 차량부터 차량을 추가하며 공통 구간이 있는지 판단
// 공통 구간이 존재한다면 그 구간으로 공통 구간을 좁히고
// 공통 구간이 존재하지 않는다면 카메라를 늘리고 새로운 공통 구간을 찾음

// 새로 진입하는 차량을 기존 공통 구간에 포함시킬 수 있다면
// 새로운 공통 구간을 굳이 만들어서
// 카메라 대수를 불필요하게 늘리는 선택을 할 필요가 없음
class Solution1 {
    public int solution(int[][] routes) {
        int count = 0;

        // 진입 지점을 기준으로 오름차순 정렬
        Arrays.sort(routes, Comparator.comparingInt(route -> route[0]));
        int end = Integer.MIN_VALUE;

        for (int[] route : routes) {
            // 새로 진입한 차량이 기존 공통 구간에 포함된다면
            // 공통 구간을 갱신하고 기존 카메라 사용 가능
            if (route[0] <= end) {
                end = Math.min(end, route[1]);
                continue;
            }

            // 그렇지 않다면 새로운 공통 구간을 만들고
            // 그 구간을 위한 카메라를 추가함
            end = route[1];
            count++;
        }

        return count;
    }
}

// 가장 빨리 진출하는 차량을 단속할 카메라는 어차피 필요하므로
// 그 진출 지점에 카메라를 설치한다면
// 그 진출 지점보다 먼저 진입한 차량을 모두 처리할 수 있음
class Solution2 {
    public int solution(int[][] routes) {
        int count = 0;

        // 진출 지점을 기준으로 오름차순 정렬
        Arrays.sort(routes, Comparator.comparingInt(route -> route[1]));
        int cam = Integer.MIN_VALUE;

        for (int[] route : routes) {
            // 현재 차량의 진입 지점이 카메라 위치 뒤에 있지 않다면
            // 그 차량은 해당 카메라가 단속할 수 있음
            if (route[0] <= cam) {
                continue;
            }

            // 그렇지 않다면(현재 차량이 카메라보다 더 뒤에서 진입)
            // 차량의 진출 지점에 새 카메라 설치
            cam = route[1];
            count++;
        }

        return count;
    }
}

// 두 방식 비교
// [Solution1]      [Solution2]
// ■■■■■■◻◻◻◻◻◻     ◻■■■◻◻◻◻◻◻◻◻
// ◻■■■◻◻◻◻◻◻◻◻     ■■■■■■◻◻◻◻◻◻
// ◻◻◻■■■■■■◻◻◻     ◻◻◻■■■■■■◻◻◻
// ◻◻◻◻◻■■■■■■■     ◻◻◻◻◻◻◻■■■◻◻
// ◻◻◻◻◻◻◻■■■◻◻     ◻◻◻◻◻■■■■■■■
// ◻◻◻]◻◻◻◻◻]◻◻     ◻◻◻✓◻◻◻◻◻✓◻◻

public class Main {
    static void main(String[] args) {
//        Solution1 sol = new Solution1();
        Solution2 sol = new Solution2();

        // 1. 기본 예제
        var r1 = sol.solution(new int[][]{{-20, -15}, {-14, -5}, {-18, -13}, {-5, -3}});
        Test.check("r1", r1, 2);

        // 2. 차량 1대, 진입 지점과 진출 지점이 같음
        var r2 = sol.solution(new int[][]{{0, 0}});
        Test.check("r2", r2, 1);

        // 3. 차량 1대, 전체 구간
        var r3 = sol.solution(new int[][]{{-30000, 30000}});
        Test.check("r3", r3, 1);

        // 4. 넓은 구간이 섞여 있어도 한 점으로 모두 덮을 수 없음
        var r4 = sol.solution(new int[][]{{-20, 15}, {-14, -5}, {-18, -13}, {-5, -3}});
        Test.check("r4", r4, 2);

        // 5. 전혀 겹치지 않는 경로
        var r5 = sol.solution(new int[][]{{1, 2}, {3, 4}, {5, 6}, {7, 8}});
        Test.check("r5", r5, 4);

        // 6. 끝점만 맞닿은 경로 (경계도 만난 것으로 간주)
        var r6 = sol.solution(new int[][]{{0, 3}, {3, 6}, {6, 9}});
        Test.check("r6", r6, 2);

        // 7. 끝점 접촉 + 내부 구간 (0 한 점으로 전부 커버)
        var r7 = sol.solution(new int[][]{{-5, 0}, {0, 5}, {-1, 1}});
        Test.check("r7", r7, 1);

        // 8. 진입 지점 기준 정렬 시 틀리는 케이스 (긴 구간이 맨 앞)
        var r8 = sol.solution(new int[][]{{0, 10}, {1, 2}, {3, 4}, {5, 6}, {7, 8}});
        Test.check("r8", r8, 4);

        // 9. 구간이 서로 포함 관계
        var r9 = sol.solution(new int[][]{{-10, 10}, {-5, 5}, {-1, 1}});
        Test.check("r9", r9, 1);

        // 10. 동일한 경로 중복
        var r10 = sol.solution(new int[][]{{2, 5}, {2, 5}, {2, 5}});
        Test.check("r10", r10, 1);

        // 11. 진입 = 진출인 차량이 섞여 있음
        var r11 = sol.solution(new int[][]{{-3, -3}, {-3, 2}, {2, 2}, {1, 4}});
        Test.check("r11", r11, 2);

        // 12. 입력이 정렬되어 있지 않음
        var r12 = sol.solution(new int[][]{{10, 20}, {-7, -1}, {3, 12}, {-30000, -20000}, {15, 30000}, {-2, 4}});
        Test.check("r12", r12, 4);

        // 13. 연쇄적으로 겹치는 경로
        var r13 = sol.solution(new int[][]{{0, 2}, {1, 3}, {2, 4}, {3, 5}, {4, 6}, {5, 7}});
        Test.check("r13", r13, 2);

        // 14. 범위 극단값
        var r14 = sol.solution(new int[][]{{-30000, -30000}, {30000, 30000}, {-30000, 30000}});
        Test.check("r14", r14, 2);

        // 15. 음수와 양수에 걸친 경로, 끝점 공유
        var r15 = sol.solution(new int[][]{{-100, -1}, {-1, 1}, {1, 100}});
        Test.check("r15", r15, 2);

        // 16. 최대 규모 - 서로 겹치지 않는 10,000대
        var routes16 = new int[10000][];
        for (int i = 0; i < 10000; i++) routes16[i] = new int[]{-30000 + 6 * i, -30000 + 6 * i + 2};
        var r16 = sol.solution(routes16);
        Test.check("r16", r16, 10000);

        // 17. 최대 규모 - 전부 동일한 전체 구간 10,000대
        var routes17 = new int[10000][];
        for (int i = 0; i < 10000; i++) routes17[i] = new int[]{-30000, 30000};
        var r17 = sol.solution(routes17);
        Test.check("r17", r17, 1);

        // 18. 최대 규모 - 인접 구간이 끝점만 공유하는 사슬 10,000대
        var routes18 = new int[10000][];
        for (int i = 0; i < 10000; i++) routes18[i] = new int[]{-30000 + 3 * i, -30000 + 3 * i + 3};
        var r18 = sol.solution(routes18);
        Test.check("r18", r18, 5000);
    }
}
