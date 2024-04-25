package com._98labs.exercises.sockets.client;

import static com._98labs.exercises.sockets.client.ClientInput.validateUserInput;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class ClientInputTest {

    @Test
    void testTerminationInput() {
        int result = validateUserInput("-1");
        assertEquals(-1, result);
    }

    @Test
    void testDecimalInput() {
        int result = validateUserInput("3.14");
        assertEquals(0, result);
    }

    @Test
    void testValidIntegerInput() {
        int result = validateUserInput("10");
        assertEquals(10, result);
    }
    @Test
    void testValidIntegerInputLarge() {
        int result = validateUserInput("987654321");
        assertEquals(987654321, result);
    }
    @Test
    void testInvalidInput() {
        int result = validateUserInput("abc");
        assertEquals(0, result);
    }
}
