package ch7.test;

import ch7.Phone;

public class Call {
    public static void main(String[] args) {
        Phone phone = new Phone("A", "White");
        MyPhone myPhone = new MyPhone("B", "Black");

//        불가
//        System.out.println(phone.field);
    }
}
