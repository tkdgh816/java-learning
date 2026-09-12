package p260912.ex3;

import java.util.ArrayList;
import java.util.List;

public class ListItems {
    public static void main(String[] args) {
        // 실습 B-1: 목록에 값을 추가하고 삭제한 뒤 결과를 출력하세요.
        List<String> list = new ArrayList<>();
        list.add("우유");
        list.add("빵");
        list.add("달걀");
        list.remove("빵");
        list.add("사과");

        System.out.println(list);
        System.out.println("첫 번째: " + list.get(0));
        System.out.println("항목 수: " + list.size());
    }
}
