package ch11;

public class ex1 {
    public static void main(String[] args) {
        try {
            String name = "ABC";
            name = null;
            System.out.println(name.length());
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
        } finally {
            System.out.println("종료");
        }
    }
}
