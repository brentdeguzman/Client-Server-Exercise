package com._98labs.exercises.sockets.client;

import static org.junit.jupiter.api.Assertions.*;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ClientInputTest {

    private ServerSocket serverSocket;
    private Socket clientSocket;

    @BeforeEach //do this before each @Test
    void setUp() throws IOException {
        //establish server&client connection
        serverSocket = new ServerSocket(9807);
        clientSocket = new Socket("localhost", 9807);
    }

    @AfterEach //do this after each @Test
    void closeConnection() throws IOException {
        // Close the client and server sockets
        clientSocket.close();
        serverSocket.close();

    }

    @Test
    void testUserInputToServer() throws IOException {
        BufferedReader serverReader = new BufferedReader(new InputStreamReader(serverSocket.accept().getInputStream()));
        InputStream simulateInput = System.in;// Simulate user input
        try {//integer
            InputStream inputStream = new ByteArrayInputStream("31".getBytes());
            //stream will simulate System.in, allows predefined input into test
            System.setIn(inputStream);
            ClientInput.userInputToServer(clientSocket);
            //reads the simulated user input,sends data to the server
            String serverOutput = serverReader.readLine();
            //captures the data sent by the client to the server and allows the test to verify
            assertEquals("31", serverOutput);
        } finally {
            System.setIn(simulateInput);
        }
        try {//decimal input test
            InputStream inputStream = new ByteArrayInputStream("1.134".getBytes());
            System.setIn(inputStream);
            ClientInput.userInputToServer(clientSocket);
            String serverOutput = serverReader.readLine();
            assertEquals("0", serverOutput);
        } finally {
            System.setIn(simulateInput);
        }
        try {//string input test
            InputStream inputStream = new ByteArrayInputStream("a".getBytes());
            System.setIn(inputStream);
            ClientInput.userInputToServer(clientSocket);
            String serverOutput = serverReader.readLine();
            assertEquals("0", serverOutput);
        } finally {
            System.setIn(simulateInput);
        }
    }

    @Test
    void testTerminationCondition() throws IOException {// -1 input test
        InputStream inputStream = new ByteArrayInputStream("-1".getBytes());
        System.setIn(inputStream);
        ClientInput.userInputToServer(clientSocket);//call the method under test
        //verify that the socket is closed
        assertTrue(clientSocket.isClosed(), "The client socket should be closed on termination input.");
    }
}
