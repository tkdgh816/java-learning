package part11.ex2_friends_block;

// 수업자료 29페이지: 프렌즈4블록
// https://school.programmers.co.kr/learn/courses/30/lessons/17679

import helpers.Test;

import java.util.*;

class Solution {
    public int solution(int m, int n, String[] board) {
        int answer = 0;

        // 중력 처리 용이하게 행과 열을 전치 (n행 m열 배열로 바꿈)
        char[][] trBoard = new char[n][m];
        for (int i = 0; i < m; i++) {
            char[] col = board[i].toCharArray();
            for (int j = 0; j < n; j++) {
                trBoard[j][i] = col[j];
            }
        }

        while (true) {
            // key: 제거 대상이 있는 행 번호, value: 그 행의 제거 대상 열 번호들
            Map<Integer, Set<Integer>> rows = new HashMap<>();

            for (int i = 0; i < n - 1; i++) {
                for (int j = 0; j < m - 1; j++) {
                    // 기준 블록
                    char anchor = trBoard[i][j];
                    if (anchor == '\0') {
                        continue;
                    }

                    // 4칸 블록이 완성될 수 있으면 map에 블록들을 추가함
                    if (trBoard[i + 1][j] == anchor && trBoard[i][j + 1] == anchor && trBoard[i + 1][j + 1] == anchor) {
                        var row1 = rows.computeIfAbsent(i, rowNum -> new HashSet<>());
                        var row2 = rows.computeIfAbsent(i + 1, rowNum -> new HashSet<>());
                        row1.add(j);
                        row1.add(j + 1);
                        row2.add(j);
                        row2.add(j + 1);
                    }
                }
            }

            // 제거된 항목이 없으면 종료
            if (rows.isEmpty()) {
                break;
            }

            // 중력처리
            for (var entry : rows.entrySet()) {
                int rowNum = entry.getKey();
                Set<Integer> removed = entry.getValue();
                answer += removed.size();

                // 행에서 제거되지 않고 남은 블록들
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < m; i++) {
                    if (!removed.contains(i)) {
                        sb.append(trBoard[rowNum][i]);
                    }
                }

                char[] remaining = sb.toString().toCharArray();
                int length = remaining.length;

                // 행을 모두 비우고 남은 블록들을 채워넣음
                Arrays.fill(trBoard[rowNum], '\0');
                System.arraycopy(remaining, 0, trBoard[rowNum], m - length, length);
            }

        }
        return answer;
    }
}

public class Main {
    static void main(String[] args) {
        Solution sol = new Solution();

// 1. 기본 예제 1 (2회 제거, 총 14개)
        var r1 = sol.solution(4, 5, new String[]{"CCBDE", "AAADE", "AAABF", "CCBBF"});
        Test.check("r1", r1, 14);

// 2. 기본 예제 2 (2회 제거, 총 15개)
        var r2 = sol.solution(6, 6, new String[]{"TTTANT", "RRFACC", "RRRFCC", "TRRRAA", "TTMMMF", "TMMTTJ"});
        Test.check("r2", r2, 15);

// 3. 최소 크기 - 전체 제거
        var r3 = sol.solution(2, 2, new String[]{"AA", "AA"});
        Test.check("r3", r3, 4);

// 4. 최소 크기 - 제거 불가능
        var r4 = sol.solution(2, 2, new String[]{"AB", "CD"});
        Test.check("r4", r4, 0);

// 5. 가로로 겹치는 2x2 블록
        var r5 = sol.solution(2, 3, new String[]{"AAA", "AAA"});
        Test.check("r5", r5, 6);

// 6. 세로로 겹치는 2x2 블록
        var r6 = sol.solution(3, 2, new String[]{"AA", "AA", "AA"});
        Test.check("r6", r6, 6);

// 7. 여러 2x2 영역이 중복되는 경우 (중복 집계 방지)
        var r7 = sol.solution(3, 3, new String[]{"AAA", "AAA", "AAA"});
        Test.check("r7", r7, 9);

// 8. 체스판 형태 - 제거할 블록 없음
        var r8 = sol.solution(4, 4, new String[]{"ABAB", "BABA", "ABAB", "BABA"});
        Test.check("r8", r8, 0);

// 9. 서로 다른 블록 그룹을 동시에 제거
        var r9 = sol.solution(2, 5, new String[]{"AABBC", "AABBC"});
        Test.check("r9", r9, 8);

// 10. 네 종류의 2x2 블록을 동시에 제거
        var r10 = sol.solution(4, 4, new String[]{"AABB", "AABB", "CCDD", "CCDD"});
        Test.check("r10", r10, 16);

// 11. 직사각형 전체가 같은 블록
        var r11 = sol.solution(4, 5, new String[]{"AAAAA", "AAAAA", "AAAAA", "AAAAA"});
        Test.check("r11", r11, 20);

// 12. 세로로 나열된 서로 다른 제거 그룹
        var r12 = sol.solution(6, 2, new String[]{"AA", "AA", "BB", "BB", "AA", "AA"});
        Test.check("r12", r12, 12);

// 13. 오른쪽 아래 모서리에서만 제거
        var r13 = sol.solution(4, 5, new String[]{"ABCDE", "FGHIJ", "KLMNN", "OPQNN"});
        Test.check("r13", r13, 4);

// 14. 일부 블록 제거 후 중력 적용, 추가 제거 없음
        var r14 = sol.solution(5, 4, new String[]{"ABCD", "AAXY", "AAXY", "BXYZ", "BXYZ"});
        Test.check("r14", r14, 4);

// 15. 여러 블록 그룹이 동시에 제거되는 경우
        var r15 = sol.solution(4, 4, new String[]{"AAAB", "AAAB", "BBBA", "BBBA"});
        Test.check("r15", r15, 12);

// 16. 3회 연쇄 제거 (4개 + 4개 + 4개)
        var r16 = sol.solution(6, 5, new String[]{"CAABC", "BACAB", "BACBB", "CAACB", "BBACA", "BBCAA"});
        Test.check("r16", r16, 12);

// 17. 최대 크기 30x30 - 전체 제거
        var r17 = sol.solution(30, 30, java.util.Collections.nCopies(30, "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAA").toArray(new String[0]));
        Test.check("r17", r17, 900);

// 18. 최대 크기 30x30 - 제거 불가능
        var r18 = sol.solution(30, 30, java.util.Collections.nCopies(30, "ABABABABABABABABABABABABABABAB").toArray(new String[0]));
        Test.check("r18", r18, 0);

// 19. 가로로 같은 문자가 연속되지만 2x2는 성립하지 않는 경우
        var r19 = sol.solution(2, 4, new String[]{"AAAA", "BBBB"});
        Test.check("r19", r19, 0);

// 20. 세로로 같은 문자가 연속되지만 2x2는 성립하지 않는 경우
        var r20 = sol.solution(4, 2, new String[]{"AB", "AB", "AB", "AB"});
        Test.check("r20", r20, 0);

// 21. 최대 크기 30x30 - 일부 제거 후 블록이 남는 경우 (4개 제거, 896개 잔존)
        var board21 = java.util.Collections.nCopies(30, "ABABABABABABABABABABABABABABAB").toArray(new String[0]);
        board21[28] = "ABABABABABABABABABABABABABABAA";
        board21[29] = "ABABABABABABABABABABABABABABAA";
        var r21 = sol.solution(30, 30, board21);
        Test.check("r21", r21, 4);

// 22. 최대 크기 30x30 - 대부분 제거되지만 4개 잔존 (896개 제거)
        var board22 = java.util.Collections.nCopies(30, "A".repeat(30)).toArray(new String[0]);
        board22[0] = "B" + "A".repeat(28) + "C";
        board22[29] = "D" + "A".repeat(28) + "E";
        var r22 = sol.solution(30, 30, board22);
        Test.check("r22", r22, 896);

// 23. 동일 열에서 떨어진 두 제거 구간이 발생하는 경우
        var r23 = sol.solution(6, 2, new String[]{"AA", "AA", "CD", "DC", "BB", "BB"});
        Test.check("r23", r23, 8);
    }
}
