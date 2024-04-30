package com._98labs.exercises.sockets.client;

import java.io.IOException;
import java.net.Socket;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ClientConnect {
    private static final Logger clientLogger = LogManager.getLogger(ClientConnect.class);
    private static int port;
    private static String localhost;
    public static Socket connectToServer() throws IOException {
        port = Integer.parseInt(LoadProperties.portProperty());
        localhost = LoadProperties.hostProperty();
        clientLogger.info("Client started... \n");
        Socket socket = new Socket(localhost, port);
        return socket;
        //establish connection to server running on the same machine (localhost) and listening on port 9807
    }
}

