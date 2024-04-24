package com._98labs.exercises.sockets.server;

import org.junit.jupiter.api.Test;

import static com._98labs.exercises.sockets.server.ServerPoemLine.validateInputFromClient;
import static org.junit.jupiter.api.Assertions.*;

class ServerValidationTest {

    @Test
    public void testValidInput() {
        int result = validateInputFromClient("5");
        assertEquals(5, result);
    }

    @Test
    public void testInvalidLineNumber() {//test the if statement: if less than 1
        int result = validateInputFromClient("-2");
        assertEquals(0, result);
    }

    @Test
    public void testInvalidStringInput() {//test the catch: NumberFormatException
        int result = validateInputFromClient("xyz");
        assertEquals(-1, result);
    }
    @Test
    public void testInvalidDecimalInput() {
        int result = validateInputFromClient("3.14");
        assertEquals(-1, result);
    }

}