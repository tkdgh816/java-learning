package part9.pr_knapsack;

// 수업자료 25페이지: D - Knapsack 1
// https://atcoder.jp/contests/dp/tasks/dp_d

import helpers.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 최대가 되어야 하는 것은 v들의 합이지 w들의 합이 아님
// w들의 합이 W 이하가 되는 모든 조합들 중에서 v들의 합이 가장 큰 것을 찾는 문제
// w들의 합이 x가 되는 모든 조합들 중에서 v들의 합의 최댓값을 f(x)라 하면
//

class Solution {
    public long solution(int w, int[][] items) {
        int n = items.length;

        return 0;
    }
}

public class Main {
    static void main(String[] args) throws IOException {
        Solution sol = new Solution();

        if (false) {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            int[][] items = new int[n][2];
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                items[i][0] = Integer.parseInt(st.nextToken());
                items[i][1] = Integer.parseInt(st.nextToken());
            }
            var r0 = sol.solution(w, items);
            System.out.println(r0);
            return;
        }

// 1. 공식 예제 1
        var r1 = sol.solution(8, new int[][]{{3, 30}, {4, 50}, {5, 60}});
        Test.check("r1", r1, 90);

// 2. 공식 예제 2 - int 범위 초과
        var r2 = sol.solution(5, new int[][]{{1, 1000000000}, {1, 1000000000}, {1, 1000000000}, {1, 1000000000}, {1, 1000000000}});
        Test.check("r2", r2, 5000000000L);

// 3. 공식 예제 3
        var r3 = sol.solution(15, new int[][]{{6, 5}, {5, 6}, {6, 4}, {6, 6}, {3, 5}, {7, 2}});
        Test.check("r3", r3, 17);

// 4. 물건이 하나이고 정확히 담을 수 있는 경우
        var r4 = sol.solution(1, new int[][]{{1, 7}});
        Test.check("r4", r4, 7);

// 5. 모든 물건을 담을 수 있는 경우
        var r5 = sol.solution(10, new int[][]{{2, 4}, {3, 5}, {5, 6}});
        Test.check("r5", r5, 15);

// 6. 가장 가치가 높은 물건 하나를 선택하면 오답인 경우
        var r6 = sol.solution(10, new int[][]{{10, 100}, {6, 61}, {4, 60}});
        Test.check("r6", r6, 121);

// 7. 가치 대비 무게 비율을 기준으로 한 탐욕법의 반례
        var r7 = sol.solution(50, new int[][]{{10, 60}, {20, 100}, {30, 120}});
        Test.check("r7", r7, 220);

// 8. 배낭의 무게를 끝까지 채우지 않는 것이 최적인 경우
        var r8 = sol.solution(7, new int[][]{{3, 5}, {4, 6}, {5, 100}});
        Test.check("r8", r8, 100);

// 9. 무게와 가치가 동일한 물건들이 여러 개 있는 경우
        var r9 = sol.solution(9, new int[][]{{3, 8}, {3, 8}, {3, 8}, {3, 8}});
        Test.check("r9", r9, 24);

// 10. 동일한 총무게에서 가치가 더 높은 물건을 선택해야 하는 경우
        var r10 = sol.solution(8, new int[][]{{8, 15}, {4, 7}, {4, 7}});
        Test.check("r10", r10, 15);

// 11. 하나의 물건을 여러 번 선택하면 안 되는 경우
        var r11 = sol.solution(6, new int[][]{{3, 10}});
        Test.check("r11", r11, 10);

// 12. 여러 물건 중 하나만 담을 수 있는 경우
        var r12 = sol.solution(8, new int[][]{{5, 9}, {6, 10}, {7, 11}});
        Test.check("r12", r12, 11);

// 13. 배낭에 남는 공간이 있어도 최적해인 경우
        var r13 = sol.solution(10, new int[][]{{6, 13}, {7, 14}, {9, 15}});
        Test.check("r13", r13, 15);

// 14. 선택한 물건의 가치 합계가 int 범위를 초과하는 경우
        var r14 = sol.solution(3, new int[][]{{1, 1000000000}, {1, 1000000000}, {1, 1000000000}, {1, 1000000000}});
        Test.check("r14", r14, 3000000000L);

// 15. N과 W가 모두 최댓값인 경우
        int[][] largeItems = new int[100][2];
        for (int i = 0; i < 100; i++) largeItems[i] = new int[]{1000, 1000000000};
        var r15 = sol.solution(100000, largeItems);
        Test.check("r15", r15, 100000000000L);
    }
}
