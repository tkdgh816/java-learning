package kr.sesac.wordcounter;

import kr.sesac.wordcounter.analysis.*;
import kr.sesac.wordcounter.text.tokenizers.SimpleTokenizer;
import kr.sesac.wordcounter.text.tokenizers.TextTokenizer;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Scanner;

/*
samples/equivalent
samples/edge
samples/invalid
data/chatbot
data/klue-ynat
data/klue-ynat/formats
data/klue-ynat/many
data/licenses
data/performance
data/performance/many
data/local/perf-64
data/local/perf-64/news-repeat-64.csv
data/local/perf-64/many
*/

public class Main {
    private static Analyzer analyzer;
    private static final TextTokenizer TOKENIZER = new SimpleTokenizer();
    private static final AnalysisMode ANALYSIS_MODE = AnalysisMode.FIXED_THREAD_POOL;
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        printMenu();
        while (true) {
            int menu = readMenu();

            switch (menu) {
                case 1 -> {
                    analyzer = getAnalyzer();
                    printSummary(true);
                }
                case 2 -> printTopWords();
                case 3 -> findWord();
                case 4 -> saveResults();
                case 5 -> printSummary(false);
                case 0 -> {
                    System.out.println("프로그램을 종료합니다.");
                    return;
                }
            }
        }
    }

    private static void printMenu() {
        System.out.println("""
                ====================
                문서 단어 분석기
                1. 새 분석 시작
                2. 상위 N개 단어 보기
                3. 특정 단어 횟수 찾기
                4. 전체 결과 저장
                5. 최근 분석 요약 보기
                0. 종료
                ====================""");
        System.out.println();
    }

    private static int readMenu() {
        while (true) {
            System.out.print("선택 > ");
            String input = scanner.nextLine().strip();
            try {
                int menu = Integer.parseInt(input);
                if (menu >= 0 && menu <= 5) {
                    return menu;
                }
            } catch (NumberFormatException e) {
                // 번호가 아니므로 아래에서 다시 선택하도록 함
            }

            System.out.println("메뉴에 있는 번호를 선택하세요.");
            System.out.println();
            printMenu();
        }
    }

    private static boolean validateAnalysis() {
        if (analyzer == null) {
            System.out.println("분석을 먼저 해 주세요.");
            System.out.println();
            return false;
        }

        return true;
    }

    private static boolean validateAnalyzerResult() {
        if (!validateAnalysis()) {
            return false;
        }

        if (!analyzer.hasSuccessfulFiles()) {
            System.out.println("모든 파일 분석에 실패했습니다.");
            System.out.println();
            return false;
        }

        return true;
    }

    private static void printSummary(boolean containsFailures) {
        if (!validateAnalysis()) {
            return;
        }

        AnalysisSummary result = analyzer.getSummary();
        StringBuilder summary = new StringBuilder();

        if (containsFailures) {
            for (FileAnalysisFailure failure : result.failures()) {
                summary.append(String.format("파일 분석 실패: %s%n", failure.filePath()));
                summary.append(String.format("원인: %s%n%n", failure.reason()));
            }
        }

        summary.append(String.format("입력: %s%n", result.inputPath()));
        summary.append(String.format("파일: 시도 %d개 / 성공 %d개 / 실패 %d개 / 건너뜀 %d개%n",
                result.fileSummary().attemptedCount(),
                result.fileSummary().successfulCount(),
                result.fileSummary().failedCount(),
                result.fileSummary().skippedCount()));
        summary.append(String.format("전체 단어: %d개 / 서로 다른 단어: %d개%n",
                result.wordSummary().totalCount(),
                result.wordSummary().uniqueCount()));
        summary.append(String.format("처리 시간: %dms%n", result.elapsedMilliseconds()));

        System.out.println(summary);
    }

    private static Analyzer getAnalyzer() {
        while (true) {
            try {
                System.out.print("파일 또는 폴더 경로: ");
                String pathString = scanner.nextLine().strip();
                return new Analyzer(pathString, TOKENIZER, ANALYSIS_MODE);
            } catch (IOException | IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private static final int DEFAULT_TOP_WORD_COUNT = 10;

    private static void printTopWords() {
        if (!validateAnalyzerResult()) {
            return;
        }

        while (true) {
            try {
                System.out.printf("몇 개를 볼까요? (기본 %d) > ", DEFAULT_TOP_WORD_COUNT);
                String input = scanner.nextLine().strip();

                int number = input.isBlank() ? DEFAULT_TOP_WORD_COUNT : Integer.parseInt(input);
                if (number > 0) {
                    List<TokenCount> tokenCounts = analyzer.getTopWords(number);
                    for (int i = 0; i < tokenCounts.size(); i++) {
                        TokenCount tokenCount = tokenCounts.get(i);
                        System.out.printf("%d. %s : %d회%n", i + 1, tokenCount.token(), tokenCount.count());
                    }
                    break;
                } else {
                    System.out.println("양수를 입력하세요.");
                }
            } catch (NumberFormatException e) {
                System.out.println("숫자를 입력하세요.");
            }
        }

        System.out.println();
    }

    private static void findWord() {
        if (!validateAnalyzerResult()) {
            return;
        }

        while (true) {
            System.out.print("찾을 단어 > ");
            String word = scanner.nextLine().strip();

            try {
                TokenCount tokenCount = analyzer.findWord(word);
                System.out.printf("%s: %d회%n", tokenCount.token(), tokenCount.count());
                break;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }

        System.out.println();
    }

    private static final Path OUTPUT_PATH = Path.of("out/counts.tsv");

    private static void saveResults() {
        if (!validateAnalyzerResult()) {
            return;
        }

        try {
            Files.createDirectories(OUTPUT_PATH.getParent());
            try (BufferedWriter writer = Files.newBufferedWriter(OUTPUT_PATH, StandardCharsets.UTF_8)) {
                List<TokenCount> tokenCounts = analyzer.getAllWordsSorted();
                writer.write("word\tcount\n");
                for (TokenCount tokenCount : tokenCounts) {
                    writer.write(String.format("%s\t%d%n", tokenCount.token(), tokenCount.count()));
                }
                System.out.printf("전체 결과 %d개 단어를 %s에 저장했습니다.%n", tokenCounts.size(), OUTPUT_PATH);
            }
        } catch (IOException e) {
            System.out.println("결과를 저장하지 못했습니다: " + e.getMessage());
        }

        System.out.println();
    }
}