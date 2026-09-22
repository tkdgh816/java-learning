package q12915;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

// 폰켓몬
// https://school.programmers.co.kr/learn/courses/30/lessons/1845
public class Test {
    public static void main(String[] args) {
        Solution sol = new Solution();
        System.out.println(Arrays.toString(sol.solution(new String[]{"sun", "bed", "car"}, 1)));
        System.out.println(Arrays.toString(sol.solution(new String[]{"abce", "abcd", "cdx"}, 2)));
    }
}

class Solution {
    public String[] solution(String[] words, int n) {
        WordInfo[] wordInfos = new WordInfo[words.length];
        for (int i = 0; i < words.length; i++) {
            wordInfos[i] = new WordInfo(words[i], n);
        }

        Arrays.sort(wordInfos);

        String[] results = new String[words.length];
        for (int i = 0; i < words.length; i++) {
            results[i] = wordInfos[i].getWord();
        }

        return results;
    }
}

class WordInfo implements Comparable<WordInfo> {
    private final String word;
    private final char nChar;

    WordInfo(String word, int index) {
        this.word = word;
        try {
            nChar = word.charAt(index);
        } catch (IndexOutOfBoundsException e) {
            throw new IllegalArgumentException();
        }
    }

    public String getWord() {
        return word;
    }

    @Override
    public int compareTo(WordInfo other) {
        int diff = this.nChar - other.nChar;

        if (diff == 0) {
            return this.word.compareTo(other.word);
        }

        return diff;
    }
}