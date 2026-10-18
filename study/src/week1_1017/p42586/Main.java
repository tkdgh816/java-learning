package week1_1017.p42586;

import helpers.Test;

import java.util.*;
import java.util.stream.IntStream;

// https://school.programmers.co.kr/learn/courses/30/lessons/42586

class Solution1 {
    public int[] solution(int[] progresses, int[] speeds) {
        int length = progresses.length;

        Queue<Task> queue = new ArrayDeque<>(length);

        // 큐에 모든 작업 넣음
        for (int i = 0; i < length; i++) {
            queue.offer(new Task(progresses[i], speeds[i]));
        }

        List<Integer> results = new ArrayList<>();

        while (!queue.isEmpty()) {
            // 큐를 한 바퀴 돌면서 남아 있는 모든 작업 한 번 수행
            for (Task task : queue) {
                task.run();
            }

            int count = 0;
            // 큐에서 꺼내면서 미완료된 작업을 발견할 때까지
            // 완료된 작업들 꺼냄, 꺼낸 개수가 배포된 작업의 개수
            while (!queue.isEmpty() && queue.peek().isCompleted()) {
                queue.poll();
                count++;
            }

            // 배포된 작업이 있다면 결과에 추가
            if (count > 0) {
                results.add(count);
            }
        }

        return results.stream().mapToInt(Integer::intValue).toArray();
    }
}

class Task {
    private int progress;
    private final int speed;

    Task(int initialProgress, int speed) {
        progress = initialProgress;
        this.speed = speed;
    }

    public void run() {
        progress += speed;
    }

    public boolean isCompleted() {
        return progress >= 100;
    }
}

public class Main {
    static void main(String[] args) {
        Solution1 sol = new Solution1();

// 1. 공식 예제 1
        var r1 = sol.solution(
                new int[]{93, 30, 55},
                new int[]{1, 30, 5}
        );
        Test.check("r1", r1, new int[]{2, 1});

// 2. 공식 예제 2
        var r2 = sol.solution(
                new int[]{95, 90, 99, 99, 80, 99},
                new int[]{1, 1, 1, 1, 1, 1}
        );
        Test.check("r2", r2, new int[]{1, 3, 2});

// 3. 작업이 하나뿐인 경우
        var r3 = sol.solution(
                new int[]{99},
                new int[]{1}
        );
        Test.check("r3", r3, new int[]{1});

// 4. 모든 작업이 같은 날 완료되는 경우
        var r4 = sol.solution(
                new int[]{90, 80, 70},
                new int[]{10, 20, 30}
        );
        Test.check("r4", r4, new int[]{3});

// 5. 첫 작업이 가장 늦게 완료되는 경우
        var r5 = sol.solution(
                new int[]{1, 99, 98, 97},
                new int[]{1, 1, 1, 1}
        );
        Test.check("r5", r5, new int[]{4});

// 6. 모든 작업이 순서대로 배포되는 경우
        var r6 = sol.solution(
                new int[]{99, 98, 97, 96},
                new int[]{1, 1, 1, 1}
        );
        Test.check("r6", r6, new int[]{1, 1, 1, 1});

// 7. 올림 계산 후 완료일이 같아지는 경우
        var r7 = sol.solution(
                new int[]{95, 90, 89},
                new int[]{2, 4, 4}
        );
        Test.check("r7", r7, new int[]{3});

// 8. 완료일이 점차 빨라지는 경우
        var r8 = sol.solution(
                new int[]{95, 96, 97},
                new int[]{2, 2, 2}
        );
        Test.check("r8", r8, new int[]{3});

// 9. 첫 작업만 먼저 배포되고 나머지는 함께 배포
        var r9 = sol.solution(
                new int[]{96, 95, 94},
                new int[]{2, 2, 2}
        );
        Test.check("r9", r9, new int[]{1, 2});

// 10. 모든 작업이 하루 만에 완료되는 경우
        var r10 = sol.solution(
                new int[]{1, 20, 99},
                new int[]{100, 100, 100}
        );
        Test.check("r10", r10, new int[]{3});

// 11. 개발 속도가 모두 1이고 앞 작업이 가장 느린 경우
        var r11 = sol.solution(
                new int[]{1, 50, 99},
                new int[]{1, 1, 1}
        );
        Test.check("r11", r11, new int[]{3});

// 12. 여러 배포 그룹이 형성되는 복합 사례
        var r12 = sol.solution(
                new int[]{90, 50, 80, 30, 90, 20},
                new int[]{5, 10, 5, 10, 5, 10}
        );
        Test.check("r12", r12, new int[]{1, 2, 2, 1});

// 13. 진도는 같지만 개발 속도가 다른 경우
        var r13 = sol.solution(
                new int[]{50, 50, 50, 50, 50},
                new int[]{10, 20, 5, 25, 10}
        );
        Test.check("r13", r13, new int[]{2, 3});

// 14. 서로 다른 진도와 속도지만 완료일이 같은 경우
        var r14 = sol.solution(
                new int[]{99, 1, 99, 1, 99},
                new int[]{1, 100, 1, 100, 1}
        );
        Test.check("r14", r14, new int[]{5});

// 15. 동일 진도에서 뒤쪽 작업이 더 빨리 완료되는 경우
        var r15 = sol.solution(
                new int[]{1, 1, 1, 1},
                new int[]{2, 3, 4, 5}
        );
        Test.check("r15", r15, new int[]{4});

// 16. 동일 진도에서 뒤쪽 작업이 더 늦게 완료되는 경우
        var r16 = sol.solution(
                new int[]{1, 1, 1, 1},
                new int[]{5, 4, 3, 2}
        );
        Test.check("r16", r16, new int[]{1, 1, 1, 1});

// 17. 완료일이 1, 5, 2, 5, 4, 20일인 경우
        var r17 = sol.solution(
                new int[]{99, 95, 90, 80, 60, 40},
                new int[]{1, 1, 5, 4, 10, 3}
        );
        Test.check("r17", r17, new int[]{1, 4, 1});

// 18. 최대 작업 개수 100개가 한 번에 배포되는 경우
        var r18 = sol.solution(
                IntStream.range(0, 100).map(i -> 99).toArray(),
                IntStream.range(0, 100).map(i -> 1).toArray()
        );
        Test.check("r18", r18, new int[]{100});

// 19. 99개 작업이 전부 개별 배포되는 경우
        var r19 = sol.solution(
                IntStream.range(0, 99).map(i -> 99 - i).toArray(),
                IntStream.range(0, 99).map(i -> 1).toArray()
        );
        Test.check(
                "r19",
                r19,
                IntStream.range(0, 99).map(i -> 1).toArray()
        );
    }
}