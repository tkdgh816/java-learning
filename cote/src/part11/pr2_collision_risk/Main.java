package part11.pr2_collision_risk;

// 수업자료 22페이지: 충돌위험 찾기
// https://school.programmers.co.kr/learn/courses/30/lessons/340211

import helpers.Test;

import java.util.*;

class Solution {
    public int solution(int[][] points, int[][] routes) {
        int collisions = 0;

        List<Robot> robots = new ArrayList<>(routes.length);

        // 각 로봇의 경로들로 로봇 목록 생성
        for (int[] route : routes) {
            List<int[]> list = Arrays.stream(route)
                    .mapToObj(p -> points[p - 1])
                    .toList();
            robots.add(new Robot(list));
        }

        while (true) {
            // 매 시행 시 각 로봇의 위치별 로봇 수를 집계
            // 최초 시행 시에는 이동하지 않고 출발 위치를 반환 (Robot.move 메서드에서 구현)
            // 운송을 마친 로봇은 null을 반환하므로 집계에서 제외
            Map<Point, Integer> countsByPoint = new HashMap<>();
            for (Robot robot : robots) {
                Point point = robot.move();
                if (point != null) {
                    countsByPoint.merge(point, 1, Integer::sum);
                }
            }

            // 모든 로봇이 이동을 마치면 종료
            if (countsByPoint.isEmpty()) {
                break;
            }

            // 특정 위치에 로봇이 2대 이상 있으면
            // 그 위치에서 충돌이 일어난 것임
            for (int value : countsByPoint.values()) {
                if (value > 1) {
                    collisions++;
                }
            }
        }

        return collisions;
    }

    private static class Robot {
        private final int[] current;
        private final List<int[]> points;

        // 현재 목표 경유지의 인덱스
        // 현재 목표 지점의 인덱스가 x이면 x단계로 생각함
        private int phase = 0;

        Robot(List<int[]> points) {
            // 이동 중 원본 경유지 좌표가 변경되지 않도록 복사
            current = points.getFirst().clone();
            this.points = points;
        }

        public Point move() {
            // 이동이 끝났을 때
            if (phase == points.size()) {
                return null;
            }

            // 목표 지점
            int[] destination = points.get(phase);

            // 목표 지점에 도달하지 못했을 때
            // 그 방향으로 한 칸 이동 (row가 col보다 먼저)
            int rowCmp = Integer.compare(current[0], destination[0]);
            if (rowCmp < 0) {
                current[0]++;
            } else if (rowCmp > 0) {
                current[0]--;
            } else {
                int colCmp = Integer.compare(current[1], destination[1]);
                if (colCmp < 0) {
                    current[1]++;
                } else if (colCmp > 0) {
                    current[1]--;
                }
            }

            // 한 칸 이동 후 목표 지점에 도달하면
            // 다음 단계로 넘어감
            if (current[0] == destination[0] && current[1] == destination[1]) {
                phase++;
            }

            // 이동 후의 지점을 반환
            return new Point(current[0], current[1]);
        }
    }

    // Map의 key 동등성 판단을 위해 record 사용
    record Point(int row, int col) {
    }
}

public class Main {
    static void main(String[] args) {
        Solution sol = new Solution();

        // 1. 공식 예제 1
        var r1 = sol.solution(new int[][]{{3, 2}, {6, 4}, {4, 7}, {1, 4}}, new int[][]{{4, 2}, {1, 3}, {2, 4}});
        Test.check("r1", r1, 1);

        // 2. 공식 예제 2
        var r2 = sol.solution(new int[][]{{3, 2}, {6, 4}, {4, 7}, {1, 4}}, new int[][]{{4, 2}, {1, 3}, {4, 2}, {4, 3}});
        Test.check("r2", r2, 9);

        // 3. 공식 예제 3
        var r3 = sol.solution(new int[][]{{2, 2}, {2, 3}, {2, 7}, {6, 6}, {5, 2}}, new int[][]{{2, 3, 4, 5}, {1, 3, 4, 5}});
        Test.check("r3", r3, 0);

        // 4. 시작점만 동일한 경우 (0초 충돌)
        var r4 = sol.solution(new int[][]{{1, 1}, {1, 3}, {3, 1}}, new int[][]{{1, 2}, {1, 3}});
        Test.check("r4", r4, 1);

        // 5. 두 로봇의 전체 이동 경로가 동일한 경우
        var r5 = sol.solution(new int[][]{{1, 1}, {1, 4}}, new int[][]{{1, 2}, {1, 2}});
        Test.check("r5", r5, 4);

        // 6. 세 로봇이 동시에 충돌해도 시간당 한 번만 계산
        var r6 = sol.solution(new int[][]{{1, 1}, {1, 3}}, new int[][]{{1, 2}, {1, 2}, {1, 2}});
        Test.check("r6", r6, 3);

        // 7. 동일한 시각에 서로 다른 두 좌표에서 충돌
        var r7 = sol.solution(new int[][]{{1, 1}, {1, 3}, {3, 1}, {3, 3}}, new int[][]{{1, 2}, {1, 2}, {3, 4}, {3, 4}});
        Test.check("r7", r7, 6);

        // 8. 서로 수직으로 이동하는 로봇이 중간 지점에서 충돌
        var r8 = sol.solution(new int[][]{{1, 2}, {3, 2}, {2, 1}, {2, 3}}, new int[][]{{1, 2}, {3, 4}});
        Test.check("r8", r8, 1);

        // 9. 서로 위치를 교환하는 것은 충돌이 아님
        var r9 = sol.solution(new int[][]{{1, 1}, {1, 2}}, new int[][]{{1, 2}, {2, 1}});
        Test.check("r9", r9, 0);

        // 10. c보다 r 좌표를 먼저 이동해야 발생하는 충돌
        var r10 = sol.solution(new int[][]{{1, 1}, {2, 2}, {3, 1}, {1, 3}}, new int[][]{{1, 2}, {3, 4}});
        Test.check("r10", r10, 1);

        // 11. 운송을 완료한 로봇은 이후 충돌 대상에서 제외
        var r11 = sol.solution(new int[][]{{1, 1}, {1, 2}, {1, 5}}, new int[][]{{1, 2}, {3, 2}});
        Test.check("r11", r11, 0);

        // 12. 중간 경유지에서 충돌하고 대기 없이 다음 지점으로 이동
        var r12 = sol.solution(new int[][]{{1, 1}, {1, 2}, {1, 3}}, new int[][]{{1, 2, 3}, {3, 2, 1}});
        Test.check("r12", r12, 1);

        // 13. 로봇이 경로를 역방향으로 재방문하지만 충돌하지 않음
        var r13 = sol.solution(new int[][]{{1, 1}, {1, 2}}, new int[][]{{1, 2, 1}, {2, 1, 2}});
        Test.check("r13", r13, 0);

        // 14. 이전에 방문한 지점으로 복귀하며 여러 번 충돌
        var r14 = sol.solution(new int[][]{{1, 1}, {1, 2}, {1, 3}}, new int[][]{{1, 2, 1, 2}, {3, 2, 1, 2}});
        Test.check("r14", r14, 3);

        // 15. 최대 좌표와 긴 이동시간 검증 (0초부터 198초까지 충돌)
        var r15 = sol.solution(new int[][]{{1, 1}, {100, 100}}, new int[][]{{1, 2}, {1, 2}});
        Test.check("r15", r15, 199);

        // 16. 두 로봇이 최종 목적지에 동시에 도착하여 충돌
        var r16 = sol.solution(new int[][]{{1, 1}, {1, 3}, {1, 2}}, new int[][]{{1, 3}, {2, 3}});
        Test.check("r16", r16, 1);
    }
}
