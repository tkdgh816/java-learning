package week2.ex1_representing_numbers;

// 숫자의 표현
// https://school.programmers.co.kr/learn/courses/30/lessons/12924

import helpers.Test;

class Solution {
    public int solution(int n) {
        int count = 0;
        int[] prefixSum = new int[n + 1];
        for (int i = 0; i < n; i++) {
            prefixSum[i + 1] = prefixSum[i] + (i + 1);
        }

        int left = 0, right = 1;

        while (left < right && right <= n) {
            int subtotal = prefixSum[right] - prefixSum[left];
            if (subtotal < n) {
                right++;
            } else if (subtotal > n) {
                left++;
            } else {
                left++;
                count++;
            }
        }

        return count;
    }
}

public class Main {
    static void main(String[] args) {
        Solution sol = new Solution();
// 1. 기본 예제
        var r1 = sol.solution(15);
        Test.check("r1", r1, 4);

// 2. 최솟값
        var r2 = sol.solution(1);
        Test.check("r2", r2, 1);

// 3. 가장 작은 짝수
        var r3 = sol.solution(2);
        Test.check("r3", r3, 1);

// 4. 작은 홀수
        var r4 = sol.solution(3);
        Test.check("r4", r4, 2);

// 5. 2의 거듭제곱
        var r5 = sol.solution(4);
        Test.check("r5", r5, 1);

// 6. 작은 소수
        var r6 = sol.solution(5);
        Test.check("r6", r6, 2);

// 7. 작은 합성수
        var r7 = sol.solution(6);
        Test.check("r7", r7, 2);

// 8. 2의 거듭제곱
        var r8 = sol.solution(8);
        Test.check("r8", r8, 1);

// 9. 홀수의 제곱
        var r9 = sol.solution(9);
        Test.check("r9", r9, 3);

// 10. 일반적인 짝수
        var r10 = sol.solution(10);
        Test.check("r10", r10, 2);

// 11. 2의 거듭제곱
        var r11 = sol.solution(16);
        Test.check("r11", r11, 1);

// 12. 여러 표현이 가능한 홀수
        var r12 = sol.solution(21);
        Test.check("r12", r12, 4);

// 13. 짝수 합성수
        var r13 = sol.solution(28);
        Test.check("r13", r13, 2);

// 14. 짝수 제곱수
        var r14 = sol.solution(36);
        Test.check("r14", r14, 3);

// 15. 표현 방법이 많은 홀수
        var r15 = sol.solution(45);
        Test.check("r15", r15, 6);

// 16. 홀수의 거듭제곱
        var r16 = sol.solution(81);
        Test.check("r16", r16, 5);

// 17. 세 자리 짝수
        var r17 = sol.solution(100);
        Test.check("r17", r17, 3);

// 18. 홀수 소수
        var r18 = sol.solution(127);
        Test.check("r18", r18, 2);

// 19. 큰 2의 거듭제곱
        var r19 = sol.solution(128);
        Test.check("r19", r19, 1);

// 20. 다양한 길이의 표현이 가능한 수
        var r20 = sol.solution(255);
        Test.check("r20", r20, 8);

// 21. 세 자리 홀수
        var r21 = sol.solution(999);
        Test.check("r21", r21, 8);

// 22. 네 자리 짝수
        var r22 = sol.solution(1000);
        Test.check("r22", r22, 4);

// 23. 큰 2의 거듭제곱
        var r23 = sol.solution(1024);
        Test.check("r23", r23, 1);

// 24. 표현 방법이 매우 많은 수
        var r24 = sol.solution(2025);
        Test.check("r24", r24, 15);

// 25. 표현 방법이 매우 많은 수
        var r25 = sol.solution(4095);
        Test.check("r25", r25, 24);

// 26. 큰 2의 거듭제곱
        var r26 = sol.solution(4096);
        Test.check("r26", r26, 1);

// 27. 큰 홀수의 거듭제곱
        var r27 = sol.solution(6561);
        Test.check("r27", r27, 9);

// 28. 최댓값에 가까운 홀수
        var r28 = sol.solution(9999);
        Test.check("r28", r28, 12);

// 29. 최댓값
        var r29 = sol.solution(10000);
        Test.check("r29", r29, 5);
    }
}
