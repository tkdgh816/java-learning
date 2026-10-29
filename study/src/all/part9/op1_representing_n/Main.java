package all.part9.op1_representing_n;

// https://school.programmers.co.kr/learn/courses/30/lessons/42895

import helpers.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Supplier;

class Solution {
    public int solution(int N, int number) {
        if (N == number) {
            return 1;
        }

        this.N = N;
        this.number = number;

        for (int i = 1; i < 8; i++) {
            if (permutation(new ArrayList<>(i), i)) {
                return i + 1;
            }
        }

        return -1;
    }

    private int N;
    private int number;

    private boolean permutation(List<Integer> selectedIndexes, int r) {
        if (selectedIndexes.size() == r) {
            long result = N;
            for (int i = 0; i < r; i++) {
                Operation operation = Operation.getOperation(selectedIndexes.get(i));
                result = operation.execute(result, N);
                if (result >= number) {
                    break;
                }
            }
            if (result == number) {
                System.out.println(selectedIndexes.stream().map(Operation::getOperation).toList());
            }
            return result == number;
        }

        for (int i = 0; i < 5; i++) {
            selectedIndexes.add(i);
            if (permutation(selectedIndexes, r)) {
                return true;
            }
            selectedIndexes.removeLast();
        }

        return false;
    }

    enum Operation {
        ADD((a, b) -> a + b),
        SUBTRACT((a, b) -> a - b),
        MULTIPLY((a, b) -> a * b),
        DIVIDE((a, b) -> a / b),
        UNION((a, b) -> 10 * a + b);

        private final BiFunction<Long, Long, Long> operator;

        Operation(BiFunction<Long, Long, Long> operator) {
            this.operator = operator;
        }

        public long execute(long a, long b) {
            return operator.apply(a, b);
        }

        public static Operation getOperation(int number) {
            return switch (number) {
                case 0 -> ADD;
                case 1 -> SUBTRACT;
                case 2 -> MULTIPLY;
                case 3 -> DIVIDE;
                case 4 -> UNION;
                default -> throw new IllegalArgumentException();
            };
        }
    }
}

public class Main {
    static void main(String[] args) {
        Solution sol = new Solution();

// 1. 기본 예제 1
        var r1 = sol.solution(5, 12);
        Test.check("r1", r1, 4);

// 2. 기본 예제 2
        var r2 = sol.solution(2, 11);
        Test.check("r2", r2, 3);

// 3. N과 number가 같은 경우
        var r3 = sol.solution(5, 5);
        Test.check("r3", r3, 1);

// 4. N의 최솟값
        var r4 = sol.solution(1, 1);
        Test.check("r4", r4, 1);

// 5. N의 최댓값
        var r5 = sol.solution(9, 9);
        Test.check("r5", r5, 1);

// 6. 나눗셈으로 1 만들기
        var r6 = sol.solution(7, 1);
        Test.check("r6", r6, 2);

// 7. 나눗셈으로 2 만들기
        var r7 = sol.solution(8, 2);
        Test.check("r7", r7, 3);

// 8. 숫자 이어 붙이기
        var r8 = sol.solution(5, 55);
        Test.check("r8", r8, 2);

// 9. 숫자 4개 이어 붙이기
        var r9 = sol.solution(2, 2222);
        Test.check("r9", r9, 4);

// 10. 숫자 4개 이어 붙이기
        var r10 = sol.solution(3, 3333);
        Test.check("r10", r10, 4);

// 11. 뺄셈 활용
        var r11 = sol.solution(5, 4);
        Test.check("r11", r11, 3);

// 12. 덧셈과 나눗셈 조합
        var r12 = sol.solution(5, 6);
        Test.check("r12", r12, 3);

// 13. 곱셈 활용
        var r13 = sol.solution(7, 49);
        Test.check("r13", r13, 2);

// 14. 정수 나눗셈의 나머지 버림
        var r14 = sol.solution(9, 4);
        Test.check("r14", r14, 4);

// 15. 이어 붙인 숫자의 정수 나눗셈
        var r15 = sol.solution(8, 5);
        Test.check("r15", r15, 4);

// 16. 곱셈 여러 번 활용
        var r16 = sol.solution(4, 256);
        Test.check("r16", r16, 4);

// 17. 여러 연산 조합
        var r17 = sol.solution(9, 1000);
        Test.check("r17", r17, 5);

// 18. 괄호를 이용한 연산 순서
        var r18 = sol.solution(5, 3600);
        Test.check("r18", r18, 6);

// 19. 최소 사용 횟수가 6인 경우
        var r19 = sol.solution(2, 17);
        Test.check("r19", r19, 6);

// 20. 최소 사용 횟수가 7인 경우
        var r20 = sol.solution(6, 777);
        Test.check("r20", r20, 7);

// 21. 여러 연산을 조합하여 7개 사용
        var r21 = sol.solution(8, 1000);
        Test.check("r21", r21, 7);

// 22. 최소 사용 횟수가 정확히 8인 경우
        var r22 = sol.solution(2, 256);
        Test.check("r22", r22, 8);

// 23. 최소 사용 횟수가 정확히 8인 경우
        var r23 = sol.solution(6, 1000);
        Test.check("r23", r23, 8);

// 24. 정수 나눗셈이 최소 횟수에 영향을 주는 경우
        var r24 = sol.solution(2, 147);
        Test.check("r24", r24, 8);

// 25. 8개 이하로 표현할 수 없는 경우
        var r25 = sol.solution(2, 1024);
        Test.check("r25", r25, -1);

// 26. N이 1이고 표현이 불가능한 경우
        var r26 = sol.solution(1, 32000);
        Test.check("r26", r26, -1);

// 27. number 최댓값, 정확히 8개 사용
        Supplier<Integer> r27 = () -> sol.solution(8, 32000);
        Test.check("r27", r27, 8);

// 28. number 최댓값, 표현 불가능
        Supplier<Integer> r28 = () -> sol.solution(9, 32000);
        Test.check("r28", r28, -1);

// 29. number 최댓값, 다른 N으로 표현 불가능
        Supplier<Integer> r29 = () -> sol.solution(5, 32000);
        Test.check("r29", r29, -1);
    }
}
