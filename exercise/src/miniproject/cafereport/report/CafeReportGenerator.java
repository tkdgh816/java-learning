package miniproject.cafereport.report;

import miniproject.cafereport.domain.*;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

public class CafeReportGenerator {
    public static String generate(Collection<Branch> branches, int errorCount) {
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

            for (Map.Entry<String, SalesSummary> salesEntry : branch.getSalesByMenu().entrySet()) {
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
