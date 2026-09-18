package p260917.ex7;

public class BoxDemo {
    public static void main(String[] args) {
        Box<String> name = new Box<>("SeSAC");
        Box<Integer> quantity = new Box<>(3);

        System.out.println(name.get().toUpperCase());
        System.out.println(quantity.get() + 1);

        // 아래 줄의 주석을 풀어 오류를 확인한 뒤 다시 주석으로 바꿉니다.
//         Box<String> wrong = new Box<>(3);
        Box<Double> price = new Box<>(12.5);
        System.out.println(price.get() * 2);
    }
}
