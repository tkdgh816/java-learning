package helpers;

import java.util.Arrays;
import java.util.Objects;

public class Test {
    public static void check(String name, Object actual, Object expected) {
        boolean ok = Objects.deepEquals(actual, expected);
        System.out.printf("%s %s \t %s (exp: %s)%n", name, ok ? "✅" : "❌" , str(actual), str(expected));
    }

    // 배열이면 deepToString, 아니면 String.valueOf (null 안전)
    static String str(Object o) {
        if (o != null && o.getClass().isArray()) {
            String s = Arrays.deepToString(new Object[]{o}); // int[]도 처리됨
            return s.substring(1, s.length() - 1);           // 바깥 [] 제거
        }
        return String.valueOf(o);
    }
}
