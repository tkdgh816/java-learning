package kr.sesac.wordcounter;

import kr.sesac.wordcounter.analysis.AnalysisException;
import kr.sesac.wordcounter.analysis.AnalysisMode;
import kr.sesac.wordcounter.analysis.Analyzer;
import kr.sesac.wordcounter.analysis.TokenAnalyzer;
import kr.sesac.wordcounter.analysis.result.AnalysisSummary;
import kr.sesac.wordcounter.analysis.test.JdbcTestAnalyzer;
import kr.sesac.wordcounter.model.TokenCount;
import kr.sesac.wordcounter.report.ReportWriter;
import kr.sesac.wordcounter.repository.InMemoryRepository;
import kr.sesac.wordcounter.repository.JdbcRepository;
import kr.sesac.wordcounter.repository.RepositoryException;
import kr.sesac.wordcounter.repository.TokenCountRepository;
import kr.sesac.wordcounter.repository.test.DbAggregationMode;
import kr.sesac.wordcounter.text.tokenizer.SimpleTokenizer;
import kr.sesac.wordcounter.text.tokenizer.TextTokenizer;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.Scanner;

public class Main {
    // 심화 1번
    // 토크나이저 선택 (정규식 사용 -> RegexTokenizer, 문자 검사 -> SimpleTokenizer)
    private static final TextTokenizer TOKENIZER = new SimpleTokenizer();

    // 심화 2번
    // 여러 파일 분석 모드 선택 (순차 처리 -> SEQUENTIAL, 병렬 처리 -> FIXED_THREAD_POOL)
    private static final AnalysisMode ANALYSIS_MODE = AnalysisMode.SEQUENTIAL;

    // 심화 7번
    // 단어별 횟수 저장 방식 선택 (메모리 -> IN_MEMORY, 데이터베이스 -> JDBC)
    // 새 분석을 시작할 때마다 clear() 호출하여 기존 분석 내용을 초기화 후 분석
    private static final TokenCountRepository REPOSITORY = new InMemoryRepository();

    // 입력 경로와 토크나이저, 저장 방식, 분석 모드 등을 받아서
    // 분석을 수행하고 분석 결과를 집계하는 객체
    // 새 분석 시 createAnalyzer()에서 객체를 만들고 분석을 시작
    private static TokenAnalyzer analyzer;

    // 심화 7번 1~4단계
    private static final DbAggregationMode DB_AGGREGATION_MODE = DbAggregationMode.STAGE_4;

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        printMenu();
        while (true) {
            int menu = readMenu();

            switch (menu) {
                case 1 -> {
                    try {
                        analyzer = createAnalyzer();
                        printSummary(true);
                    } catch (AnalysisException | RepositoryException e) {
                        analyzer = null;
                        System.out.println("분석을 완료하지 못했습니다: " + e.getMessage());
                    }
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

    private static TokenAnalyzer createAnalyzer() {
        while (true) {
            try {
                System.out.print("파일 또는 폴더 경로: ");
                String pathString = scanner.nextLine().strip();
                return Analyzer.analyze(pathString, TOKENIZER, REPOSITORY, ANALYSIS_MODE);

                // 심화 7번 1~4단계
                // 1~4단계는 토큰 하나 혹은 아직 집계되지 않은 토큰 목록 단위로 DB에 저장하므로
                // 파일별 토큰 집계 객체인 FileTokenCounter를 사용하는 Analyzer가 아닌 JdbcTestAnalyzer 사용
//                 return JdbcTestAnalyzer.analyze(pathString, TOKENIZER, DB_AGGREGATION_MODE, ANALYSIS_MODE);
            } catch (IOException | AnalysisException | RepositoryException | IllegalArgumentException e) {
                System.out.println(e.getMessage());
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

    // 기존 분석이 존재하는지 확인
    private static boolean isAnalysisInvalid() {
        if (analyzer == null) {
            System.out.println("분석을 먼저 해 주세요.");
            System.out.println();
            return true;
        }

        return false;
    }

    // 분석이 존재하고 분석이 성공했는지 확인
    private static boolean isAnalysisResultInvalid() {
        if (isAnalysisInvalid()) {
            return true;
        }

        if (!analyzer.hasSuccessfulFiles()) {
            System.out.println("모든 파일 분석에 실패했습니다.");
            System.out.println();
            return true;
        }

        return false;
    }

    private static void printSummary(boolean containsFailures) {
        if (isAnalysisInvalid()) {
            return;
        }

        AnalysisSummary summary = analyzer.getSummary();
        if (containsFailures) {
            System.out.println(ReportWriter.generateFailureSummary(summary.failures()));
        }

        System.out.println(ReportWriter.generateAnalysisSummary(summary));
    }

    private static final int DEFAULT_TOP_WORD_COUNT = 10;

    private static void printTopWords() {
        if (isAnalysisResultInvalid()) {
            return;
        }

        while (true) {
            try {
                System.out.printf("몇 개를 볼까요? (기본 %d) > ", DEFAULT_TOP_WORD_COUNT);
                String input = scanner.nextLine().strip();

                // 입력이 빈 경우 기본값으로 처리함
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
            } catch (RepositoryException e) {
                System.out.println("결과를 조회할 수 없습니다: " + e.getMessage());
                break;
            }
        }

        System.out.println();
    }

    private static void findWord() {
        if (isAnalysisResultInvalid()) {
            return;
        }

        while (true) {
            System.out.print("찾을 단어 > ");
            String word = scanner.nextLine().strip();

            try {
                TokenCount tokenCount = analyzer.findByWord(word);
                System.out.printf("%s: %d회%n", tokenCount.token(), tokenCount.count());
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("단어 하나를 입력하세요.");
            } catch (RepositoryException e) {
                System.out.println("결과를 조회할 수 없습니다: " + e.getMessage());
                break;
            }
        }

        System.out.println();
    }

    private static final Path OUTPUT_PATH = Path.of("out/counts.tsv");

    private static void saveResults() {
        if (isAnalysisResultInvalid()) {
            return;
        }

        try {
            List<TokenCount> tokenCounts = analyzer.getAllWordsSorted();
            ReportWriter.saveAsFile(OUTPUT_PATH, tokenCounts);
            System.out.printf("전체 결과 %d개 단어를 %s에 저장했습니다.%n", tokenCounts.size(), OUTPUT_PATH);
        } catch (IOException e) {
            System.out.println("결과를 저장하지 못했습니다: " + e.getMessage());
        } catch (RepositoryException e) {
            System.out.println("결과를 조회할 수 없습니다: " + e.getMessage());
        }

        System.out.println();
    }
}


/* 분석 경로
samples/equivalent
samples/edge
samples/invalid
data/chatbot
data/klue-ynat
data/klue-ynat/news-1000.csv
data/klue-ynat/news-10000.csv
data/klue-ynat/formats
data/klue-ynat/many
data/licenses
data/performance
data/performance/news-repeat-8.csv
data/performance/many
data/local/perf-64
data/local/perf-64/news-repeat-64.csv
data/local/perf-64/many
*/