package p260912.ex0;

import java.util.HashSet;

public class Name {
    public static void main(String[] args){
        String[] names = {"민수", "지연", "민수", "서준", "지연", "하나"};
        HashSet<String> set = new HashSet<>();
        int duplicated = 0;
        for(String name: names){
            if(!set.add(name)){
                duplicated++;
            }
        }

        System.out.println("전체 신청: " + names.length);
        System.out.println("실제 참가자: " + set.size());
        System.out.println("중복 신청: " + duplicated);
    }
}
