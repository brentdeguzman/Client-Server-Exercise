package com._98labs.exercises.sockets.server;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.net.ServerSocket;

public class ServerConnection {
    private static ServerSocket serverSocket;
    private static final Logger connectionLogger = LogManager.getLogger(ServerConnection.class);
    private static int port;

    public static void establishConnection() throws IOException {
        port = Integer.parseInt(LoadProperties.portProperty());
        connectionLogger.info("Waiting for client to connect...\n");
        serverSocket = new ServerSocket(port);
        Server.clientSocket = serverSocket.accept();
        connectionLogger.info("Connection between server and client established\n");
    }
}