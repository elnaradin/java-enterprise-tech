package com.skillbox;

import org.apache.commons.collections4.IterableUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class Collections {
    static <T, R> List<R> map(Function1<? super T, ? extends R> f, Iterable<T> a) {
        List<R> result = new ArrayList<>();
        for (T t : a) {
            result.add(f.apply(t));
        }
        return result;
    }

    public static <T> List<T> filter(Predicate<T> p, Iterable<T> a) {
        List<T> result = new ArrayList<>();
        for (T t : a) {
            if (p.test(t)) {
                result.add(t);
            }
        }
        return result;
    }

    public static <T> List<T> takeWhile(Predicate<T> p, Iterable<T> a) {
        List<T> result = new ArrayList<>();
        ListIterator<T> li = getListIterator(a);
        while (li.hasPrevious()) {
            T t = li.previous();
            if (!p.test(t)) {
                break;
            }
            result.add(t);
        }
        return result;
    }

    public static <T> List<T> takeUnless(Predicate<T> p, Iterable<T> a) {
        return takeWhile(p.not(), a);
    }

    public static <T> T foldl(Function2<T, T, T> f, T initial, Iterable<T> a) {
        T result = initial;
        for (T t : a) {
            result = f.apply(result, t);
        }
        return result;
    }

    public static <T> T foldr(Function2<T, T, T> f, T initial, Iterable<T> a) {
        T result = initial;
        ListIterator<T> li = getListIterator(a);
        while (li.hasPrevious()) {
            T t = li.previous();
            result = f.apply(result, t);
        }
        return result;
    }

    private static <T> ListIterator<T> getListIterator(Iterable<T> a) {
        List<T> list = IterableUtils.toList(a);
        return list.listIterator(list.size());
    }

}
