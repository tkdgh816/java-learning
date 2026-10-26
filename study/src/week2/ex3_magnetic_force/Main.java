package week2.ex3_magnetic_force;

// https://leetcode.com/problems/magnetic-force-between-two-balls/description/

import helpers.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
    public int maxDistance(int[] position, int m) {
        original = position;
        Arrays.sort(original);
        n = original.length;
        r = m;
        max = Integer.MIN_VALUE;

        combination(new ArrayList<>(r), 0);
        return max;
    }

    private int[] original;
    private int n;
    private int r;
    int max;

    private void combination(List<Integer> selectedIndexes, int startIndex) {
        if (selectedIndexes.size() == r) {
            int minDistance = Integer.MAX_VALUE;
            for (int i = 0; i < selectedIndexes.size() - 1; i++) {
                int current = selectedIndexes.get(i);
                int next = selectedIndexes.get(i + 1);
                minDistance = Math.min(minDistance, original[next] - original[current]);
            }
            max = Math.max(max, minDistance);
            return;
        }

        for (int i = startIndex; i < n; i++) {
            selectedIndexes.add(i);
            combination(selectedIndexes, i + 1);
            selectedIndexes.removeLast();
        }
    }
}

class Solution2 {
    public int maxDistance(int[] position, int m) {

        return 0;
    }
}

public class Main {
    static void main(String[] args) {
//        Solution sol = new Solution();
        Solution2 sol = new Solution2();

// 1. 기본 예제 1
        var r1 = sol.maxDistance(new int[]{1, 2, 3, 4, 7}, 3);
        Test.check("r1", r1, 3);

// 2. 기본 예제 2
        var r2 = sol.maxDistance(new int[]{5, 4, 3, 2, 1, 1000000000}, 2);
        Test.check("r2", r2, 999999999);

// 3. 최소 입력 크기
        var r3 = sol.maxDistance(new int[]{1, 2}, 2);
        Test.check("r3", r3, 1);

// 4. 공이 2개인 경우
        var r4 = sol.maxDistance(new int[]{1, 100}, 2);
        Test.check("r4", r4, 99);

// 5. 공의 개수와 바구니 개수가 같은 경우
        var r5 = sol.maxDistance(new int[]{1, 2, 3, 4, 5}, 5);
        Test.check("r5", r5, 1);

// 6. 공을 모든 바구니에 배치해야 하는 불균등 간격
        var r6 = sol.maxDistance(new int[]{1, 4, 10, 20, 21}, 5);
        Test.check("r6", r6, 1);

// 7. 정렬되지 않은 입력
        var r7 = sol.maxDistance(new int[]{9, 1, 4, 7, 13}, 3);
        Test.check("r7", r7, 6);

// 8. 일정한 간격으로 배치된 바구니
        var r8 = sol.maxDistance(new int[]{2, 5, 8, 11, 14, 17}, 3);
        Test.check("r8", r8, 6);

// 9. 간격이 점점 증가하는 경우
        var r9 = sol.maxDistance(new int[]{1, 3, 6, 10, 15}, 3);
        Test.check("r9", r9, 5);

// 10. 지수적으로 간격이 증가하는 경우
        var r10 = sol.maxDistance(new int[]{1, 2, 4, 8, 16, 32}, 3);
        Test.check("r10", r10, 15);

// 11. 마지막 바구니만 멀리 떨어진 경우
        var r11 = sol.maxDistance(new int[]{1, 2, 3, 4, 100}, 3);
        Test.check("r11", r11, 3);

// 12. 바구니가 두 구간에 밀집된 경우
        var r12 = sol.maxDistance(new int[]{1, 2, 3, 100, 101, 102}, 3);
        Test.check("r12", r12, 2);

// 13. 최적 배치가 단순한 균등 분할과 다른 경우
        var r13 = sol.maxDistance(new int[]{1, 5, 10, 20, 25}, 3);
        Test.check("r13", r13, 9);

// 14. 중간 바구니 선택이 중요한 경우
        var r14 = sol.maxDistance(new int[]{1, 2, 8, 12, 17}, 3);
        Test.check("r14", r14, 7);

// 15. 공이 바구니보다 하나 적은 경우
        var r15 = sol.maxDistance(new int[]{4, 7, 12, 19, 27}, 4);
        Test.check("r15", r15, 7);

// 16. 좌표 최댓값과 최소 간격
        var r16 = sol.maxDistance(new int[]{1, 999999999, 1000000000}, 3);
        Test.check("r16", r16, 1);

// 17. 매우 큰 좌표에서 공 3개 배치
        var r17 = sol.maxDistance(new int[]{1, 499999999, 1000000000}, 3);
        Test.check("r17", r17, 499999998);

// 18. 바구니가 여러 개지만 공은 2개인 경우
        var r18 = sol.maxDistance(new int[]{1, 2, 100, 101}, 2);
        Test.check("r18", r18, 100);

// 19. 일정한 간격에서 일부 바구니만 선택
        var r19 = sol.maxDistance(new int[]{1, 4, 7, 10, 13}, 4);
        Test.check("r19", r19, 3);

// 20. 세 구간에 밀집된 바구니에 공 4개 배치
        var r20 = sol.maxDistance(new int[]{1, 2, 3, 10, 11, 12, 20, 21, 22}, 4);
        Test.check("r20", r20, 2);

// 21. 최대 배열 크기, 공 2개
        int[] p21 = new int[100_000];
        for (int i = 0; i < p21.length; i++) p21[i] = i + 1;
        var r21 = sol.maxDistance(p21.clone(), 2);
        Test.check("r21", r21, 99_999);

// 22. 최대 배열 크기, 공 50,000개
        var r22 = sol.maxDistance(p21.clone(), 50_000);
        Test.check("r22", r22, 2);

// 23. 최대 배열 크기, 공 50,001개
        var r23 = sol.maxDistance(p21.clone(), 50_001);
        Test.check("r23", r23, 1);

// 24. 최대 배열 크기, 큰 좌표 간격, 공 3개
        int[] p24 = new int[100_000];
        for (int i = 0; i < p24.length; i++) p24[i] = 1 + i * 10_000;
        var r24 = sol.maxDistance(p24.clone(), 3);
        Test.check("r24", r24, 499_990_000);

// 25. 최대 배열 크기, 큰 좌표 간격, 공 50,000개
        var r25 = sol.maxDistance(p24.clone(), 50_000);
        Test.check("r25", r25, 20_000);

// 26. 최대 배열 크기, 정렬되지 않은 입력
        int[] p26 = new int[100_000];
        for (int i = 0; i < p26.length; i++) p26[i] = (int) ((i * 99_991L) % 100_000) + 1;
        var r26 = sol.maxDistance(p26, 25_000);
        Test.check("r26", r26, 4);

// 27. 최대 배열 크기, 양 끝에 밀집된 바구니, 공 3개
        int[] p27 = new int[100_000];
        for (int i = 0; i < p27.length; i++) p27[i] = i < 50_000 ? i + 1 : 999_950_001 + i - 50_000;
        var r27 = sol.maxDistance(p27.clone(), 3);
        Test.check("r27", r27, 49_999);

// 28. 최대 배열 크기, 양 끝에 밀집된 바구니, 공 5개
        var r28 = sol.maxDistance(p27.clone(), 5);
        Test.check("r28", r28, 24_999);

// 29. 최대 배열 크기, 모든 바구니 사용
        var r29 = sol.maxDistance(p27.clone(), 100_000);
        Test.check("r29", r29, 1);

// 30. 최대 배열 크기, 최대 좌표 차이
        var r30 = sol.maxDistance(p27.clone(), 2);
        Test.check("r30", r30, 999_999_999);

// 31. 배열 크기 10,000, 큰 좌표 간격, 공 3,334개
        int[] p31 = new int[10_000];
        for (int i = 0; i < p31.length; i++) p31[i] = 1 + i * 100_000;
        var r31 = sol.maxDistance(p31.clone(), 3_334);
        Test.check("r31", r31, 300_000);

// 32. 배열 크기 10,000, 모든 바구니 사용
        var r32 = sol.maxDistance(p31.clone(), 10_000);
        Test.check("r32", r32, 100_000);
    }
}
