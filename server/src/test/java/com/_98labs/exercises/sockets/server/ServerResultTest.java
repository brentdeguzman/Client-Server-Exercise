package com._98labs.exercises.sockets.server;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class ServerResultTest {

    @Test
    void testSuffixForElevenToThirteen() {
        // Test when lineNumber is between 11 and 13
        assertEquals("th", ServerResult.getSuffix(11));
        assertEquals("th", ServerResult.getSuffix(12));
        assertEquals("th", ServerResult.getSuffix(13));
    }

    @Test
    void testSuffixForNumberEndingIn1() {
        // Test when lineNumber ends in 1 but is not 11
        assertEquals("st", ServerResult.getSuffix(1));
        assertEquals("st", ServerResult.getSuffix(21));
        assertEquals("st", ServerResult.getSuffix(31));
    }

    @Test
    void testSuffixForNumberEndingIn2() {
        // Test when lineNumber ends in 2 but is not 12
        assertEquals("nd", ServerResult.getSuffix(2));
        assertEquals("nd", ServerResult.getSuffix(22));
        assertEquals("nd", ServerResult.getSuffix(102));
    }

    @Test
    void testSuffixForNumberEndingIn3() {
        // Test when lineNumber ends in 3 but is not 13
        assertEquals("rd", ServerResult.getSuffix(3));
        assertEquals("rd", ServerResult.getSuffix(23));
        assertEquals("rd", ServerResult.getSuffix(103));
    }

    @Test
    void testSuffixForOtherNumbers() {
        // Test when lineNumber ends in a number other than 1, 2, or 3
        assertEquals("th", ServerResult.getSuffix(4));
        assertEquals("th", ServerResult.getSuffix(5));
        assertEquals("th", ServerResult.getSuffix(6));
        assertEquals("th", ServerResult.getSuffix(7));
        assertEquals("th", ServerResult.getSuffix(8));
        assertEquals("th", ServerResult.getSuffix(9));
        assertEquals("th", ServerResult.getSuffix(10));
    }
}
