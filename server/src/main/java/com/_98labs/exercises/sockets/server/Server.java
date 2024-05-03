package com._98labs.exercises.sockets.server;

import java.io.*;
import java.net.Socket;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Server {
    private static final Logger serverLogger = LogManager.getLogger(Server.class);
    public static Socket clientSocket;

    public static void main(String[] args){
        try {
            ServerConnection.establishConnection();
            while (true) {
                int lineNumber = ServerPoemLineEager.handleLineNumberFromClient();
                if(lineNumber == -1){
                    serverLogger.info("Connection was terminated.");
                    break;
                }
                ServerResult.sendResult(lineNumber);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
