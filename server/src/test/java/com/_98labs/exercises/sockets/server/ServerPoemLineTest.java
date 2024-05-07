package com._98labs.exercises.sockets.server;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import static com._98labs.exercises.sockets.server.HandleClientInput.validateInputFromClient;
import static org.junit.jupiter.api.Assertions.*;

class InputValidationTest{
    private Properties properties;
    @BeforeEach
    void setUp() throws IOException {// Load properties file
        properties = new Properties();
        String propertiesFilePath = InputValidationTest.class.getClassLoader().getResource("serverConfig.properties").getPath();
        try (FileInputStream readConfig = new FileInputStream(propertiesFilePath)) {
            properties.load(readConfig);
        }
    }
    @Test
    void testValidInput()  throws IOException{
        int result = validateInputFromClient(properties.getProperty("validInput"));
        assertEquals(5, result);
    }

    @Test
    void testValidInputButExceeds() throws IOException{
        int result = validateInputFromClient(properties.getProperty("validInputExceeds"));
        assertEquals(987654321, result);
    }

    @Test
    void testInvalidLineNumberZero() throws IOException{//test the if statement: is less than 1
        int result = validateInputFromClient(properties.getProperty("invalidInputZero"));
        assertEquals(0, result);
    }

    @Test
    void testInvalidLineNumber() throws IOException{//test the if statement: is less than 1
        int result = validateInputFromClient(properties.getProperty("invalidInputNegative"));
        assertEquals(0, result);
    }

    @Test
    void testInvalidStringInput() throws IOException{//test the catch: NumberFormatException
        int result = validateInputFromClient(properties.getProperty("invalidStringInput"));
        assertEquals(-1, result);
    }

    @Test
    void testInvalidDecimalInput() throws IOException{
        int result = validateInputFromClient(properties.getProperty("invalidDecimalInput"));
        assertEquals(-1, result);
    }
}
class EagerReadPoemTest {
    private ServerPoemEager poemReader;

    @BeforeEach
    void setUp() {// Initialize the PoemReader instance
        poemReader = new ServerPoemEager();
    }
    @Test
    void testReadPoemValidLine1() throws IOException {
        String expectedLine = "If you can keep your head when all about you";
        String result = poemReader.readPoem(1);
        assertEquals(expectedLine.trim(), result.trim());
    }
    @Test
    void testReadPoemValidLine2() throws IOException {
        String expectedLine = "Are losing theirs and blaming it on you,";
        String result = poemReader.readPoem(2);
        assertEquals(expectedLine.trim(), result.trim());
    }
    @Test
    void testReadPoemValidLine3() throws IOException {
        String expectedLine = "If you can trust yourself when all men doubt you,";
        String result = poemReader.readPoem(3);
        assertEquals(expectedLine.trim(), result.trim());
    }

    @Test
    void testReadPoemInvalidLine() throws IOException {
        String result = poemReader.readPoem(100);
        assertNull(result);
    }

    @Test
    void testReadPoemInvalidLineNegative() throws IOException {
        String result = poemReader.readPoem(-2);
        assertNull(result);
    }
}
class LazyReadPoemTest {
    private ServerPoemLazy poemReader;

    @BeforeEach
    void setUp() {// Initialize the PoemReader instance
        poemReader = new ServerPoemLazy();
    }

    @Test
    void testReadPoemValidLine1() throws IOException {
        String expectedLine = "If you can keep your head when all about you";
        String result = poemReader.getPoemLine(1);
        assertEquals(expectedLine.trim(), result.trim());
    }

    @Test
    void testReadPoemValidLine2() throws IOException {
        String expectedLine = "Are losing theirs and blaming it on you,";
        String result = poemReader.getPoemLine(2);
        assertEquals(expectedLine.trim(), result.trim());
    }

    @Test
    void testReadPoemValidLine3() throws IOException {
        String expectedLine = "If you can trust yourself when all men doubt you,";
        String result = poemReader.getPoemLine(3);
        assertEquals(expectedLine.trim(), result.trim());
    }

    @Test
    void testReadPoemInvalidLine() throws IOException {
        String result = poemReader.getPoemLine(100);
        assertNull(result);
    }

    @Test
    void testReadPoemInvalidLineNegative() throws IOException {
        String result = poemReader.getPoemLine(-2);
        assertNull(result);
    }
}
