package part2.ex3_filename;

// 수업자료 108페이지: 파일명 정렬
// https://school.programmers.co.kr/learn/courses/30/lessons/17686

import java.util.Arrays;
import java.util.Comparator;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        Solution sol = new Solution();
        var r1 = sol.solution(new String[]{"img12", "img10.png", "img02.png", "img1.png", "IMG01.GIF", "img2.JPG"});
        var r2 = sol.solution(new String[]{"F-5 Freedom Fighter", "B-50 Superfortress", "A-10 Thunderbolt II", "F-14 Tomcat"});

        System.out.println(Arrays.toString(r1));
        System.out.println(Arrays.toString(r2));
    }
}

class Solution {
    public String[] solution(String[] files) {
        return Arrays.stream(files)
                .map(FileName::getInstanceRegex)
                .sorted(Comparator.comparing(FileName::head).thenComparingInt(FileName::number))
                .map(FileName::fullName)
                .toArray(String[]::new);
    }
}

record FileName(String fullName, String head, int number) {
    static FileName getInstance(String file) {
        int headEndIndex = 0;
        for (int i = 0; i < file.length(); i++) {
            if (isNumber(file.charAt(i))) {
                headEndIndex = i;
                break;
            }
        }
        String head = file.substring(0, headEndIndex).toLowerCase(Locale.ROOT);

        int numEndIndex = headEndIndex;
        for (int i = headEndIndex; i < file.length(); i++) {
            if (!isNumber(file.charAt(i))) {
                numEndIndex = i;
                break;
            }
        }

        if (numEndIndex == headEndIndex) {
            numEndIndex = file.length();
        }

        int number = Integer.parseInt(file.substring(headEndIndex, numEndIndex));
        return new FileName(file, head, number);
    }

    private static boolean isNumber(char c) {
        return c >= '0' && c <= '9';
    }

    private static final Pattern PATTERN = Pattern.compile("(^[A-Za-z\s.-]+)([0-9]+)");
    static FileName getInstanceRegex(String file){
        Matcher matcher = PATTERN.matcher(file);

        if(!matcher.find()){
            throw new IllegalStateException();
        }

        String head = matcher.group(1).toLowerCase(Locale.ROOT);
        int number = Integer.parseInt(matcher.group(2));

        return new FileName(file, head, number);
    }
}