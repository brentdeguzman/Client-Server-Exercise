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
    private static String integerPattern; // Matches integers (positive or negative)
    private static String decimalPattern; // Matches decimals (positive or negative)
    private static int defaultValue;//static, it will be initialized to zero
    private static int terminateValue;
    public static void userInputToServer(Socket socket) throws IOException {
        BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));//reads input from terminal
        PrintWriter clientOutput = new PrintWriter(socket.getOutputStream(), true);
        //allows client to send data to the server

        clientInputLogger.info("Type a  number or Type -1 to terminate: ");
        String input = userInput.readLine();
        int response = validateUserInput(input);

        if (response == terminateValue) {
            clientInputLogger.warn("Connection will be terminated.");
            socket.close();
        } else {
            clientOutput.println(response);
        }
    }
    public static int validateUserInput(String input) throws IOException{
        terminateValue = Integer.parseInt(LoadProperties.terminateProperty());
        decimalPattern = LoadProperties.decimalProperty();
        integerPattern = LoadProperties.integerProperty();
        if ("-1".equals(input)) {
            clientInputLogger.warn("Input is code for termination: " + input);
            return terminateValue; //termination
        } else if (input.matches(decimalPattern)) {
            clientInputLogger.warn("Input is invalid: " + input);
            return defaultValue; //decimal input not valid; return default value (0)
        } else if (input.matches(integerPattern)) {
            return Integer.parseInt(input); //valid input
        } else {
            clientInputLogger.warn("Input is invalid: " + input);
            return defaultValue; //invalid input; return default value (0)
        }
    }
}

