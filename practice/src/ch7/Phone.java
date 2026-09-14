package ch7;

public class Phone {
    public String model;
    public String color;
    protected String field = "field";

    public Phone(String model, String color) {
        this.model = model;
        this.color = color;
    }

    public void bell() {
        System.out.println("벨이 울립니다.");
    }

    public void sendVoice(String message) {
        System.out.println("자기: " + message);
    }

    public void receiveVoice(String message) {
        System.out.println("상대방: " + message);
    }

    public final void hangUp() {
        System.out.println("전화를 끊습니다.");
    }
}