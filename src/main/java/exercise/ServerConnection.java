package exercise;

import java.io.IOException;
import java.net.ServerSocket;

public class ServerConnection {

    public static void establishConnection() throws IOException {
        Server.logger.info("Waiting for client to connect...\n");
        Server.serverSocket = new ServerSocket(9807);
        Server.clientSocket = Server.serverSocket.accept();
        Server.logger.info("Connection between server and client established\n");
    }
}

