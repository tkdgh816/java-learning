package part11.pr1_string_compression;

// 수업자료 16페이지: 문자열 압축
// https://school.programmers.co.kr/learn/courses/30/lessons/60057

import helpers.Test;

import java.util.function.Supplier;

class Solution {
    public int solution(String s) {
        int answer = s.length();

        // gap: 문자열을 자른 길이
        for (int gap = 1; gap <= s.length() / 2; gap++) {
            StringBuilder sb = new StringBuilder();

            int start = 0;
            String str = "";
            int count = 0;

            while (start < s.length()) {
                // 시작 인덱스는 포함, 끝 인덱스는 불포함
                int end = start + gap;
                String newStr = s.substring(start, Math.min(end, s.length()));

                // 이전 그룹 문자열과 일치하면 카운트만 늘리고
                // 일치하지 않으면 이전 구간 그룹 확정 및
                // 새 문자열 그룹 생성, 횟수 초기화
                if (str.equals(newStr)) {
                    count++;
                } else {
                    if (count > 1) {
                        sb.append(count).append(str);
                    } else {
                        sb.append(str);
                    }
                    str = newStr;
                    count = 1;
                }

                start = end;
            }

            // 마지막에 남은 그룹 처리
            if (count > 1) {
                sb.append(count).append(str);
            } else {
                sb.append(str);
            }

            answer = Math.min(answer, sb.length());
        }

        return answer;
    }
}

public class Main {
    static void main(String[] args) {
        Solution sol = new Solution();

// 1. 공식 예제 1
        var r1 = sol.solution("aabbaccc");
        Test.check("r1", r1, 7);

// 2. 공식 예제 2
        var r2 = sol.solution("ababcdcdababcdcd");
        Test.check("r2", r2, 9);

// 3. 공식 예제 3
        var r3 = sol.solution("abcabcdede");
        Test.check("r3", r3, 8);

// 4. 공식 예제 4
        var r4 = sol.solution("abcabcabcabcdededededede");
        Test.check("r4", r4, 14);

// 5. 공식 예제 5
        var r5 = sol.solution("xababcdcdababcdcd");
        Test.check("r5", r5, 17);

// 6. 최소 길이 문자열
        var r6 = sol.solution("a");
        Test.check("r6", r6, 1);

// 7. 동일한 문자 2개
        var r7 = sol.solution("aa");
        Test.check("r7", r7, 2);

// 8. 서로 다른 문자 2개
        var r8 = sol.solution("ab");
        Test.check("r8", r8, 2);

// 9. 동일한 문자 3개
        var r9 = sol.solution("aaa");
        Test.check("r9", r9, 2);

// 10. 동일한 문자 4개
        var r10 = sol.solution("aaaa");
        Test.check("r10", r10, 2);

// 11. 압축할 수 없는 문자열
        var r11 = sol.solution("abcdefg");
        Test.check("r11", r11, 7);

// 12. 길이 5 단위의 반복
        var r12 = sol.solution("abcdeabcde");
        Test.check("r12", r12, 6);

// 13. 마지막에 압축 단위보다 짧은 문자열이 남는 경우
        var r13 = sol.solution("abcabcab");
        Test.check("r13", r13, 6);

// 14. 시작 위치가 어긋나 압축되지 않는 경우
        var r14 = sol.solution("zabcabc");
        Test.check("r14", r14, 7);

// 15. 서로 다른 문자열 그룹이 각각 반복되는 경우
        var r15 = sol.solution("abcabcxyzxyz");
        Test.check("r15", r15, 8);

// 16. 길이 2 단위가 여러 번 반복되는 경우
        var r16 = sol.solution("ababababab");
        Test.check("r16", r16, 3);

// 17. 연속 반복 이후 다른 문자가 남는 경우
        var r17 = sol.solution("aaaaab");
        Test.check("r17", r17, 3);

// 18. 반복 횟수가 한 자릿수인 경우
        var r18 = sol.solution("a".repeat(9));
        Test.check("r18", r18, 2);

// 19. 반복 횟수가 두 자릿수가 되는 경우
        var r19 = sol.solution("a".repeat(10));
        Test.check("r19", r19, 3);

// 20. 반복 횟수가 세 자릿수가 되는 경우
        var r20 = sol.solution("a".repeat(100));
        Test.check("r20", r20, 4);

// 21. 최대 길이 1000, 동일 문자 반복
        var r21 = sol.solution("a".repeat(1000));
        Test.check("r21", r21, 5);

// 22. 길이 10 단위의 문자열이 10번 반복되는 경우
        var r22 = sol.solution("abcdefghij".repeat(10));
        Test.check("r22", r22, 12);

// 23. 서로 다른 문자가 각각 9번 연속 반복되는 경우
        var r23 = sol.solution("a".repeat(9) + "b".repeat(9));
        Test.check("r23", r23, 4);

// 24. 서로 다른 문자가 각각 10번 연속 반복되는 경우
        var r24 = sol.solution("a".repeat(10) + "b".repeat(10));
        Test.check("r24", r24, 6);

// 25. 여러 압축 단위 중 최적의 압축 단위를 선택해야 하는 경우
        var r25 = sol.solution("aaaaabaaaaab");
        Test.check("r25", r25, 6);

// 26. 반복 문자열 이후 압축되지 않는 문자가 남는 경우
        var r26 = sol.solution("abcabcabcx");
        Test.check("r26", r26, 5);

// 27. 반복 구간 뒤에 서로 다른 접미사가 남는 경우
        var r27 = sol.solution("abcabcabcabcde");
        Test.check("r27", r27, 6);

// 28. 최대 길이 1000, 길이 2 단위 반복
        Supplier<Integer> r28 = () -> sol.solution("ab".repeat(500));
        Test.check("r28", r28, 5);

// 29. 최대 길이 1000, 다양한 반복 패턴과 불규칙한 구간 혼합
        Supplier<Integer> r29 = () -> sol.solution("a".repeat(73) + "ab".repeat(43) + "abac".repeat(41) + "abcxyz".repeat(27) + "x".repeat(29) + "qwertyuiop".repeat(20) + "lmno".repeat(40) + "pqrstuvwxyz".repeat(11) + "vbcde");
        Test.check("r29", r29, 468);
    }
}
