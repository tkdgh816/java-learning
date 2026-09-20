package p260919.ex1;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;

public class ImageCopy {
    public static void main(String[] args) {
        Path original = Path.of("out", "lesson07", "image.jpg");
        Path copied = Path.of("out", "lesson07", "image-copied.jpg");

        try (InputStream inputStream = Files.newInputStream(original);
             OutputStream outputStream = Files.newOutputStream(copied)) {
            byte[] buffer = new byte[1024];
            int count;
            while ((count = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, count);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
