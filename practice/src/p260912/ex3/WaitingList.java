package p260912.ex3;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class WaitingList {
    public static void main(String[] args) {
        // 실습 B-3: 대기자를 추가하고 맨 앞의 사람부터 입장시키세요.
        List<String> list = new ArrayList<>();

        StringBuilder output = new StringBuilder();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            int number = scanner.nextInt();

            if (number == 0) {
                output.append("남은 인원: " + list.size() );
                break;
            }

            switch (number) {
                case 1 -> {
                    String name = scanner.next();
                    list.add(name);
                    output.append("추가: " + name + "\n");
                }
                case 2 -> {
                    if (list.isEmpty()) {
                        output.append("대가자가 없습니다.");
                    } else {
                        String name = list.remove(0);
                        output.append("입장: " + name + "\n");
                    }
                }
                case 3 -> output.append("대기: " + list + "\n");
            }
        }

        System.out.println(output);
    }
}