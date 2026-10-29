package all.part8.op2_delivery;

// https://school.programmers.co.kr/learn/courses/30/lessons/12978

import helpers.Test;

import java.util.*;
import java.util.function.Supplier;

class Solution {
    public int solution(int N, int[][] road, int K) {
        List<List<Edge>> graph = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }
        for (int[] r : road) {
            graph.get(r[0]).add(new Edge(r[1], r[2]));
            graph.get(r[1]).add(new Edge(r[0], r[2]));
        }

        int[] times = new int[N + 1];
        Arrays.fill(times, Integer.MAX_VALUE);
        times[1] = 0;

        PriorityQueue<State> pq = new PriorityQueue<>(Comparator.comparing(State::time));
        pq.offer(new State(1, times[1]));

        while (!pq.isEmpty()) {
            State current = pq.poll();
            if (current.time > times[current.node()]) {
                continue;
            }

            for (Edge edge : graph.get(current.node)) {
                int time = times[current.node()] + edge.weight();
                if (time >= times[edge.to()]) {
                    continue;
                }
                times[edge.to()] = time;
                pq.offer(new State(edge.to, time));
            }
        }

        return Arrays.stream(times).reduce(0, (count, time) -> count + (time <= K ? 1 : 0));
    }

    private record Edge(int to, int weight) {

    }

    private record State(int node, int time) {

    }
}

public class Main {
    static void main(String[] args) {
        Solution sol = new Solution();

// 1. 공식 예제 1
        var r1 = sol.solution(5, new int[][]{{1, 2, 1}, {2, 3, 3}, {5, 2, 2}, {1, 4, 2}, {5, 3, 1}, {5, 4, 2}}, 3);
        Test.check("r1", r1, 4);

// 2. 공식 예제 2 (중복 간선 포함)
        var r2 = sol.solution(6, new int[][]{{1, 2, 1}, {1, 3, 2}, {2, 3, 2}, {3, 4, 3}, {3, 5, 2}, {3, 5, 3}, {5, 6, 1}}, 4);
        Test.check("r2", r2, 4);

// 3. 최소 마을 수, 시작 마을만 배달 가능
        var r3 = sol.solution(2, new int[][]{{1, 2, 10000}}, 1);
        Test.check("r3", r3, 1);

// 4. 도달 시간이 정확히 K인 경우
        var r4 = sol.solution(2, new int[][]{{1, 2, 10000}}, 10000);
        Test.check("r4", r4, 2);

// 5. 일직선 도로, 모든 마을 도달 가능
        var r5 = sol.solution(5, new int[][]{{1, 2, 1}, {2, 3, 1}, {3, 4, 1}, {4, 5, 1}}, 4);
        Test.check("r5", r5, 5);

// 6. 일직선 도로, 일부 마을만 도달 가능
        var r6 = sol.solution(5, new int[][]{{1, 2, 1}, {2, 3, 1}, {3, 4, 1}, {4, 5, 1}}, 3);
        Test.check("r6", r6, 4);

// 7. 직접 연결된 도로보다 우회 경로가 빠른 경우
        var r7 = sol.solution(4, new int[][]{{1, 2, 10}, {1, 3, 2}, {3, 2, 2}, {2, 4, 1}}, 5);
        Test.check("r7", r7, 4);

// 8. 같은 두 마을 사이에 가중치가 다른 도로가 여러 개 존재
        var r8 = sol.solution(3, new int[][]{{1, 2, 9}, {1, 2, 1}, {2, 3, 2}, {1, 3, 8}}, 3);
        Test.check("r8", r8, 3);

// 9. 양방향 도로 처리 검증
        var r9 = sol.solution(4, new int[][]{{2, 1, 2}, {3, 2, 2}, {4, 3, 2}}, 4);
        Test.check("r9", r9, 3);

// 10. 여러 마을을 거치는 최단 경로
        var r10 = sol.solution(5, new int[][]{{1, 2, 5}, {1, 3, 1}, {3, 4, 1}, {4, 2, 1}, {2, 5, 1}}, 3);
        Test.check("r10", r10, 4);

// 11. 최대 마을 수, 최대 가중치, 일부 마을만 도달 가능
        Supplier<Integer> r11 = () -> {
            int[][] road = new int[49][3];
            for (int i = 0; i < 49; i++) road[i] = new int[]{i + 1, i + 2, 10000};
            return sol.solution(50, road, 250000);
        };
        Test.check("r11", r11, 26);

// 12. 최대 마을 수, 최대 가중치, 최대 K
        Supplier<Integer> r12 = () -> {
            int[][] road = new int[49][3];
            for (int i = 0; i < 49; i++) road[i] = new int[]{i + 1, i + 2, 10000};
            return sol.solution(50, road, 500000);
        };
        Test.check("r12", r12, 50);

// 13. 최대 도로 수 2000개, 중복 간선 포함, 일부 마을만 도달 가능
        Supplier<Integer> r13 = () -> {
            int[][] road = new int[2000][3];
            int index = 0;
            for (int i = 1; i <= 50; i++) {
                for (int j = i + 1; j <= 50; j++) {
                    road[index++] = new int[]{i, j, i == 1 && j <= 25 ? 1 : 10000};
                }
            }
            outer:
            for (int i = 1; i <= 50; i++) {
                for (int j = i + 1; j <= 50; j++) {
                    if (index == 2000) break outer;
                    road[index++] = new int[]{i, j, 9999};
                }
            }
            return sol.solution(50, road, 1);
        };
        Test.check("r13", r13, 25);
    }
}
