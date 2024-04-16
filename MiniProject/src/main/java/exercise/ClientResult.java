package exercise;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ClientResult {
    public static void clientResult(Socket socket) throws IOException {
        BufferedReader serverInput = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        //allows client to receive data from the server, reads input
        System.out.println("\nFrom the poem: If— by Rudyard Kipling");
        System.out.println(serverInput.readLine());
    }
}
