package all.part8.ex4_negative_edges;

// https://judge.u-aizu.ac.jp/onlinejudge/description.jsp?id=GRL_1_B&lang=en

import helpers.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;
import java.util.function.Supplier;

class Solution {
    public String[] solution(String input) {
        try {
            BufferedReader br = new BufferedReader(new StringReader(input));
            StringTokenizer st = new StringTokenizer(br.readLine());

            int v = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());

            List<Edge> edges = new ArrayList<>();
            for (int i = 0; i < e; i++) {
                st = new StringTokenizer(br.readLine());
                Edge edge = new Edge(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
                edges.add(edge);
            }

            int[] distances = new int[v];
            Arrays.fill(distances, Integer.MAX_VALUE);
            distances[r] = 0;

            for (int i = 0; i < v; i++) {
                boolean updated = false;

                for (Edge edge : edges) {
                    if (distances[edge.from] == Integer.MAX_VALUE) {
                        continue;
                    }
                    int distance = distances[edge.from] + edge.weight;
                    if (distance >= distances[edge.to]) {
                        continue;
                    }
                    distances[edge.to] = distance;
                    updated = true;

                    if (i == v - 1) {
                        return new String[]{"NEGATIVE CYCLE"};
                    }
                }

                if (!updated) {
                    break;
                }
            }

            String[] result = new String[v];
            for (int i = 0; i < v; i++) {
                result[i] = distances[i] == Integer.MAX_VALUE ? "INF" : String.valueOf(distances[i]);
            }

            return result;
        } catch (IOException e) {
            return null;
        }
    }

    private record Edge(int from, int to, int weight) {

    }
}

public class Main {
    static void main(String[] args) {
        Solution sol = new Solution();

        // 1. 공식 예제 1 - 음수 간선이 있는 그래프
        var r1 = sol.solution("4 5 0\n0 1 2\n0 2 3\n1 2 -5\n1 3 1\n2 3 2");
        Test.check("r1", r1, new String[]{"0", "2", "-3", "-1"});

        // 2. 공식 예제 2 - 도달 가능한 음수 사이클
        var r2 = sol.solution("4 6 0\n0 1 2\n0 2 3\n1 2 -5\n1 3 1\n2 3 2\n3 1 0");
        Test.check("r2", r2, new String[]{"NEGATIVE CYCLE"});

        // 3. 공식 예제 3 - 시작 정점이 0이 아닌 경우
        var r3 = sol.solution("4 5 1\n0 1 2\n0 2 3\n1 2 -5\n1 3 1\n2 3 2");
        Test.check("r3", r3, new String[]{"INF", "0", "-5", "-3"});

        // 4. 최소 입력 - 정점 하나, 간선 없음
        var r4 = sol.solution("1 0 0");
        Test.check("r4", r4, new String[]{"0"});

        // 5. 간선이 전혀 없는 그래프
        var r5 = sol.solution("5 0 3");
        Test.check("r5", r5, new String[]{"INF", "INF", "INF", "0", "INF"});

        // 6. 음수 간선은 존재하지만 음수 사이클은 없는 그래프
        var r6 = sol.solution("5 4 0\n0 1 4\n1 2 -7\n0 3 2\n3 4 -1");
        Test.check("r6", r6, new String[]{"0", "4", "-3", "2", "1"});

        // 7. 시작점에서 도달할 수 없는 음수 사이클
        var r7 = sol.solution("6 5 0\n0 1 3\n1 2 4\n3 4 -5\n4 3 1\n4 5 -7");
        Test.check("r7", r7, new String[]{"0", "3", "7", "INF", "INF", "INF"});

        // 8. 도달할 수 없는 정점들 사이의 큰 음수 간선
        var r8 = sol.solution("5 2 0\n2 3 -10000\n3 4 -10000");
        Test.check("r8", r8, new String[]{"0", "INF", "INF", "INF", "INF"});

        // 9. 가중치 합이 정확히 0인 사이클
        var r9 = sol.solution("4 4 0\n0 1 2\n1 2 -5\n2 1 5\n2 3 2");
        Test.check("r9", r9, new String[]{"0", "2", "-3", "-1"});

        // 10. 음수 간선을 포함하지만 가중치 합이 양수인 사이클
        var r10 = sol.solution("4 4 0\n0 1 1\n1 2 -4\n2 1 5\n2 3 3");
        Test.check("r10", r10, new String[]{"0", "1", "-3", "0"});

        // 11. 시작점에서 여러 정점을 거쳐 도달하는 음수 사이클
        var r11 = sol.solution("6 6 0\n0 1 5\n1 2 3\n2 3 1\n3 4 -4\n4 3 3\n4 5 7");
        Test.check("r11", r11, new String[]{"NEGATIVE CYCLE"});

        // 12. 역순으로 배치된 간선 - 반복적인 완화가 필요
        var r12 = sol.solution("5 4 0\n3 4 -2\n2 3 -2\n1 2 -2\n0 1 -2");
        Test.check("r12", r12, new String[]{"0", "-2", "-4", "-6", "-8"});

        // 13. 나중에 발견한 음수 경로가 기존 경로보다 짧음
        var r13 = sol.solution("4 5 0\n0 1 1\n0 2 4\n2 1 -5\n1 3 1\n2 3 9");
        Test.check("r13", r13, new String[]{"0", "-1", "4", "0"});

        // 14. 시작 정점으로 되돌아오는 가중치 합 0의 사이클
        var r14 = sol.solution("3 3 0\n0 1 10\n1 2 -20\n2 0 10");
        Test.check("r14", r14, new String[]{"0", "10", "-10"});

        // 15. 정점 1000개, 가중치 -10000의 연속 간선, 최단 거리 -9990000
        StringBuilder sb15 = new StringBuilder("1000 999 0\n");
        String[] expected15 = new String[1000];
        for (int i = 0; i < 1000; i++) {
            expected15[i] = Integer.toString(-10000 * i);
            if (i < 999) sb15.append(i).append(' ').append(i + 1).append(" -10000\n");
        }
        String input15 = sb15.toString();
        Supplier<String[]> r15 = () -> sol.solution(input15);
        Test.check("r15", r15, expected15);

        // 16. 정점 1000개, 가중치 +10000의 연속 간선, 최단 거리 9990000
        StringBuilder sb16 = new StringBuilder("1000 999 0\n");
        String[] expected16 = new String[1000];
        for (int i = 0; i < 1000; i++) {
            expected16[i] = Integer.toString(10000 * i);
            if (i < 999) sb16.append(i).append(' ').append(i + 1).append(" 10000\n");
        }
        String input16 = sb16.toString();
        Supplier<String[]> r16 = () -> sol.solution(input16);
        Test.check("r16", r16, expected16);

        // 17. 최대 정점 1000개, 최대 간선 2000개, 최악에 가까운 반복 완화
        StringBuilder sb17 = new StringBuilder("1000 2000 999\n");
        for (int i = 1; i < 1000; i++) {
            sb17.append(i).append(' ').append(i - 1).append(" -1\n");
        }
        for (int i = 0; i < 998; i++) {
            sb17.append(i).append(' ').append(i + 2).append(" 10000\n");
        }
        for (int i = 0; i < 3; i++) {
            sb17.append(i).append(' ').append(i + 3).append(" 10000\n");
        }
        String[] expected17 = new String[1000];
        for (int i = 0; i < 1000; i++) {
            expected17[i] = Integer.toString(i - 999);
        }
        String input17 = sb17.toString();
        Supplier<String[]> r17 = () -> sol.solution(input17);
        Test.check("r17", r17, expected17);

        // 18. 최대 정점 1000개, 최대 간선 2000개, 마지막에 발견되는 음수 사이클
        StringBuilder sb18 = new StringBuilder("1000 2000 999\n");
        for (int i = 1; i < 1000; i++) {
            sb18.append(i).append(' ').append(i - 1).append(" -1\n");
        }
        for (int i = 0; i < 998; i++) {
            sb18.append(i).append(' ').append(i + 2).append(" 10000\n");
        }
        for (int i = 0; i < 2; i++) {
            sb18.append(i).append(' ').append(i + 3).append(" 10000\n");
        }
        sb18.append("0 999 998\n");
        String input18 = sb18.toString();
        Supplier<String[]> r18 = () -> sol.solution(input18);
        Test.check("r18", r18, new String[]{"NEGATIVE CYCLE"});
    }
}
