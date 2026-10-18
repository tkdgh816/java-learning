package programmers.q42840;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// 수업 시간에 배운 컬렉션, 객체 지향, 스트림, 람다를 활용해 보았음.
// 컬렉션: List와 Map 사용
// 객체 지향: Student 클래스를 만들어 번호와 패턴을 묶고, 점수 계산하는 메서드 정의
// 스트림: filter, map(mapToInt) 사용
// 람다: filter의 조건을 람다식으로 작성
// 메서드 참조: Student::getNumber 사용

// 모의고사
// https://school.programmers.co.kr/learn/courses/30/lessons/42840
public class Test {
    public static void main(String[] args) {
        Solution sol = new Solution();
        System.out.println(Arrays.toString(sol.solution(new int[]{1,2,3,4,5})));
        System.out.println(Arrays.toString(sol.solution(new int[]{1,3,2,4,2})));
    }
}

class Solution {
    public int[] solution(int[] answers) {
        // 학생 리스트
        List<Student> students = List.of(
                new Student(1, new int[]{1, 2, 3, 4, 5}),
                new Student(2, new int[]{2, 1, 2, 3, 2, 4, 2, 5}),
                new Student(3, new int[]{3, 3, 1, 1, 2, 2, 4, 4, 5, 5})
        );

        // 학생과 채점 결과 점수를 매핑, 최고점 구함
        Map<Student, Integer> scoresByStudent = new HashMap<>();
        int max = 0;
        for (Student student : students) {
            int score = student.calculateScore(answers);
            scoresByStudent.put(student, score);
            max = Math.max(max, score);
        }

        // 학생 리스트를 순회하며 최고점을 가진 학생들만 필터링 후 배열로 변환
        int finalMax = max;
        return students.stream()
                .filter((student) -> scoresByStudent.get(student) == finalMax)
                .mapToInt(Student::getNumber)
                .toArray();
    }
}

class Student {
    private final int number;

    int getNumber() {
        return number;
    }

    private final int[] pattern;

    // 학생은 번호와 찍기 패턴을 가짐
    Student(int number, int[] pattern) {
        this.number = number;
        this.pattern = pattern;
    }

    // 정답 배열을 받아 점수 계산
    int calculateScore(int[] answers) {
        int divisor = pattern.length;
        int score = 0;
        for (int i = 0; i < answers.length; i++) {
            if (answers[i] == pattern[i % divisor]) {
                score++;
            }
        }
        return score;
    }
}