package excercise;

import java.util.Scanner;

public class Ex1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        long input = scanner.nextLong();

        long maxNumber = input;
        int evenCount = 0;
        int oddCount = 0;

        while (input > 1) {
            if (input % 2 == 0) {
                input /= 2;
                evenCount++;
            } else {
                input = input * 3 + 1;
                oddCount++;
            }

            if (maxNumber < input) {
                maxNumber = input;
            }
        }

        System.out.println(evenCount + oddCount);
        System.out.println(maxNumber);
        System.out.println(evenCount);
        System.out.println(oddCount);
    }
}
