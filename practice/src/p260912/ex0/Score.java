package p260912.ex0;

import java.util.ArrayList;

public class Score {
    public static void main(String[] args) {
        int[] scores = {45, 80, 60, 95, 55, 70};
        int passScore = 80;

        ArrayList<Integer> list = new ArrayList<>();
        for(int score : scores){
            if(score >= passScore){
                list.add(score);
            }
        }

        int sum = 0;
        for(Integer element : list){
            sum += element;
        }

        System.out.println("합격 점수: " + list);
        System.out.println("합격 인원: " + list.size() + "명");
        System.out.println("합격 점수 합계: " + sum);
    }
}
