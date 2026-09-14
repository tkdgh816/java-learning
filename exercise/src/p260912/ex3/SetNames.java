package p260912.ex3;

import java.util.HashSet;
import java.util.Set;

public class SetNames {
    public static void main(String[] args) {
        // 실습 C-1: 이름을 Set에 저장하고 서로 다른 이름 수를 구하세요.
        String[] names = {"민수", "지연", "민수", "서준", "지연"};
        Set<String> set = new HashSet<>();
        for(String name : names){
            set.add(name);
        }

        set.add("서준");
        System.out.println("서로 다른 이름: " + set.size());
        System.out.println("지연 포함: " + set.contains("지연"));
    }
}