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

    @Test
    public void testReadPoemLine2Exists() throws IOException {
        String expectedLine = "Are losing theirs and blaming it on you,";
        assertEquals(expectedLine.trim(), readPoem(2).trim());
    }

    @Test
    public void testReadPoemLine34Exists() throws IOException {
        String expectedLine = "Yours is the Earth and everything that’s in it,";
        assertEquals(expectedLine.trim(), readPoem(34).trim());
    }
    @Test
    public void testReadPoemDoesNotExist() throws IOException {
        String expectedLine = null;
        String actualLine = readPoem(37);
        assertEquals(expectedLine, actualLine);
    }
}
//    private static final String testFilePath = "C:\\Users\\brent\\OneDrive\\Documents\\GitHub\\Client-Server-Exercise\\server\\src\\test\\resources\\Haiku.txt";
//        @BeforeEach
//    void testReadPoem() throws Exception {
//        BufferedReader readFile = new BufferedReader(new FileReader(testFilePath));
////        readPoem();
//        String test;
//        while((test  = readFile.readLine()) != null){
//            System.out.println(test);
//        }
//    }
