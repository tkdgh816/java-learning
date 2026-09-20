package p260919.ex1;


import java.io.InputStream;
import java.io.OutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class ByteCopyExample {
    public static void main(String[] args) {
        Path input = Path.of("out", "lesson07", "notes.txt");
        Path output = Path.of("out", "lesson07", "notes-copy.txt");
        try {
            Files.createDirectories(output.getParent());
            try (InputStream in = Files.newInputStream(input);
                 OutputStream out = Files.newOutputStream(output)) {
                byte[] buffer = new byte[5];
                int count;
                while ((count = in.read(buffer)) != -1) {
                    out.write(buffer, 0, count);
                }
            }
            System.out.println("복사 완료");
        } catch (IOException e) {
            System.out.println("복사하지 못했습니다: " + e.getMessage());
        }
    }
}