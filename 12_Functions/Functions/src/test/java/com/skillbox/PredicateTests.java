package com.skillbox;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PredicateTests {

    @Test
    void test1() {
        Predicate<Integer> p = (number) -> number > 0;
        assertTrue(p.test(5));
    }

    @Test
    void or() {
        Predicate<Integer> p1 = (number) -> number > 0;
        Predicate<Integer> p2 = p1.or(Predicate.ALWAYS_FALSE);
        assertTrue(p2.test(5));
    }

    @Test
    void and() {
        Predicate<Integer> p1 = (number) -> number > 0;
        Predicate<Integer> p2 = p1.and(Predicate.ALWAYS_TRUE);
        assertTrue(p2.test(5));
    }

    @Test
    void not() {
        Predicate<Integer> p1 = (number) -> number > 0;
        Predicate<Integer> p2 = p1.not();
        assertFalse(p2.test(5));
    }
}