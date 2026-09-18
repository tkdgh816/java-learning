package p260917.ex1;

public class SoundDemo {
    public static void main(String[] args) {
        // 문제를 풀고, 실습 안내의 실행 예시를 옮겨 결과를 확인하세요.
        Sound[] sounds = {new Bell(), new Beep()};
        for (Sound sound : sounds) {
            sound.play();
        }
    }
}
