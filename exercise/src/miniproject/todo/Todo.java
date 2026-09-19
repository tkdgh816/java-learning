package miniproject.todo;

public class Todo {
    private final int number;
    private String title;
    private boolean completed = false;

    public Todo(int number, String title) {
        this.number = number;
        this.title = title;
    }

    public int getNumber() {
        return number;
    }

    public String getTitle() {
        return title;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void complete(){
        completed = true;
    }
}
