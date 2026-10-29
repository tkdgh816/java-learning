package all.part8.ex3_cheapest_flights;

// https://leetcode.com/problems/cheapest-flights-within-k-stops/

import helpers.Test;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.function.Supplier;

class Solution {
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        List<List<Edge>> graph = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int[] flight : flights) {
            graph.get(flight[0]).add(new Edge(flight[1], flight[2]));
        }

        Distance[] distances = new Distance[n];
        for (int i = 0; i < n; i++) {
            distances[i] = new Distance(i);
        }

        distances[src].stops = -1;
        Queue<Distance> queue = new ArrayDeque<>();
        queue.offer(distances[src]);

        while (!queue.isEmpty()) {
            Distance current = queue.poll();
            if (current.stops < Integer.MAX_VALUE) {
                continue;
            }
            for(Edge edge : graph.get(current.node)){
                distances[edge.to].stops = distances[current.node].stops + 1;
                queue.offer(distances[edge.to]);
            }
        }

        return 0;
    }

    private record Edge(int to, int weight) {

    }

    private class Distance {
        public final int node;
        public int price = Integer.MAX_VALUE;
        public int stops = Integer.MAX_VALUE;

        Distance(int node){
            this.node = node;
        }
    }
}

public class Main {
    static void main(String[] args) {
        Solution sol = new Solution();

// 1. 기본 예제 1 - 저렴한 경로가 경유 제한을 초과
        var r1 = sol.findCheapestPrice(4, new int[][]{{0, 1, 100}, {1, 2, 100}, {2, 0, 100}, {1, 3, 600}, {2, 3, 200}}, 0, 3, 1);
        Test.check("r1", r1, 700);

// 2. 기본 예제 2 - 경유하는 경로가 더 저렴
        var r2 = sol.findCheapestPrice(3, new int[][]{{0, 1, 100}, {1, 2, 100}, {0, 2, 500}}, 0, 2, 1);
        Test.check("r2", r2, 200);

// 3. 기본 예제 3 - 경유 불가능
        var r3 = sol.findCheapestPrice(3, new int[][]{{0, 1, 100}, {1, 2, 100}, {0, 2, 500}}, 0, 2, 0);
        Test.check("r3", r3, 500);

// 4. 항공편이 없는 경우
        var r4 = sol.findCheapestPrice(2, new int[][]{}, 0, 1, 0);
        Test.check("r4", r4, -1);

// 5. 최소 도시 수, 최소 비용
        var r5 = sol.findCheapestPrice(2, new int[][]{{0, 1, 1}}, 0, 1, 0);
        Test.check("r5", r5, 1);

// 6. 항공편의 방향을 구분해야 하는 경우
        var r6 = sol.findCheapestPrice(2, new int[][]{{1, 0, 5}}, 0, 1, 1);
        Test.check("r6", r6, -1);

// 7. 출발지와 목적지가 서로 다른 연결 요소에 있는 경우
        var r7 = sol.findCheapestPrice(4, new int[][]{{0, 1, 50}, {1, 0, 50}, {2, 3, 5}}, 0, 3, 3);
        Test.check("r7", r7, -1);

// 8. 경유 제한보다 한 번 더 경유해야 도달 가능한 경우
        var r8 = sol.findCheapestPrice(4, new int[][]{{0, 1, 10}, {1, 2, 10}, {2, 3, 10}}, 0, 3, 1);
        Test.check("r8", r8, -1);

// 9. 정확히 k번 경유해서 도달하는 경우
        var r9 = sol.findCheapestPrice(4, new int[][]{{0, 1, 10}, {1, 2, 10}, {2, 3, 10}}, 0, 3, 2);
        Test.check("r9", r9, 30);

// 10. 경유 횟수가 적은 경로를 선택해야 하는 경우
        var r10 = sol.findCheapestPrice(5, new int[][]{{0, 4, 900}, {0, 1, 100}, {1, 4, 400}, {0, 2, 50}, {2, 3, 50}, {3, 4, 50}}, 0, 4, 1);
        Test.check("r10", r10, 500);

// 11. 경유 횟수를 늘려 더 저렴한 경로를 선택하는 경우
        var r11 = sol.findCheapestPrice(5, new int[][]{{0, 4, 900}, {0, 1, 100}, {1, 4, 400}, {0, 2, 50}, {2, 3, 50}, {3, 4, 50}}, 0, 4, 2);
        Test.check("r11", r11, 150);

// 12. 동일 도시에 더 비싸게 도착한 경로가 최적해가 되는 경우
        var r12 = sol.findCheapestPrice(4, new int[][]{{0, 1, 5}, {0, 2, 2}, {2, 1, 2}, {1, 3, 2}, {0, 3, 100}}, 0, 3, 1);
        Test.check("r12", r12, 7);

// 13. 동일 도시에 더 저렴하게 도착한 경로가 최적해가 되는 경우
        var r13 = sol.findCheapestPrice(4, new int[][]{{0, 1, 5}, {0, 2, 2}, {2, 1, 2}, {1, 3, 2}, {0, 3, 100}}, 0, 3, 2);
        Test.check("r13", r13, 6);

// 14. 순환 경로가 존재하는 경우
        var r14 = sol.findCheapestPrice(4, new int[][]{{0, 1, 5}, {1, 0, 1}, {1, 2, 5}, {2, 1, 1}, {2, 3, 5}, {0, 3, 100}}, 0, 3, 3);
        Test.check("r14", r14, 15);

// 15. 항공편이 경로의 역순으로 주어진 경우
        var r15 = sol.findCheapestPrice(4, new int[][]{{2, 3, 1}, {1, 2, 1}, {0, 1, 1}, {0, 3, 50}}, 0, 3, 2);
        Test.check("r15", r15, 3);


// 큰 입력 테스트용 데이터 1 - 100개 도시가 일렬로 연결된 그래프
        int[][] chainFlights = new int[99][3];
        for (int i = 0; i < 99; i++) {
            chainFlights[i] = new int[]{98 - i, 99 - i, 10_000};
        }

// 16. 경유 제한 때문에 마지막 도시에 도달할 수 없는 경우
        Supplier<Integer> r16 = () -> sol.findCheapestPrice(100, chainFlights, 0, 99, 97);
        Test.check("r16", r16, -1);

// 17. 최대 경유 횟수가 필요하고 누적 비용이 큰 경우
        Supplier<Integer> r17 = () -> sol.findCheapestPrice(100, chainFlights, 0, 99, 98);
        Test.check("r17", r17, 990_000);


// 큰 입력 테스트용 데이터 2 - 최대 항공편 수 4,950개의 밀집 그래프
        int[][] denseFlights = new int[4950][3];
        int index = 0;
        for (int from = 0; from < 100; from++) {
            for (int to = from + 1; to < 100; to++) {
                denseFlights[index++] = new int[]{from, to, to == from + 1 ? 1 : (to - from) * 100};
            }
        }

// 18. 최대 항공편 수, 경유 불가능
        Supplier<Integer> r18 = () -> sol.findCheapestPrice(100, denseFlights, 0, 99, 0);
        Test.check("r18", r18, 9_900);

// 19. 최대 항공편 수, 최적 경로보다 경유 제한이 1 작은 경우
        Supplier<Integer> r19 = () -> sol.findCheapestPrice(100, denseFlights, 0, 99, 97);
        Test.check("r19", r19, 297);

// 20. 최대 항공편 수, 긴 경로를 이용해야 최소 비용인 경우
        Supplier<Integer> r20 = () -> sol.findCheapestPrice(100, denseFlights, 0, 99, 98);
        Test.check("r20", r20, 99);

// 21. 최대 항공편 수, 최대 k, 목적지에 도달할 수 없는 경우
        Supplier<Integer> r21 = () -> sol.findCheapestPrice(100, denseFlights, 99, 0, 99);
        Test.check("r21", r21, -1);
    }
}
