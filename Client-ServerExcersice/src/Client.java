import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
    public static void main(String[] args){

        try {
            System.out.println("Client started...");
            System.out.println(" ");
            Socket socket = new Socket("localhost", 9807);
            //establish connection to server running on the same machine (localhost) and listening on port 9805
            BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));//reads input from terminal
            System.out.println("Select line number between 1 and 35 : ");
            int lineNumber = Integer.parseInt(userInput.readLine());
            PrintWriter clientOutput = new PrintWriter(socket.getOutputStream(), true);
            //allows client to send data to the server
            clientOutput.println(lineNumber);
            BufferedReader serverInput = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            //allows client to receive data from the server, reads input
            System.out.println(" ");
            System.out.println("From the poem: If— by Rudyard Kipling");
            System.out.println(serverInput.readLine());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
