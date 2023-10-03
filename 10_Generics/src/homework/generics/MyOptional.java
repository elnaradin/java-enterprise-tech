package homework.generics;

public class MyOptional<T> {
    private final T value;
    private static final MyOptional<?> EMPTY = new MyOptional<>(null);

    public MyOptional(T value) {
        this.value = value;
    }

    static <T> MyOptional<T> of(T value) {
        if (value == null) {
            throw new InvalidParameterException();
        }
        return new MyOptional<>(value);
    }

    @SuppressWarnings("unchecked")
    static <T> MyOptional<T> ofNullable(T value) {
        return value == null ? (MyOptional<T>) EMPTY : new MyOptional<>(value);
    }

    public T get() {
        if (value == null) {
            throw new InvalidParameterException();
        }
        return value;
    }

    public boolean isPresent() {
        return value != null;
    }

    public T orElse(T other) {
        return value != null ? value : other;
    }
}
