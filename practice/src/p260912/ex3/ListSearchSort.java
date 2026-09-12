package p260912.ex3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ListSearchSort {
    public static void main(String[] args) {
        // 실습 B-2: 숫자를 검색하고 정렬한 뒤 최솟값과 최댓값을 출력하세요.
        List<Integer> list = new ArrayList<>();
        list.add(40);
        list.add(10);
        list.add(30);
        list.add(20);

        System.out.println("30 포함: " + list.contains(30));
        Collections.sort(list);
        System.out.println("정렬 후: " + list);
        System.out.println("최솟값: " + list.get(0));
        System.out.println("최댓값: " + list.get(list.size() - 1));
    }
}