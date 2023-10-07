package com.skillbox;

@FunctionalInterface
public interface Function2<T> {
    T apply(T t1, T t2);
    default Function2<T> compose(Function1<? super T, ? extends T> after) {
        return (T t1, T t2) -> after.apply(apply(t1, t2));
    }

    default Function2<T> bind1(T t3) {
        return (T t1, T t2) -> apply(t3, apply(t1, t2));
    }

    default Function2<T> bind2(T t3) {
        return (T t1, T t2) -> apply(apply(t1, t2), t3);
    }
}
