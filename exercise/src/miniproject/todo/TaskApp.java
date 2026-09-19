package miniproject.todo;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TaskApp {
    private static final List<Todo> todoList = new ArrayList<>();

    public static void main(String[] args) {
        // TODO: 할 일 목록을 준비하고 안내에 나온 세 항목을 등록하세요.
        // 목록은 아래 while 바깥에 두어 메뉴를 바꿔도 유지되도록 합니다.
        addTodo("자바 복습");
        addTodo("예외 처리 정리");
        addTodo("파일 입출력 예제 실행");
        todoList.get(1).complete();

        try (Scanner scanner = new Scanner(System.in)) {
            boolean running = true;
            while (running) {
                printMenu();
                int menu = readInt(scanner, "선택: ");
                switch (menu) {
                    case 1: {
                        String title = readText(scanner, "할 일 제목: ");
                        // TODO: 제목을 검사하고 미완료 항목을 추가하세요.
                        if (addTodo(title)) {
                            System.out.println("할 일을 추가했습니다.");
                        } else {
                            System.out.println("할 일을 추가하지 못했습니다.");
                        }
                        break;
                    }
                    case 2:
                        // TODO: 전체 목록을 출력하세요.
                        printAllTodos();
                        break;
                    case 3:
                        // TODO: 미완료 항목만 출력하세요.
                        printIncompleteTodos();
                        break;
                    case 4: {
                        int targetId = readInt(scanner, "완료할 번호: ");
                        // TODO: 번호로 찾은 항목을 완료 처리하세요.
                        completeTodo(targetId);
                        break;
                    }
                    case 5:
                        // TODO: 전체·완료·미완료 개수를 출력하세요.
                        printStatus();
                        break;
                    case 0:
                        running = false;
                        System.out.println("종료합니다.");
                        break;
                    default:
                        System.out.println("메뉴에 있는 번호를 선택하세요.");
                }
            }
        }
    }

    private static void printMenu() {
        System.out.println();
        System.out.println("1. 할 일 추가");
        System.out.println("2. 전체 목록 보기");
        System.out.println("3. 미완료 목록 보기");
        System.out.println("4. 완료 처리");
        System.out.println("5. 진행 상황 보기");
        System.out.println("0. 종료");
    }

    private static String readText(Scanner scanner, String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }

    private static int readInt(Scanner scanner, String prompt) {
        while (true) {
            String text = readText(scanner, prompt);
            try {
                return Integer.parseInt(text);
            } catch (NumberFormatException e) {
                System.out.println("정수로 입력하세요.");
            }
        }
    }

    private static boolean addTodo(String title) {
        if (title == null || title.isEmpty()) {
            return false;
        }

        todoList.add(new Todo(todoList.size() + 1, title));
        return true;
    }

    private static void printAllTodos() {
        System.out.println();
        System.out.println("전체 목록");

        if (todoList.size() == 0) {
            System.out.println("조회할 목록이 없습니다.");
            return;
        }

        for (Todo todo : todoList) {
            System.out.printf("%d. %s / %s%n",
                    todo.getNumber(),
                    todo.getTitle(),
                    todo.isCompleted() ? "완료" : "미완료");
        }
    }

    private static void printIncompleteTodos() {
        System.out.println();
        System.out.println("미완료 목록");

        if (!todoList.stream().anyMatch(todo -> !todo.isCompleted())) {
            System.out.println("미완료 목록이 없습니다.");
            return;
        }

        for (Todo todo : todoList) {
            if (!todo.isCompleted()) {
                System.out.printf("%d. %s / 미완료%n", todo.getNumber(), todo.getTitle());
            }
        }
    }

    private static void completeTodo(int number) {
        System.out.println();

        if (number < 1 || number > todoList.size()) {
            System.out.println("잘못된 번호입니다.");
            return;
        }

        Todo todo = todoList.get(number - 1);
        if (todo.isCompleted()) {
            System.out.println("이미 완료된 할 일입니다.");
            return;
        }
        todo.complete();
        System.out.println("할 일을 완료했습니다.");
    }

    private static void printStatus() {
        System.out.println();
        System.out.println("진행 상황");
        System.out.println("전체: " + todoList.size() + "개");
        int completedCount = (int) todoList.stream()
                .filter(Todo::isCompleted)
                .count();
        int incompleteCount = todoList.size() - completedCount;
        System.out.println("완료: " + completedCount + "개");
        System.out.println("미완료: " + incompleteCount + "개");
    }
}