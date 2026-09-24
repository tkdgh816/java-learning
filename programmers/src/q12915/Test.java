package q12915;

import java.util.Arrays;

// 문자열 내 마음대로 정렬하기
// https://school.programmers.co.kr/learn/courses/30/lessons/12915
public class Test {
    public static void main(String[] args) {
        Solution sol = new Solution();
        System.out.println(Arrays.toString(sol.solution(new String[]{"sun", "bed", "car"}, 1)));
        System.out.println(Arrays.toString(sol.solution(new String[]{"abce", "abcd", "cdx"}, 2)));
    }
}

class Solution {
    public String[] solution(String[] words, int n) {
        return Arrays.stream(words)
                .map(word -> new WordInfo(word, n))
                .sorted()
                .map(WordInfo::getWord)
                .toArray(String[]::new);
    }
}

class WordInfo implements Comparable<WordInfo> {
    private final String word;
    private final char nChar;

    WordInfo(String word, int index) {
        this.word = word;
        nChar = word.charAt(index);
    }

    public String getWord() {
        return word;
    }

    @Override
    public int compareTo(WordInfo other) {
        int diff = this.nChar - other.nChar;
//        diff = Character.compare(this.nChar, other.nChar);

        if (diff == 0) {
            return this.word.compareTo(other.word);
        }

        return diff;
    }
}