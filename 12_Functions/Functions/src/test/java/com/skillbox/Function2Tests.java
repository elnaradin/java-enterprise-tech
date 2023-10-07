package com.skillbox;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Function2Tests {

    @Test
    void apply() {
        Function2<Integer, Integer, Integer> sum = Integer::sum;
        assertEquals(12, sum.apply(4, 8));
    }

    @Test
    void compose() {
        Function2<Integer, Integer, Integer> f = Integer::sum;
        Function2<Integer, Integer, Integer> compose = f.compose((total) -> (int) Math.sqrt((double) total));
        assertEquals(6, compose.apply(28, 8));
    }

    @Test
    void bind1() {
        Function2<String, String, String> f = (a, b) -> a + ", " + b;
        Function1<String, String> bind = f.bind1("b");
        assertEquals("b, a", bind.apply("a"));
    }

    @Test
    void bind2() {
        Function2<String, String, String> f = (a, b) -> a + ", " + b;
        Function1<String, String> bind = f.bind2("b");
        assertEquals("a, b", bind.apply("a"));
    }
}