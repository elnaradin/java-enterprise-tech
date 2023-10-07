package com.skillbox;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CollectionsTest {
    private List<Integer> a;

    @BeforeEach
    void setUp() {
        a = new ArrayList<>();
        for (int i = 1; i <= 5; i++) {
            a.add(i);
        }
    }

    @AfterEach
    void tearDown() {
        a = null;
    }

    @Test
    void map() {
        List<Integer> list = Collections.map((num) -> num * 2, a);
        assertEquals(List.of(2, 4, 6, 8, 10), list);
    }

    @Test
    void filter() {
        List<Integer> list = Collections.filter((num) -> num > 3, a);
        assertEquals(List.of(4, 5), list);
    }

    @Test
    void takeWhile() {
        List<Integer> list = Collections.takeWhile((num) -> num > 2, a);
        assertEquals(List.of(5, 4, 3), list);
    }

    @Test
    void takeUnless() {
        List<Integer> list = Collections.takeUnless((num) -> num < 2, a);
        assertEquals(List.of(5, 4, 3, 2), list);
    }

    @Test
    void foldl() {
        Integer result = Collections.foldl((a, b) ->  (int)(Math.pow(a, 2)/b), 2, a);
        assertEquals(2420, result);
    }

    @Test
    void foldr() {
        Integer result = Collections.foldr((a, b) -> (int)(Math.pow(a, 2)/b), 2, a);
        assertEquals(0, result);
    }


}