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
    public static void userInputToServer(Socket socket) throws IOException {
        BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));//reads input from terminal
        PrintWriter clientOutput = new PrintWriter(socket.getOutputStream(), true);
        //allows client to send data to the server

        clientInputLogger.info("Type a  number or Type -1 to terminate: ");
        String input = userInput.readLine();
        if (input == "-1") {
            clientInputLogger.warn("Connection will be terminated.");
            socket.close();
        }
        String integerPattern = "-?\\d+"; // Matches integers (positive or negative)
        //-? = minus sign on integer is optional,\\d = shorthand for a digit (0-9),+ = at least one digit
        String decimalPattern = "-?\\d+\\.\\d+"; // Matches decimals (positive or negative)
        clientInputLogger.info("User input: "+ input);
        //if input matches the pattern
        if (input.matches(decimalPattern)) {
            clientOutput.println(0);
        } else if (input.matches(integerPattern)) {
            clientOutput.println(input);
        } else {
            clientOutput.println(0);
        }
    }
}

