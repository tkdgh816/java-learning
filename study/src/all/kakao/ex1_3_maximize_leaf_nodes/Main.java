package all.kakao.ex1_3_maximize_leaf_nodes;

import helpers.Test;
import java.util.function.Supplier;

// K1-3 리프 노드 수 최대화
// 문제: https://school.programmers.co.kr/learn/courses/30/lessons/468372
// PDF 출처: https://tech.kakao.com/posts/813
class Solution {
    public int solution(int dist_limit, int split_limit) {
        // TODO: 직접 구현하세요.
        return 0;
    }
}

public class Main {
    public static void main(String[] args) {
        Solution sol = new Solution();

        // 공식 예제 1
        Supplier<Integer> r1 = () -> sol.solution(3, 6);
        Test.check("r1", r1, 6);

        // 공식 예제 2
        Supplier<Integer> r2 = () -> sol.solution(0, 10);
        Test.check("r2", r2, 1);

        // 공식 예제 3
        Supplier<Integer> r3 = () -> sol.solution(3, 100);
        Test.check("r3", r3, 7);

        // 공식 예제 4
        Supplier<Integer> r4 = () -> sol.solution(5, 16);
        Test.check("r4", r4, 9);
    }
}
