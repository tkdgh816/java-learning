package ch7;


public class SmartPhone extends Phone {
    public boolean wifi;

    public SmartPhone(String model, String color) {
        super(model, color);
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
        System.out.println("스마트폰에서 실행됨");
    }

//    불가능
//    @Override
//    public void hangUp(){
//
//    }
}