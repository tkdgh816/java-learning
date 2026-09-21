package miniproject.cafereport;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.Map.Entry;

public class CafeReportApp {
    public static void main(String[] args) {
        Path[] inputFiles = {
                Path.of("data", "miniproject", "cafe-report", "branch-a.txt"),
                Path.of("data", "miniproject", "cafe-report", "branch-b.txt")
        };
        Path output = Path.of("out", "cafe-report.txt");

        // TODO: 전체·지점별·메뉴별 결과를 저장할 변수를 준비하세요.
        Map<String, Branch> branchesByName = new LinkedHashMap<>();
        int errorCount = 0;

        for (Path input : inputFiles) {
            String branchName = input.getFileName().toString().replace(".txt", "");

            try (BufferedReader reader = Files.newBufferedReader(input, StandardCharsets.UTF_8)) {
                Branch branch = branchesByName.computeIfAbsent(branchName, Branch::new);

                String line;
                int lineNumber = 0;
                while ((line = reader.readLine()) != null) {
                    lineNumber++;
                    // TODO: 항목을 나누고 검사한 뒤 정상 내역만 집계하세요.
                    Order order = parseOrder(line);

                    // order가 유효하지 않으면 오류 카운트 증가하고 메시지 출력
                    if (order == null) {
                        errorCount++;
                        System.out.println("[잘못된 줄] " + input.getFileName() + ": " + lineNumber + "행");
                        continue;
                    }

                    // 브랜치에 order 추가
                    branch.addOrder(order);
                }
            } catch (IOException e) {
                System.out.println("파일 읽기 실패: " + input.getFileName());
            }
        }

        // TODO: 보고서를 문자열로 만들고 콘솔 출력과 파일 저장을 구현하세요.
        String report = createReport(branchesByName.values(), errorCount);

        // 콘솔 출력
        System.out.println();
        System.out.println(report);

        // 파일 저장
        System.out.println();
        try {
            Files.createDirectories(output.getParent());
            Files.writeString(output, report, StandardCharsets.UTF_8);
            System.out.println("보고서 파일 저장 완료");
        } catch (IOException e) {
            System.out.println("보고서 파일 저장 실패");
        }
    }

    // 한 줄을 해석해서 Order 객체를 생성, 잘못된 줄이면 null 반환
    private static Order parseOrder(String line) {
        String[] words = line.split(",", -1);

        if (words.length != 3) {
            return null;
        }

        try {
            String menu = words[0].strip();
            int price = Integer.parseInt(words[1].strip());
            int quantity = Integer.parseInt(words[2].strip());
            return Order.create(menu, price, quantity);
        } catch (NumberFormatException e) {
            return null;
        } catch (InvalidOrderException e) {
            return null;
        }
    }

    // 보고서 문구 작성
    private static String createReport(Collection<Branch> branches, int errorCount) {
        StringBuilder report = new StringBuilder();
        Map<String, SalesSummary> totalSalesByMenu = new LinkedHashMap<>();

        int totalCount = 0;
        int totalQuantity = 0;
        long totalAmount = 0;

        StringBuilder branchReport = new StringBuilder();
        for (Branch branch : branches) {
            // 통합 계산
            totalCount += branch.getOrderCount();
            totalQuantity += branch.getTotalQuantity();
            totalAmount += branch.getTotalAmount();

            // 지점별 매출
            branchReport.append(String.format("%s: %d원%n", branch.getName(), branch.getTotalAmount()));

            for (Entry<String, SalesSummary> salesEntry : branch.getSalesByMenu().entrySet()) {
                SalesSummary salesSummary = totalSalesByMenu.computeIfAbsent(salesEntry.getKey(), m -> new SalesSummary());
                salesSummary.add(salesEntry.getValue());
            }
        }

        report.append(String.format("정상 내역: %d건%n", totalCount));
        report.append(String.format("제외한 내역: %d건%n", errorCount));
        report.append(String.format("전체 판매 수량: %d잔%n", totalQuantity));
        report.append(String.format("총매출: %d원%n", totalAmount));

        report.append("\n[지점별 매출]\n");
        report.append(branchReport);

        report.append("\n[메뉴별 판매]\n");
        for (var salesEntry : totalSalesByMenu.entrySet()) {
            String menu = salesEntry.getKey();
            SalesSummary sales = salesEntry.getValue();
            report.append(String.format("%s: %d잔 / %d원%n", menu, sales.getTotalQuantity(), sales.getTotalAmount()));
        }

        return report.toString().stripTrailing();
    }
}
