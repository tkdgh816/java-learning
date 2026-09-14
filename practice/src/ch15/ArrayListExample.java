package ch15;

import java.util.List;
import java.util.ArrayList;

public class ArrayListExample {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("AAA");
        list.add("BBB");
        list.add("EEE");
        System.out.println(list.get(1));
        list.set(2, "DDD");
        list.add(2, "CCC");
        for(String element : list){
            System.out.println(element);
        }
    }
}
