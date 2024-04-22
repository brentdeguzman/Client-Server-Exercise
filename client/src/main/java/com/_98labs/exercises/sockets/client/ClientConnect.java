package com._98labs.exercises.sockets.client;

import java.io.IOException;
import java.net.Socket;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ClientConnect {
    private static final Logger clientLogger = LogManager.getLogger(ClientConnect.class);
    public static Socket connectToServer() throws IOException {
        clientLogger.info("Client started... \n");
        Socket socket = new Socket("localhost", 9807);
        return socket;
        //establish connection to server running on the same machine (localhost) and listening on port 9807
    }
}

