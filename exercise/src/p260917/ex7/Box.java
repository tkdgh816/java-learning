package p260917.ex7;

public class Box<T> {
    private final T value;

    public Box(T value) {
        this.value = value;
    }

    public T get() {
        return value;
    }

    public static <T> Box<T> of(T value) {
        return new Box<>(value);
    }
}
