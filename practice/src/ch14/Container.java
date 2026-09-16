package ch14;

public interface Container<T> {
    void putIn(T value);

    T takeOut();
}
