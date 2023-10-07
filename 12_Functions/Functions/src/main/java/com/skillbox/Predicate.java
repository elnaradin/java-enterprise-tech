package com.skillbox;
@FunctionalInterface
public interface Predicate<T> {
    Predicate<Object> ALWAYS_TRUE = t -> true;
    Predicate<Object> ALWAYS_FALSE = t -> false;

    boolean test(T t);

    default Predicate<T> or(Predicate<? super T> other) {
        return (T t) -> test(t) || other.test(t);
    }

    default Predicate<T> and(Predicate<? super T> other) {
        return (T t) -> test(t) && other.test(t);
    }

    default Predicate<T> not() {
        return (T t) -> !test(t);
    }
}
