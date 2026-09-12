package p260912.ex2;

public class Test {
    public static void main(String[] args){
        String name1 = "홍길동";
        String name2 = "홍길동";
        String name3 = new String("홍길동");
        String name4 = new String("홍길동");


        System.out.println(name1 == name2);
        System.out.println(name1 == name3);
        System.out.println(name3 == name4);
        System.out.println(name1.equals(name2));
        System.out.println(name1.equals(name3));
    }
}
