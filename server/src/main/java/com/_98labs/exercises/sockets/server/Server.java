package com._98labs.exercises.sockets.server;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static Socket clientSocket;

    public static void main(String[] args){
        try {
            ServerConnection.establishConnection();
            while (true) {
                int lineNumber = ServerPoemLine.handleLineNumberFromClient();
                if(lineNumber == -1)
                    break;
                ServerResult.sendResult(lineNumber);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
