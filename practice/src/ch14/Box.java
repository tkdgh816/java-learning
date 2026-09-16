package ch14;

class Box<T> implements Container<T> {
    private T value;

    @Override
    public void putIn(T value) {
        if (this.value != null) {
            takeOut();
        }

        this.value = value;
        System.out.println(value + " 들어갔습니다.");
    }

    @Override
    public T takeOut() {
        if (value == null) {
            System.out.println("아무것도 없습니다.");
            return null;
        }

        T result = value;
        System.out.println(value + " 나갔습니다.");
        value = null;
        return result;
    }
}