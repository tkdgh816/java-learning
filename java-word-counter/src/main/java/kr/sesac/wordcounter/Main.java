package kr.sesac.wordcounter;

import kr.sesac.wordcounter.analyzer.Analyzer;

import java.io.IOException;
import java.util.*;

/**
 * 첫 실행용 코드입니다. TXT 내용을 읽은 뒤 TODO를 채우며 기능을 추가하세요.
 * 구현 기준은 docs/requirements.md에 있습니다.
 */
public class Main {
    private static Analyzer analyzer;
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        printMenu();
        while (true) {
            int menu = readMenu();

            switch (menu) {
                case 1 -> {
                    analyzer = getAnalyzer();
                    System.out.println(analyzer.getReport());
                }
                case 2 -> printTopWords();
                case 3 -> findWords();
                case 4 -> saveReport();
                case 5 -> summarize();
                case 0 -> {
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
        if (analyzer != null) {
            System.out.println("입력 경로: " + analyzer.getInputPath());
        }

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

            }

            System.out.println("메뉴에 있는 번호를 선택하세요.");
            System.out.println();
            printMenu();
        }
    }

    private static Analyzer getAnalyzer() {
        while (true) {
            try {
                System.out.print("파일 또는 폴더 경로: ");
                String pathString = scanner.nextLine().strip();
                return Analyzer.analyze(pathString);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            } catch (IOException e){
                System.out.println(e.getMessage());
            }
        }
    }

    private static void printTopWords() {
        if (analyzer == null) {
            System.out.println("분석을 먼저 해 주세요.");
            return;
        }

        while (true) {
            try {
                System.out.print("몇 개를 볼까요? (기본 10, 나가기 0) > ");
                String input = scanner.nextLine().strip();
                int number = Integer.parseInt(input);

                if (number > 0) {
                    analyzer.getTopWordsAndCounts();
                    System.out.println("개수 표시");
                    break;
                } else if (number == 0) {
                    System.out.println("표시할 항목이 없습니다.");
                    break;
                } else {
                    System.out.println("양수를 입력하세요.");
                }
            } catch (NumberFormatException e) {
                System.out.println("숫자를 입력하세요.");
            }
        }
    }

    private static void findWords() {
        if (analyzer == null) {
            System.out.println("분석을 먼저 해 주세요.");
            return;
        }
        System.out.print("찾을 단어 > ");
        String word = scanner.nextLine().strip();
    }

    private static void saveReport() {
        if (analyzer == null) {
            System.out.println("분석을 먼저 해 주세요.");
            return;
        }
    }

    private static void summarize() {
        if (analyzer == null) {
            System.out.println("분석을 먼저 해 주세요.");
            return;
        }
    }
}

/*
public class Main {
    public static void main(String[] args) throws IOException {
        Path input = Path.of("samples/equivalent/basic.txt");

        System.out.println("문서 단어 분석기 - 시작 코드");
        System.out.println("입력 파일: " + input);
        System.out.println();

        Map<String, Integer> wordCount = new LinkedHashMap<>();
        // TODO 1: 단어별 출현 횟수를 저장할 자료구조를 준비하세요. (요구사항 4. 단어별 횟수 집계)
        try (BufferedReader reader =
                     Files.newBufferedReader(input, StandardCharsets.UTF_8)) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
                // TODO 2: line을 요구사항 3. 단어 처리 규칙대로 단어(토큰)로 나누세요.
                List<String> words = getTokens(line);
                // TODO 3: 숫자만 있는 단어는 제외하고 단어별 횟수를 늘리세요.
                words.stream()
                        .filter(word -> !numberPattern.matcher(word).matches())
                        .forEach(word -> {
                            String lowerCaseWord = word.toLowerCase(Locale.ROOT);
                            wordCount.put(lowerCaseWord, wordCount.getOrDefault(lowerCaseWord, 0) + 1);
                        });
            }
        }

        System.out.println();
        System.out.println("파일 읽기 성공. 다음 단계는 단어 분리와 카운팅입니다.");
        System.out.println("구현 후 전체 9개·6종인지 expected/basic-counts.tsv와 비교하세요.");
        // TODO 4: 원문 출력 대신 집계 결과를 출력하세요.
        // TXT 카운팅 완성 후 다른 형식, 메뉴, 오류 처리, 저장을 추가하세요.
        System.out.println("word\tcount");
        wordCount.forEach((word, count) -> System.out.println(word + "\t" + count));
    }

    private static Pattern wordPattern = Pattern.compile("[a-zA-Z0-9가-힣ㄱ-ㅎㅏ-ㅣ]+");
    private static Pattern numberPattern = Pattern.compile("[0-9]+");
    private static List<String> getTokens(String line){
        List<String> words = new ArrayList<>();
        Matcher matcher = wordPattern.matcher(line);
        while(matcher.find()){
            words.add(matcher.group());
        }
        return words;
    }
}
*/