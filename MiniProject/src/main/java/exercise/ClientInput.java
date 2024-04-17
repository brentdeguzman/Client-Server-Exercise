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
        System.out.println("Select line number between 1 and 35 : ");
        int lineNumber = Integer.parseInt(userInput.readLine());
        if (lineNumber > 35 || lineNumber == 0) //if input is greater than 35 or is equal to 0 throw exception
            throw new IllegalArgumentException(lineNumber +" is invalid number.Please only select between 1 and 35");
        else clientOutput.println(lineNumber);
    }
}
