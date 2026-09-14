package ch7.test;

import ch7.Phone;

public class MyPhone extends Phone implements  TestInterface{
    public boolean wifi;

    public MyPhone(String model, String color) {
        super(model, color);

//        다른 패키지의 상속 클래스에서 protected 접근 가능
        field = "myPhone field";

//        불가
//        Phone phone = new Phone(model, color);
//        System.out.println(phone.field);
    }

    @Override
    public int getNumber(){
        return 0;
    }

    public void setWifi(boolean wifi){
        this.wifi = wifi;
        System.out.println("와이파이 상태를 변경했습니다.");
    }

    public void internet(){
        System.out.println("인터넷에 연결합니다.");
    }

    @Override
    public void sendVoice(String message){
        super.sendVoice(message);
        System.out.println("내 폰에서 실행됨");
    }
}

interface TestInterface{
    int getNumber();

    default void setNumber(){

    }

    static void changeBattery(){

    }
}