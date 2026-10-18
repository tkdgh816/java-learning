package week1_1017.ex1_eof;

// https://www.hackerrank.com/challenges/java-end-of-file/problem

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        Solution1 sol1 = new Solution1();
        sol1.solution();
    }
}

class Solution1 {
    public void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        String line;
        int number = 0;
        while ((line = br.readLine()) != null) {
            sb.append(++number).append(' ').append(line).append('\n');
        }

        System.out.println(sb.toString().strip());
    }
}