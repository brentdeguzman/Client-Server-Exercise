package com._98labs.exercises.sockets.server;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.net.ServerSocket;
import java.util.Properties;

public class ServerConnection {
    private static ServerSocket serverSocket;
    private static final Logger connectionLogger = LogManager.getLogger(ServerConnection.class);
    private static int port;
    private static int loadPort() throws Exception {
        String filePath = ServerConnection.class.getClassLoader().getResource("serverConfig.properties").getPath();
        FileInputStream readFile = new FileInputStream(filePath);
        Properties prop = new Properties();
        prop.load(readFile);
        port = Integer.parseInt(prop.getProperty("port"));
        return port;
    }
    public static void establishConnection() throws Exception {
        connectionLogger.info("Waiting for client to connect...\n");
        serverSocket = new ServerSocket(loadPort());
        Server.clientSocket = serverSocket.accept();
        connectionLogger.info("Connection between server and client established\n");
    }
}