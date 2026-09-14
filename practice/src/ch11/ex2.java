package ch11;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

public class ex2 {
    public static void main(String[] args) {
        Path path = Path.of("C:\\Dev\\test.txt");
        try (FileInputStream fileInputStream = new FileInputStream(path.toString())) {
            byte[] bytes = new byte[5];
            fileInputStream.read(bytes);
            System.out.print(new String(bytes));
        } catch (IOException e) {
            System.out.println("File Not Found");
        }
        finally {
            System.out.println();
        }

        try(Stream<String> lines = Files.lines(path)){
            lines.forEach(System.out::println);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
