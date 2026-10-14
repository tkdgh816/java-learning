package part1.ex1_process;

// 수업자료 90페이지: 프로세스
// https://school.programmers.co.kr/learn/courses/30/lessons/42587

import java.util.*;

public class Main {
    static void main(String[] args) {
        Solution2 sol = new Solution2();
        int r1 = sol.solution(new int[]{2, 1, 3, 2}, 2);
        int r2 = sol.solution(new int[]{1, 1, 9, 1, 1, 1}, 0);

        System.out.println(r1);
        System.out.println(r2);
    }
}

class Solution1 {
    public int solution(int[] priorities, int location) {
        Queue<Task> queue = new ArrayDeque<>();

        for (int i = 0; i < priorities.length; i++) {
            queue.offer(new Task(i, priorities[i]));
        }

        int count = 0;
        while (!queue.isEmpty()) {
            Task polledTask = queue.poll();

            Optional<Task> higherTask = queue.stream()
                    .filter(task -> task.priority() > polledTask.priority())
                    .findFirst();

            if (higherTask.isPresent()) {
                queue.offer(polledTask);
                continue;
            }

            count++;

            if (polledTask.location() == location) {
                return count;
            }
        }

        return 0;
    }
}

class Solution2 {
    public int solution(int[] priorities, int location) {
        Queue<Task> queue = new ArrayDeque<>();

        for (int i = 0; i < priorities.length; i++) {
            queue.offer(new Task(i, priorities[i]));
        }

        List<Integer> list = new ArrayList<>();
        for (int p : priorities) {
            list.add(p);
        }
        list.sort(Comparator.reverseOrder());

        int count = 0;
        while (!queue.isEmpty()) {
            Task polledTask = queue.poll();

            if (polledTask.priority() < list.getFirst()) {
                queue.offer(polledTask);
                continue;
            }

            count++;

            if (polledTask.location() == location) {
                return count;
            }

            list.removeFirst();
        }

        return 0;
    }
}

record Task(int location, int priority) {
}