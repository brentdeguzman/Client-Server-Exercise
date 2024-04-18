package exercise;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientInput {
    public static void userInputToServer(Socket socket) throws IOException {
        BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));//reads input from terminal
        PrintWriter clientOutput = new PrintWriter(socket.getOutputStream(), true);
        //allows client to send data to the server
        System.out.println("Type a positive number or Type -1 to terminate: ");
        int lineNumber = Integer.parseInt(userInput.readLine());
        if (lineNumber == -1) {
            System.out.println("Connection will be terminated.");
            socket.close();
        }
        else clientOutput.println(lineNumber);
    }
}
