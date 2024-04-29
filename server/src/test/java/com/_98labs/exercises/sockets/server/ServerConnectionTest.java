package com._98labs.exercises.sockets.server;

import org.junit.jupiter.api.Test;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class ServerConnectionTest {

    @Test
    public void testEstablishConnection() throws Exception {
        //call the method to be tested
        ServerConnection.establishConnection();
//        Server.clientSocket = null;
        //verify the server established the connection
        assertNotNull(Server.clientSocket, "Test failed, returns null");
        assertTrue(Server.clientSocket.isConnected());//boolean state of connection
    }
}