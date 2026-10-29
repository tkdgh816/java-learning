package all.part0.op1_collatz;

// https://school.programmers.co.kr/learn/courses/30/lessons/12943

import helpers.Test;

import java.util.function.Supplier;

class Solution {
    public int solution(int num) {
        if (num == 1) {
            return 0;
        }

        long number = num;
        int count = 0;

        while (count++ < 500) {
            number = number % 2 == 0 ? number / 2 : number * 3 + 1;
            if (number == 1) {
                return count;
            }
        }

        return -1;
    }
}

public class Main {
    static void main(String[] args) {
        Solution sol = new Solution();

// 1. 기본 예제 1
        var r1 = sol.solution(6);
        Test.check("r1", r1, 8);

// 2. 기본 예제 2
        var r2 = sol.solution(16);
        Test.check("r2", r2, 4);

// 3. 기본 예제 3
        var r3 = sol.solution(626331);
        Test.check("r3", r3, -1);

// 4. 최솟값 (이미 1인 경우)
        var r4 = sol.solution(1);
        Test.check("r4", r4, 0);

// 5. 한 번의 연산으로 1이 되는 경우
        var r5 = sol.solution(2);
        Test.check("r5", r5, 1);

// 6. 작은 홀수
        var r6 = sol.solution(3);
        Test.check("r6", r6, 7);

// 7. 작은 짝수
        var r7 = sol.solution(4);
        Test.check("r7", r7, 2);

// 8. 2의 거듭제곱
        var r8 = sol.solution(8);
        Test.check("r8", r8, 3);

// 9. 연산 과정에서 값이 크게 증가하는 경우
        var r9 = sol.solution(27);
        Test.check("r9", r9, 111);

// 10. 비교적 긴 연산
        var r10 = sol.solution(97);
        Test.check("r10", r10, 118);

// 11. 긴 연산
        var r11 = sol.solution(871);
        Test.check("r11", r11, 178);

// 12. 더 긴 연산
        var r12 = sol.solution(6171);
        Test.check("r12", r12, 261);

// 13. 연산 도중 int 범위를 초과하는 경우
        var r13 = sol.solution(113383);
        Test.check("r13", r13, 247);

// 14. 정확히 499회 연산하는 경우
        var r14 = sol.solution(1585403);
        Test.check("r14", r14, 499);

// 15. 정확히 500회 연산하는 경우
        var r15 = sol.solution(3027113);
        Test.check("r15", r15, 500);

// 16. 501회 연산해야 1이 되는 경우
        var r16 = sol.solution(1056935);
        Test.check("r16", r16, -1);

// 17. 500회를 초과하는 또 다른 경우
        var r17 = sol.solution(837799);
        Test.check("r17", r17, -1);

// 18. 입력 최댓값
        Supplier<Integer> r18 = () -> sol.solution(7999999);
        Test.check("r18", r18, 261);

// 19. 반복 횟수가 매우 많은 입력
        Supplier<Integer> r19 = () -> sol.solution(6649279);
        Test.check("r19", r19, -1);
    }
}
