package p260917.ex3_1;

public enum OrderStatus {
    // 실습 또는 과제 안내에 나온 상수를 직접 선언하세요.
    READY("준비 중"),
    SHIPPED("배송 중"),
    DELIVERED("배송 완료");

    private final String message;

    OrderStatus(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return message;
    }
}
