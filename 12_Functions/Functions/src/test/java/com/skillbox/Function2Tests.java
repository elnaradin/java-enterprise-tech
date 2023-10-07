package com.skillbox;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Function2Tests {

    @Test
    void apply() {
        Function2<Integer> sum = Integer::sum;
        assertEquals(12, sum.apply(4, 8));
    }

    @Test
    void compose() {
        Function2<Integer> f = Integer::sum;
        Function2<Integer> compose = f.compose((total) -> (int) Math.sqrt((double) total));
        assertEquals(6, compose.apply(28, 8));
    }

    @Test
    void bind1() {
        Function2<String> f = (a, b) -> a + ", " + b;
        Function2<String> bind = f.bind1("c");
        assertEquals("c, a, b", bind.apply("a", "b"));
    }

    @Test
    void bind2() {
        Function2<String> f = (a, b) -> a + ", " + b;
        Function2<String> bind = f.bind2("c");
        assertEquals("a, b, c", bind.apply("a", "b"));
    }
}