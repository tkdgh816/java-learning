package ch7;

public class Call {
    public static void main(String[] args){
        Phone phone = new Phone("A", "White");
        SmartPhone smartPhone = new SmartPhone("B", "Black");
        System.out.println(phone.field);
    }
}
