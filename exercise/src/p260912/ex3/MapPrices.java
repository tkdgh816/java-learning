package p260912.ex3;

import java.util.HashMap;
import java.util.Map;

public class MapPrices {
    public static void main(String[] args) {
        // 실습 C-2: Map에 가격을 저장하고 수정·삭제한 결과를 확인하세요.
        Map<String, Integer> map = new HashMap<>();
        map.put("커피", 3000);
        map.put("라떼", 4000);
        map.put("차", 2500);

        map.put("커피", 3500);
        map.remove("차");

        System.out.println("커피: " + map.get("커피"));
        System.out.println("메뉴 수: " + map.size());
        System.out.println("차 포함: " + map.containsKey("차"));
    }
}
