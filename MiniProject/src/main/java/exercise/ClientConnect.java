package exercise;

import java.io.IOException;
import java.net.Socket;

public class ClientConnect {
    public static Socket connectToServer() throws IOException {
        System.out.println("Client started... \n");
        Socket socket = new Socket("localhost", 9807);
        return socket;
        //establish connection to server running on the same machine (localhost) and listening on port 9807
    }
}

