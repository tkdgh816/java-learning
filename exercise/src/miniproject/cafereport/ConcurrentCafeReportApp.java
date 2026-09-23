package miniproject.cafereport;

import miniproject.cafereport.domain.*;
import miniproject.cafereport.parser.OrderParser;
import miniproject.cafereport.report.CafeReportGenerator;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class ConcurrentCafeReportApp {
    public static void main(String[] args) {
        Path[] inputFiles = {
                Path.of("data", "miniproject", "cafe-report", "branch-a.txt"),
                Path.of("data", "miniproject", "cafe-report", "branch-b.txt")
        };
        Path output = Path.of("out", "cafe-report.txt");

        // TODO: 전체·지점별·메뉴별 결과를 저장할 변수를 준비하세요.
        List<BranchFileThread> threads = new ArrayList<>();

        // 스레드 생성 및 실행
        for (Path input : inputFiles) {
            BranchFileThread thread = new BranchFileThread(input);
            thread.start();
            threads.add(thread);
        }

        // 최종 보고서 작성을 위한 브랜치 모음
        Map<String, Branch> branchesByName = new LinkedHashMap<>();
        int errorCount = 0;

        // 각 스레드 작업을 기다리고 결과를 브랜치 목록에 추가
        for (BranchFileThread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }

            Branch branch = thread.getBranch();
            if (branch != null) {
                branchesByName.put(branch.getName(), branch);
                errorCount += thread.getErrorCount();
            }
        }

        // TODO: 보고서를 문자열로 만들고 콘솔 출력과 파일 저장을 구현하세요.
        String report = CafeReportGenerator.generate(branchesByName.values(), errorCount);

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
}

class BranchFileThread extends Thread {
    private final Path input;
    private Branch branch;
    private int errorCount;

    public BranchFileThread(Path input) {
        this.input = input;
    }

    public int getErrorCount() {
        return errorCount;
    }

    public Branch getBranch() {
        return branch;
    }

    @Override
    public void run() {
        String branchName = input.getFileName().toString().replace(".txt", "");

        Branch br = new Branch(branchName);
        int branchErrorCount = 0;

        try (BufferedReader reader = Files.newBufferedReader(input, StandardCharsets.UTF_8)) {
            String line;
            int lineNumber = 0;
            while ((line = reader.readLine()) != null) {
                lineNumber++;
                // TODO: 항목을 나누고 검사한 뒤 정상 내역만 집계하세요.
                Order order = OrderParser.parse(line);

                // order가 유효하지 않으면 오류 카운트 증가하고 메시지 출력
                if (order == null) {
                    branchErrorCount++;
                    System.out.println("[잘못된 줄] " + input.getFileName() + ": " + lineNumber + "행");
                    continue;
                }

                // 브랜치에 order 추가
                br.addOrder(order);
            }

        } catch (IOException e) {
            System.out.println("파일 읽기 실패: " + input.getFileName());
            return;
        }

        // 파일 읽기가 성공한 후에 branch 필드에 대입
        this.branch = br;
        errorCount = branchErrorCount;
    }
}