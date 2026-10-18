package part7.ex2_course_schedule;

// 수업자료 54페이지: Course Schedule
// https://leetcode.com/problems/course-schedule/description/

import helpers.Test;

import java.util.*;

class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            graph.add(new ArrayList<>());
        }

        int[] indegree = new int[numCourses];

        for (int[] prerequisite : prerequisites) {
            graph.get(prerequisite[1]).add(prerequisite[0]);
            indegree[prerequisite[0]]++;
        }

        Queue<Integer> queue = new ArrayDeque<>();
        for (int i = 0; i < numCourses; i++) {
            if (indegree[i] == 0) {
                queue.offer(i);
            }
        }

        int count = 0;
        while (!queue.isEmpty()) {
            int current = queue.poll();
            count++;
            for (int next : graph.get(current)) {
                if (--indegree[next] == 0) {
                    queue.offer(next);
                }
            }
        }

        return count == numCourses;
    }
}


public class Main {
    static void main(String[] args) {
        Solution sol = new Solution();

// 공식 예제 1
        var r1 = sol.canFinish(2, new int[][]{{1, 0}});
        Test.check("r1", r1, true);

// 공식 예제 2
        var r2 = sol.canFinish(2, new int[][]{{1, 0}, {0, 1}});
        Test.check("r2", r2, false);

// 과목이 하나이고 선수 과목이 없는 경우
        var r3 = sol.canFinish(1, new int[][]{});
        Test.check("r3", r3, true);

// 자기 자신을 선수 과목으로 요구하는 경우
        var r4 = sol.canFinish(1, new int[][]{{0, 0}});
        Test.check("r4", r4, false);

// 여러 과목이 있지만 선수 과목 관계가 없는 경우
        var r5 = sol.canFinish(5, new int[][]{});
        Test.check("r5", r5, true);

// 일자 형태의 선수 과목 관계
        var r6 = sol.canFinish(5, new int[][]{{1, 0}, {2, 1}, {3, 2}, {4, 3}});
        Test.check("r6", r6, true);

// 모든 과목이 하나의 사이클을 이루는 경우
        var r7 = sol.canFinish(5, new int[][]{{1, 0}, {2, 1}, {3, 2}, {4, 3}, {0, 4}});
        Test.check("r7", r7, false);

// 분리된 컴포넌트들이 모두 사이클이 없는 경우
        var r8 = sol.canFinish(5, new int[][]{{1, 0}, {3, 2}});
        Test.check("r8", r8, true);

// 분리된 컴포넌트 중 하나에 사이클이 존재하는 경우
        var r9 = sol.canFinish(6, new int[][]{{1, 0}, {2, 1}, {0, 2}, {4, 3}, {5, 4}});
        Test.check("r9", r9, false);

// 다이아몬드 형태의 선수 과목 관계
        var r10 = sol.canFinish(4, new int[][]{{1, 0}, {2, 0}, {3, 1}, {3, 2}});
        Test.check("r10", r10, true);

// 다이아몬드 형태에 역방향 연결이 추가되어 사이클이 발생
        var r11 = sol.canFinish(4, new int[][]{{1, 0}, {2, 0}, {3, 1}, {3, 2}, {0, 3}});
        Test.check("r11", r11, false);

// 하나의 과목에 여러 선수 과목이 필요한 경우
        var r12 = sol.canFinish(6, new int[][]{{4, 0}, {4, 1}, {4, 2}, {5, 3}, {5, 4}});
        Test.check("r12", r12, true);

// 긴 경로 중간으로 돌아가는 간선이 존재하는 경우
        var r13 = sol.canFinish(6, new int[][]{{1, 0}, {2, 1}, {3, 2}, {4, 3}, {5, 4}, {2, 5}});
        Test.check("r13", r13, false);

// 분기된 그래프의 일부 경로에서 사이클이 발생하는 경우
        var r14 = sol.canFinish(6, new int[][]{{1, 0}, {2, 0}, {3, 1}, {4, 2}, {5, 3}, {3, 5}});
        Test.check("r14", r14, false);

// 간선 입력 순서가 선수 과목 이수 순서와 다른 경우
        var r15 = sol.canFinish(8, new int[][]{{7, 5}, {6, 4}, {5, 3}, {4, 2}, {3, 1}, {2, 0}, {7, 6}});
        Test.check("r15", r15, true);

// 여러 컴포넌트 중 일부에만 사이클이 존재하는 경우
        var r16 = sol.canFinish(8, new int[][]{{1, 0}, {2, 1}, {3, 2}, {4, 5}, {5, 6}, {6, 4}, {7, 3}});
        Test.check("r16", r16, false);

// 무방향 그래프에서는 사이클이지만 유향 그래프에서는 DAG인 경우
        var r17 = sol.canFinish(3, new int[][]{{1, 0}, {2, 0}, {2, 1}});
        Test.check("r17", r17, true);

// 시작 노드가 아닌 중간 노드로 되돌아가는 사이클
        var r18 = sol.canFinish(4, new int[][]{{1, 0}, {2, 1}, {3, 2}, {1, 3}});
        Test.check("r18", r18, false);

// 높은 과목 번호를 사용하는 비순환 그래프
        var r19 = sol.canFinish(2000, new int[][]{{1999, 1500}, {1500, 1000}, {1000, 500}, {500, 0}, {1998, 1999}});
        Test.check("r19", r19, true);

// 높은 과목 번호를 사용하는 순환 그래프
        var r20 = sol.canFinish(2000, new int[][]{{1999, 1500}, {1500, 1000}, {1000, 500}, {500, 1999}});
        Test.check("r20", r20, false);
    }
}