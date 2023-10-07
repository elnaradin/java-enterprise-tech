package com.skillbox;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Function1Tests {

    @Test
    void apply() {
        Function1<String, String> f = (name) -> "Hi, " + name;
        String result = f.apply("Maria");
        assertEquals("Hi, Maria", result);
    }

    @Test
    void compose() {
        Function1<String, String> f = (name) -> "Hi, " + name;
        Function1<String, String> compose = f.compose((greeting) -> greeting + "!");
        assertEquals("Hi, Maria!", compose.apply("Maria"));
    }
}