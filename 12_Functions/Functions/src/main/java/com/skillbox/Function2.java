package com.skillbox;


@FunctionalInterface
public interface Function2<T, U, R> {
    R apply(T t1, U t2);
    default <V> Function2<T, U, V> compose(Function1<? super R, ? extends V> after) {
        return (T t1, U t2) -> after.apply(apply(t1, t2));
    }

    default Function1<U, R> bind1(T t) {
        return (U u) -> apply(t, u);
    }

    default Function1<T, R> bind2(U u) {
        return (T t) -> apply(t, u);
    }
}
