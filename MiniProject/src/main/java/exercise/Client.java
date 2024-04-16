package exercise;

import java.io.IOException;
import java.net.Socket;

public class Client {
    public static void main(String[] args){

        try {
            Socket socket = ClientConnect.connectToServer();
            ClientInput.userInputToServer(socket);
            ClientResult.clientResult(socket);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}

