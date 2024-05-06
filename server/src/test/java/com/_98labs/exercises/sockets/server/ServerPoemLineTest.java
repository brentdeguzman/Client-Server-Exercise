package com._98labs.exercises.sockets.server;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import static com._98labs.exercises.sockets.server.HandleClientInput.validateInputFromClient;
import static org.junit.jupiter.api.Assertions.*;

class ServerValidationTest{
    private Properties properties;
    @BeforeEach
    void setUp() throws IOException {// Load properties file
        properties = new Properties();
        String propertiesFilePath = ServerValidationTest.class.getClassLoader().getResource("serverConfig.properties").getPath();
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
class ServerReadPoemTest {
    private static final String filePath = ServerPoemLazy.class.getClassLoader().getResource("Haiku.txt").getPath();
    private static final String emptyFile = ServerPoemLazy.class.getClassLoader().getResource("Empty.txt").getPath();
    private ServerPoemEager poemReader;

    @BeforeEach
    void setUp() {// Initialize the PoemReader instance
        poemReader = new ServerPoemEager();
//        poemReader.setFilePath(filePath);
//        poemReader.loadPoem();
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

//    @Test
//    void testReadPoemEmptyFile() throws IOException {
//        poemReader.setFilePath(emptyFile);
//        String result = poemReader.readPoem(1);
//        assertNull(result);// Expect null since the file is empty
//    }
}

