package excercise;

import java.io.FileNotFoundException;
import java.io.FileReader;

public class Ex3 {
    public static void main(String[] args) {
        try {
            FileReader reader = new FileReader("test.txt");
        } catch (FileNotFoundException e) {
            System.out.println("파일을 찾을 수 없습니다.");
        }
    }
}