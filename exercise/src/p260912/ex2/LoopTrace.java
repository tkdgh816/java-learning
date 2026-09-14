package p260912.ex2;

// 요구사항: 1부터 3까지 더해서 6 출력. 반복 조건에 의도적인 오류가 있습니다.
public class LoopTrace {
    public static void main(String[] args) {
        int limit = 3;
        int total = 0;
        for (int i = 1; i < limit; i++) {
            total += i; // 이 줄에 중단점
        }
        System.out.println(total);
    }
}