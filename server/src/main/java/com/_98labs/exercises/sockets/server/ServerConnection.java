package com._98labs.exercises.sockets.server;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.net.ServerSocket;

public class ServerConnection {
    private static final Logger connectionLogger = LogManager.getLogger(ServerConnection.class);
    public static void establishConnection() throws IOException {
        connectionLogger.info("Waiting for client to connect...\n");
        Server.serverSocket = new ServerSocket(9807);
        Server.clientSocket = Server.serverSocket.accept();
        connectionLogger.info("Connection between server and client established\n");
    }
}

