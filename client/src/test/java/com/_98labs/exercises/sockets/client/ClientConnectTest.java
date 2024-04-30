package com._98labs.exercises.sockets.client;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.IOException;
import java.net.Socket;
import org.junit.jupiter.api.Test;

public class ClientConnectTest {

    @Test
    void testConnectToServer() throws Exception {
        //call the method to be tested
        Socket socket = ClientConnect.connectToServer();
//        Socket socket = null; //test to fail
        //verify socket if not null
        assertNotNull(socket, "Test failed, returns null");
    }
}