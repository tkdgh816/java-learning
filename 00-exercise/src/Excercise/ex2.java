package Excercise;

import java.util.Scanner;

public class ex2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        long input = scanner.nextLong();

        long inverse = 0;
        int sum = 0;

        while(input != 0){
            long num = input % 10;
            inverse = inverse * 10 + num;
            input /= 10;
        }


        System.out.println(inverse);
    }
}
