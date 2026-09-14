package assignments.p02;

import java.util.Scanner;

public class ScoreSummary {
    public static void main(String[] args) {
        // 과제 2: 점수 통계 구하기
        // 입력, 계산, 출력을 직접 작성하세요.
        Scanner scanner = new Scanner(System.in);
        int number = scanner.nextInt();
        int sum = 0;
        int max = 0;
        int count = 0;

        for(int i = 0; i<number; i++){
            int score = scanner.nextInt();
            sum += score;
            max = Math.max(max, score);
            if(score >= 60){
                count++;
            }
        }

        System.out.println("평균: " + (double)sum / number);
        System.out.println("최고점: " + max);
        System.out.println("합격 인원: " + count);
    }
}