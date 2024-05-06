package com._98labs.exercises.sockets.server;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class HandleClientInput {
    private static int lineNumber;
    private static int defaultValue;
    private static BufferedReader clientInput;
    private static final Logger handleClientInputLogger = LogManager.getLogger(HandleClientInput.class);
    public static int handleLineNumberFromClient() throws IOException {
        clientInput = new BufferedReader(new InputStreamReader(Server.clientSocket.getInputStream()));
        String input = clientInput.readLine();
        lineNumber = validateInputFromClient(input);
        return lineNumber;
    }

    public static int validateInputFromClient(String input) throws IOException {
        try {
            int lineNumber = Integer.parseInt(input);
            if (lineNumber < 1) {
                handleClientInputLogger.warn("Invalid line number");
                return defaultValue; //Invalid line number (less than 1)
            }
            return lineNumber;
        } catch (NumberFormatException e) {
            int terminateValue = Integer.parseInt(LoadProperties.terminateProperty());
            handleClientInputLogger.warn("The input is invalid.");
            return terminateValue; //Invalid input (not an integer)
        }
    }
}
