package q468370;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// 중요한 단어를 스포 방지
// https://school.programmers.co.kr/learn/courses/30/lessons/468370
public class Test {
    public static void main(String[] args) {
        Solution sol = new Solution();
        System.out.println(sol.solution("here is muzi here is a secret message", new int[][]{{0, 3}, {23, 28}}));
        System.out.println(sol.solution("my phone number is 01012345678 and may i have your phone number", new int[][]{{5, 5}, {25, 28}, {34, 40}, {53, 59}}));
    }
}

class Solution {
    public int solution(String message, int[][] spoiler_ranges) {
        List<WordInfo> wordInfos = new ArrayList<>();

        Matcher matcher = Pattern.compile("[a-z0-9]+").matcher(message);
        while (matcher.find()) {
            wordInfos.add(new WordInfo(matcher.group(), matcher.start()));
        }

//        String[] words = message.split("\\s+");
//        int index = 0;
//        for (String word : words) {
//            int start = message.indexOf(word, index);
//            wordInfos.add(new WordInfo(word,start));
//            index = start + word.length();
//        }

        for (int[] spoiler_range : spoiler_ranges) {
            for (WordInfo wordInfo : wordInfos) {
                if (wordInfo.overlaps(spoiler_range[0], spoiler_range[1])) {
                    wordInfo.isHidden = true;
                }
            }
        }

        int count = 0;
        Set<String> spoiled = new HashSet<>();
        for (WordInfo wordInfo : wordInfos) {
            if (!wordInfo.isHidden) {
                spoiled.add(wordInfo.word);
            }
        }

        for (WordInfo wordInfo : wordInfos) {
            if (wordInfo.isHidden) {
                if (spoiled.add(wordInfo.word)) {
                    count++;
                }
                wordInfo.isHidden = false;
            }
        }
        return count;
    }

    private static class WordInfo {
        final String word;
        final int start;
        final int end;
        boolean isHidden = false;

        WordInfo(String word, int start) {
            this.word = word;
            this.start = start;
            end = start + word.length() - 1;
        }

        boolean overlaps(int start, int end) {
            return this.start <= end && start <= this.end;
        }
    }
}