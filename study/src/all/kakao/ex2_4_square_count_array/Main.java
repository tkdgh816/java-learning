package all.kakao.ex2_4_square_count_array;

import helpers.Test;
import java.util.function.Supplier;

// K2-4 제곱 개수 배열
// 문제: https://school.programmers.co.kr/learn/courses/30/lessons/468380
// PDF 출처: https://tech.kakao.com/posts/814
class Solution {
    public long[] solution(int[] arr, long l, long r) {
        // TODO: 직접 구현하세요.
        return new long[0];
    }
}

public class Main {
    public static void main(String[] args) {
        Solution sol = new Solution();

        // 공식 예제 1
        Supplier<long[]> r1 = () -> sol.solution(new int[] { 3, 2, 3, 1, 1 }, 5L, 7L);
        Test.check("r1", r1, new long[] { 8L, 2L });

        // 공식 예제 2
        Supplier<long[]> r2 = () -> sol.solution(new int[] { 2, 2, 2 }, 2L, 2L);
        Test.check("r2", r2, new long[] { 2L, 6L });

        // 공식 예제 3
        Supplier<long[]> r3 = () -> sol.solution(new int[] { 8, 8, 6, 5, 2, 9, 8, 4, 3, 10 }, 25L, 27L);
        Test.check("r3", r3, new long[] { 15L, 3L });

        // 공식 예제 4
        Supplier<long[]> r4 = () -> sol.solution(new int[] { 70195, 25471, 7389, 58187, 18454, 90532, 97667, 17148, 91636, 2810 }, 126058L, 462933L);
        Test.check("r4", r4, new long[] { 27554327568L, 1L });

        // 공식 예제 5
        Supplier<long[]> r5 = () -> sol.solution(new int[] { 16952, 70276, 16771, 37992, 87549, 54906, 36718, 20478, 57088, 27916, 51509, 83422, 51707, 18807, 80859, 2673, 37734, 93380 }, 149845L, 228204L);
        Test.check("r5", r5, new long[] { 6860339640L, 9190L });

        // 공식 예제 6
        Supplier<long[]> r6 = () -> sol.solution(new int[] { 49134, 86806, 94548, 88849, 95022, 28334, 16637, 79487, 23773, 7314, 47370, 50269, 36573, 9415, 44674, 28096 }, 61242L, 88535L);
        Test.check("r6", r6, new long[] { 2369282964L, 59513L });
    }
}
