package all.part0.op3_palindrome;

// https://leetcode.com/problems/palindrome-number/

import helpers.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

class Solution {
    public boolean isPalindrome(int x) {
        if (x < 0) {
            return false;
        }
        if (x < 10) {
            return true;
        }

        List<Integer> nums = new ArrayList<>();

        int num = x;
        while (num > 0) {
            nums.add(num % 10);
            num /= 10;
        }

        int length = nums.size();
        for (int i = 0; i < length / 2; i++) {
            if (!nums.get(i).equals(nums.get(length - i - 1))) {
                return false;
            }
        }

        return true;
    }
}

public class Main {
    static void main(String[] args) {
        Solution sol = new Solution();

// 1. 기본 예제 1
        var r1 = sol.isPalindrome(121);
        Test.check("r1", r1, true);

// 2. 기본 예제 2
        var r2 = sol.isPalindrome(-121);
        Test.check("r2", r2, false);

// 3. 기본 예제 3
        var r3 = sol.isPalindrome(10);
        Test.check("r3", r3, false);

// 4. 0
        var r4 = sol.isPalindrome(0);
        Test.check("r4", r4, true);

// 5. 한 자리 양수
        var r5 = sol.isPalindrome(1);
        Test.check("r5", r5, true);

// 6. 한 자리 최댓값
        var r6 = sol.isPalindrome(9);
        Test.check("r6", r6, true);

// 7. 두 자리 회문
        var r7 = sol.isPalindrome(11);
        Test.check("r7", r7, true);

// 8. 두 자리 비회문
        var r8 = sol.isPalindrome(12);
        Test.check("r8", r8, false);

// 9. 한 자리 음수
        var r9 = sol.isPalindrome(-1);
        Test.check("r9", r9, false);

// 10. 가운데 0이 있는 회문
        var r10 = sol.isPalindrome(101);
        Test.check("r10", r10, true);

// 11. 짝수 자릿수에 0이 포함된 회문
        var r11 = sol.isPalindrome(1001);
        Test.check("r11", r11, true);

// 12. 홀수 자릿수에 0이 포함된 회문
        var r12 = sol.isPalindrome(10001);
        Test.check("r12", r12, true);

// 13. 끝자리가 0인 비회문
        var r13 = sol.isPalindrome(10010);
        Test.check("r13", r13, false);

// 14. 짝수 자릿수 회문
        var r14 = sol.isPalindrome(1221);
        Test.check("r14", r14, true);

// 15. 홀수 자릿수 회문
        var r15 = sol.isPalindrome(12321);
        Test.check("r15", r15, true);

// 16. 여섯 자리 회문
        var r16 = sol.isPalindrome(123321);
        Test.check("r16", r16, true);

// 17. 가운데 숫자가 다른 비회문
        var r17 = sol.isPalindrome(123421);
        Test.check("r17", r17, false);

// 18. 큰 수이면서 중간이 0으로 채워진 회문
        var r18 = sol.isPalindrome(1000000001);
        Test.check("r18", r18, true);

// 19. 10자리 회문
        var r19 = sol.isPalindrome(2000000002);
        Test.check("r19", r19, true);

// 20. int 최댓값에 가까운 회문
        var r20 = sol.isPalindrome(2147447412);
        Test.check("r20", r20, true);

// 21. 정수 뒤집기 과정에서 오버플로가 발생할 수 있는 경우
        var r21 = sol.isPalindrome(1534236469);
        Test.check("r21", r21, false);

// 22. 10자리이면서 끝자리가 0인 경우
        var r22 = sol.isPalindrome(1000000000);
        Test.check("r22", r22, false);

// 23. int 최댓값
        Supplier<Boolean> r23 = () -> sol.isPalindrome(Integer.MAX_VALUE);
        Test.check("r23", r23, false);

// 24. int 최솟값
        Supplier<Boolean> r24 = () -> sol.isPalindrome(Integer.MIN_VALUE);
        Test.check("r24", r24, false);
    }
}
