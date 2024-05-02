package com._98labs.exercises.sockets.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ClientResult {
    private static final Logger clientResultLogger = LogManager.getLogger(ClientResult.class);
    public static void displayResult(Socket socket) throws IOException {
        BufferedReader serverInput = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        //allows client to receive data from the server, reads input
        clientResultLogger.info(serverInput.readLine());
    }
}
