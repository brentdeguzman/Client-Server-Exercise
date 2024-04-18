package exercise;

import java.io.IOException;
import java.net.Socket;

public class Client {
    public static void main(String[] args){

        try {
            Socket socket = ClientConnect.connectToServer();
            while (true) {
                ClientInput.userInputToServer(socket);
                ClientResult.displayResult(socket);
            }
        } catch (IOException e) {
            System.out.println("Connection ended: " + e.getMessage());
        }

    }

}

