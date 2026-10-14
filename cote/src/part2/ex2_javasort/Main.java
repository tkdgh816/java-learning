package part2.ex2_javasort;

// 수업자료 107페이지: Java Sort
// https://www.hackerrank.com/challenges/java-sort/problem

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        List<Student> students = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int id = Integer.parseInt(st.nextToken());
            String name = st.nextToken();
            float cgpa = Float.parseFloat(st.nextToken());

            students.add(new Student(id, name, cgpa));
        }


        StringBuilder sb = new StringBuilder();
        students.stream()
                .sorted(Comparator.comparingDouble(Student::cgpa).reversed()
                        .thenComparing(Student::name)
                        .thenComparingInt(Student::id))
                .forEach(student -> sb.append(student.name()).append(System.lineSeparator()));
        System.out.println(sb);
    }
}

record Student(int id, String name, float cgpa) {

}

//class Student {
//    private final int id;
//    private final String name;
//    private final float cgpa;
//
//    Student(int id, String name, float cgpa) {
//        this.id = id;
//        this.name = name;
//        this.cgpa = cgpa;
//    }
//
//    int id() {
//        return id;
//    }
//
//    String name() {
//        return name;
//    }
//
//    float cgpa() {
//        return cgpa;
//    }
//}