package com._98labs.exercises.sockets.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ClientInput {
    private static final Logger clientInputLogger = LogManager.getLogger(ClientInput.class);
    private static final String integerPattern = "-?\\d+"; // Matches integers (positive or negative)
    //-? = minus sign on integer is optional,\\d = shorthand for a digit (0-9),+ = at least one digit
    private static final String decimalPattern = "-?\\d+\\.\\d+"; // Matches decimals (positive or negative)
    public static void userInputToServer(Socket socket) throws IOException {
        BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));//reads input from terminal
        PrintWriter clientOutput = new PrintWriter(socket.getOutputStream(), true);
        //allows client to send data to the server

        clientInputLogger.info("Type a  number or Type -1 to terminate: ");
        String input = userInput.readLine();
        clientInputLogger.info("User input: "+ input);
        int response = validateUserInput(input);

        if (response == -1) {
            clientInputLogger.warn("Connection will be terminated.");
            socket.close();
        } else {
            clientOutput.println(response);
        }
    }
    private static int validateUserInput(String input) {
        if ("-1".equals(input)) {
            return -1; //termination
        } else if (input.matches(decimalPattern)) {
            return 0; //decimal input not valid; return default value (0)
        } else if (input.matches(integerPattern)) {
            return Integer.parseInt(input); //valid input
        } else {
            return 0; //invalid input; return default value (0)
        }
    }
}

