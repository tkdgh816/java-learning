package part11.ex1_walk;

// 수업자료 28페이지: 공원 산책
// https://school.programmers.co.kr/learn/courses/30/lessons/172928

import helpers.Test;

import java.util.StringTokenizer;

class Solution {
    public int[] solution(String[] park, String[] routes) {
        int[] position = new int[2];

        int h = park.length, w = park[0].length();

        // 출발 위치 찾기
        outer: for (int i = 0; i < h; i++) {
            char[] chars = park[i].toCharArray();
            for (int j = 0; j < w; j++) {
                if (chars[j] == 'S') {
                    position[0] = i;
                    position[1] = j;
                    break outer;
                }
            }
        }

        for (String route : routes) {
            StringTokenizer st = new StringTokenizer(route);

            // 방향
            int[] delta = getDelta(st.nextToken());

            // 이동 횟수
            int n = Integer.parseInt(st.nextToken());

            boolean canMove = true;

            // 한 칸씩 이동하며 장애물이 있는지 확인
            for (int i = 1; i <= n; i++) {
                int x = position[0] + i * delta[0];
                int y = position[1] + i * delta[1];
                if (x < 0 || x >= h || y < 0 || y >= w || park[x].charAt(y) == 'X') {
                    canMove = false;
                    break;
                }
            }

            // 장애물 없이 이동 가능하다면 현재 위치 이동 확정
            if (canMove) {
                position[0] += n * delta[0];
                position[1] += n * delta[1];
            }
        }

        return position;
    }

    private int[] getDelta(String op) {
        return switch (op) {
            case "W" -> new int[]{0, -1};
            case "E" -> new int[]{0, 1};
            case "N" -> new int[]{-1, 0};
            case "S" -> new int[]{1, 0};
            default -> throw new IllegalArgumentException("op: " + op);
        };
    }
}

public class Main {
    static void main(String[] args) {
        Solution sol = new Solution();

// 1. 기본 예제 1
        var r1 = sol.solution(new String[]{"SOO", "OOO", "OOO"}, new String[]{"E 2", "S 2", "W 1"});
        Test.check("r1", r1, new int[]{2, 1});

// 2. 기본 예제 2
        var r2 = sol.solution(new String[]{"SOO", "OXX", "OOO"}, new String[]{"E 2", "S 2", "W 1"});
        Test.check("r2", r2, new int[]{0, 1});

// 3. 기본 예제 3
        var r3 = sol.solution(new String[]{"OSO", "OOO", "OXO", "OOO"}, new String[]{"E 2", "S 3", "W 1"});
        Test.check("r3", r3, new int[]{0, 0});

// 4. 경계 밖으로 이동하는 명령 무시
        var r4 = sol.solution(new String[]{"SOO", "OOO", "OOO"}, new String[]{"N 1", "W 1", "E 2", "S 2", "E 1", "S 1"});
        Test.check("r4", r4, new int[]{2, 2});

// 5. 바로 옆에 장애물이 있는 경우
        var r5 = sol.solution(new String[]{"OOO", "OSX", "OOO"}, new String[]{"E 1", "S 1"});
        Test.check("r5", r5, new int[]{2, 1});

// 6. 이동 경로 중간에 장애물이 있는 경우
        var r6 = sol.solution(new String[]{"SOXO", "OOOO", "OOOO"}, new String[]{"E 3", "S 2"});
        Test.check("r6", r6, new int[]{2, 0});

// 7. 도착 지점에 장애물이 있는 경우
        var r7 = sol.solution(new String[]{"SOOX", "OOOO", "OOOO"}, new String[]{"E 3", "E 2"});
        Test.check("r7", r7, new int[]{0, 2});

// 8. 일부 칸만 이동할 수 있어도 명령 전체를 무시
        var r8 = sol.solution(new String[]{"SOO", "OOO", "OOO"}, new String[]{"E 3", "S 1"});
        Test.check("r8", r8, new int[]{1, 0});

// 9. 시작점의 모든 방향이 장애물로 막힌 경우
        var r9 = sol.solution(new String[]{"XXX", "XSX", "XXX"}, new String[]{"N 1", "E 1", "S 1", "W 1"});
        Test.check("r9", r9, new int[]{1, 1});

// 10. 이동 경로와 무관한 장애물이 있는 경우
        var r10 = sol.solution(new String[]{"SOOO", "OXOO", "OOOO", "OOOO"}, new String[]{"E 2", "S 2", "W 1", "S 1", "E 2"});
        Test.check("r10", r10, new int[]{3, 3});

// 11. 이동 후 다음 명령이 장애물 때문에 무시되는 경우
        var r11 = sol.solution(new String[]{"SOOO", "OOXO", "OOOO"}, new String[]{"E 1", "S 1", "E 2", "S 1"});
        Test.check("r11", r11, new int[]{2, 1});

// 12. 장애물을 피해 우회하는 경우
        var r12 = sol.solution(new String[]{"SOXO", "OOOO", "OOOO"}, new String[]{"E 3", "S 1", "E 3", "N 1"});
        Test.check("r12", r12, new int[]{0, 3});

// 13. 우측 하단에서 시작하는 경우
        var r13 = sol.solution(new String[]{"OOOO", "OOOO", "OOOS"}, new String[]{"N 2", "W 3", "S 2", "E 1"});
        Test.check("r13", r13, new int[]{2, 1});

// 14. 세로로 긴 직사각형 공원
        var r14 = sol.solution(new String[]{"OOO", "OXO", "OSO", "OOO", "OOO"}, new String[]{"N 2", "S 2", "E 1", "S 2"});
        Test.check("r14", r14, new int[]{4, 2});

// 15. 한 번에 최대 거리인 9칸 이동
        var r15 = sol.solution(new String[]{"SOOOOOOOOO", "OOOOOOOOOO", "OOOOOOOOOO"}, new String[]{"E 9", "S 2", "W 9"});
        Test.check("r15", r15, new int[]{2, 0});

// 16. 9칸 이동 중 장애물을 만나는 경우
        var r16 = sol.solution(new String[]{"SOOOOOOXOO", "OOOOOOOOOO", "OOOOOOOOOO"}, new String[]{"E 9", "S 2"});
        Test.check("r16", r16, new int[]{2, 0});

// 17. 네 방향으로 연속 이동하는 경우
        var r17 = sol.solution(new String[]{"OOO", "OSO", "OOO"}, new String[]{"N 1", "S 2", "W 1", "E 2", "N 1"});
        Test.check("r17", r17, new int[]{1, 2});

// 18. 장애물 직전까지는 정상적으로 이동할 수 있는 경우
        var r18 = sol.solution(new String[]{"SOOX", "OOOO", "OOOO"}, new String[]{"E 2", "E 1", "S 2", "W 2"});
        Test.check("r18", r18, new int[]{2, 0});

// 19. 최종 목적지는 통로지만 중간에 장애물이 있는 경우
        var r19 = sol.solution(new String[]{"SOXOO", "OOOOO", "OOOOO"}, new String[]{"E 4", "S 1", "E 4"});
        Test.check("r19", r19, new int[]{1, 4});

// 20. 여러 번의 정상 이동과 경계 초과를 함께 처리
        var r20 = sol.solution(new String[]{"OOOOO", "OOSOO", "OOOOO"}, new String[]{"W 2", "N 1", "E 4", "S 2"});
        Test.check("r20", r20, new int[]{2, 4});
    }
}
