package org.example;

public class Assertions {
    public static void assertEquals(Object expected, Object actual, String message) throws AssertionException{
        if (!expected.equals(actual)) {
            throw new AssertionException(message + ". Expected " + expected + ", but was " + actual);
        }
    }

    public static void assertNotEquals(Object expected, Object actual, String message) throws AssertionException {
        if (expected.equals(actual)) {
            throw new AssertionException(message + ". Expected not equals to " + expected + ", but was.");
        }
    }
}
