package com._98labs.exercises.sockets.server;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ServerPoemLine {
    private static final Logger poemLineLogger = LogManager.getLogger(ServerPoemLine.class);
    private static String filePath = ServerPoemLine.class.getClassLoader().getResource("Poem.txt").getPath();
    private static BufferedReader clientInput;
    private static int lineNumber;
    private static String poemLine;

    public static int handleLineNumberFromClient() throws IOException {
        clientInput = new BufferedReader(new InputStreamReader(Server.clientSocket.getInputStream()));
        String input = clientInput.readLine();
        lineNumber = validateInputFromClient(input);
        return lineNumber;
    }

    public static int validateInputFromClient(String input) {
        try {
            int lineNumber = Integer.parseInt(input);
            if (lineNumber < 1) {
                poemLineLogger.warn("Invalid line number");
                return 0; //Invalid line number (less than 1)
            }
            return lineNumber;
        } catch (NumberFormatException e) {
            poemLineLogger.warn("The input is invalid.");
            return -1; //Invalid input (not an integer)
        }
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public static String readPoem(int lineNumber) throws IOException {
        int lineCounter = 0;
        try (BufferedReader fileReader = new BufferedReader(new FileReader(filePath))) {
            while ((poemLine = fileReader.readLine()) != null) {
                lineCounter += 1;
                if (lineNumber == lineCounter) {
                    poemLineLogger.info(poemLine);
                    return poemLine;
                }
            }
            poemLineLogger.warn("Poem line does not exist.");
            return poemLine;
        }
    }
}
