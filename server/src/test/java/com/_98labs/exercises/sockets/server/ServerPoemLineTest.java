package com._98labs.exercises.sockets.server;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import static com._98labs.exercises.sockets.server.ServerPoemLine.readPoem;
import static com._98labs.exercises.sockets.server.ServerPoemLine.validateInputFromClient;
import static org.junit.jupiter.api.Assertions.*;

class ServerValidationTest {

    @Test
    void testValidInput() {
        int result = validateInputFromClient("5");
        assertEquals(5, result);
    }

    @Test
    void testValidInputButExceeds() {
        int result = validateInputFromClient("987654321");
        assertEquals(987654321, result);
    }

    @Test
    void testInvalidLineNumberZero() {//test the if statement: is less than 1
        int result = validateInputFromClient("0");
        assertEquals(0, result);
    }

    @Test
    void testInvalidLineNumber() {//test the if statement: is less than 1
        int result = validateInputFromClient("-2");
        assertEquals(0, result);
    }


    @Test
    void testInvalidStringInput() {//test the catch: NumberFormatException
        int result = validateInputFromClient("xyz");
        assertEquals(-1, result);
    }

    @Test
    void testInvalidDecimalInput() {
        int result = validateInputFromClient("3.1416");
        assertEquals(-1, result);
    }
}
class ServerReadPoemTest {

//    private static final String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\Haiku.txt";
    private static final String filePath = ServerPoemLine.class.getClassLoader().getResource("Haiku.txt").getPath();
//    private static final String emptyFile = System.getProperty("user.dir") + "\\src\\test\\resources\\Empty.txt";
    private static final String emptyFile = ServerPoemLine.class.getClassLoader().getResource("Empty.txt").getPath();
    private ServerPoemLine poemReader;

    @BeforeEach
    void setUp() {
        // Initialize the PoemReader instance
        poemReader = new ServerPoemLine();
        poemReader.setFilePath(filePath);
    }
    @Test
    void testReadPoemValidLine1() throws IOException {
        String expectedLine = "In pale moonlight";
        String result = poemReader.readPoem(1);
        assertEquals(expectedLine.trim(), result);
    }
    @Test
    void testReadPoemValidLine2() throws IOException {
        String expectedLine = "the wisteria’s scent";
        String result = poemReader.readPoem(2);
        assertEquals(expectedLine.trim(), result);
    }
    @Test
    void testReadPoemValidLine3() throws IOException {
        String expectedLine = "comes from far away.";
        String result = poemReader.readPoem(3);
        assertEquals(expectedLine.trim(), result);
    }

    @Test
    void testReadPoemInvalidLine() throws IOException {
        // Test that requesting a non-existent line returns null
        String result = poemReader.readPoem(100);
        assertNull(result);
    }

    @Test
    void testReadPoemInvalidLineNegative() throws IOException {
        // Test that requesting a non-existent line returns null
        String result = poemReader.readPoem(-2);
        assertNull(result);
    }

    @Test
    void testReadPoemEmptyFile() throws IOException {
        poemReader.setFilePath(emptyFile);
        String result = poemReader.readPoem(1);
        assertNull(result);// Expect null since the file is empty
    }

//    @Test
//    void testReadPoemEmptyFile() throws IOException {
//        // Test with an empty file
//        poemReader.setFilePath(filePath);
//
//        int lineNumber = 1;
//        String result = poemReader.readPoem(lineNumber);
//        assertNull(result);
//    }

//    @Test
//    public void testReadPoemLine2Exists() throws IOException {
//        String expectedLine = "Are losing theirs and blaming it on you,";
//        assertEquals(expectedLine.trim(), readPoem(2).trim());
//    }
//
//    @Test
//    public void testReadPoemLine34Exists() throws IOException {
//        String expectedLine = "Yours is the Earth and everything that’s in it,";
//        assertEquals(expectedLine.trim(), readPoem(34).trim());
//    }
//    @Test
//    public void testReadPoemDoesNotExist() throws IOException {
//        String expectedLine = null;
//        String actualLine = readPoem(37);
//        assertEquals(expectedLine, actualLine);
//    }
}

