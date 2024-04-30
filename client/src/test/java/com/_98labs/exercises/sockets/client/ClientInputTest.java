package com._98labs.exercises.sockets.client;

import static com._98labs.exercises.sockets.client.ClientInput.validateUserInput;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ClientInputTest {
    private static Properties properties;
    @BeforeEach
    void setUp() throws IOException {// Load properties file
        properties = new Properties();
        String propertiesFilePath = ClientInputTest.class.getClassLoader().getResource("clientConfig.properties").getPath();
        try (FileInputStream readConfig = new FileInputStream(propertiesFilePath)) {
            properties.load(readConfig);
        }
    }
    @Test
    void testTerminationInput() throws Exception{
        int result = validateUserInput(properties.getProperty("terminateValue"));
        assertEquals(-1, result);
    }

    @Test
    void testDecimalInput() throws Exception{
        int result = validateUserInput(properties.getProperty("invalidDecimalInput"));
        assertEquals(0, result);
    }

    @Test
    void testValidInput() throws Exception{
        int result = validateUserInput(properties.getProperty("validInput"));
        assertEquals(5, result);
    }
    @Test
    void testValidIntegerInputLarge() throws Exception{
        int result = validateUserInput(properties.getProperty("validInputExceeds"));
        assertEquals(987654321, result);
    }
    @Test
    void testInvalidStringInput() throws Exception{
        int result = validateUserInput(properties.getProperty("invalidStringInput"));
        assertEquals(0, result);
    }
}
