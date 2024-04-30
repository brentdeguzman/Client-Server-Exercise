package com._98labs.exercises.sockets.server;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.util.Properties;

public class ServerPoemLine {
    private static final Logger poemLineLogger = LogManager.getLogger(ServerPoemLine.class);
    private static String filePath = ServerPoemLine.class.getClassLoader().getResource("Poem.txt").getPath();
    //getPath() -> convert URL object to string
    //getResources() -> returns a URL object representing the location of the resource
    //getClassLoader() -> loads classes from the target folder
    private static BufferedReader clientInput;
    private static int lineNumber;
    private static int defaultValue;
    private static String poemLine;

    public static int handleLineNumberFromClient() throws IOException {
        clientInput = new BufferedReader(new InputStreamReader(Server.clientSocket.getInputStream()));
        String input = clientInput.readLine();
        lineNumber = validateInputFromClient(input);
        return lineNumber;
    }

    public static int validateInputFromClient(String input) throws IOException{
        try {
            int lineNumber = Integer.parseInt(input);
            if (lineNumber < 1) {
                poemLineLogger.warn("Invalid line number");
                return defaultValue; //Invalid line number (less than 1)
            }
            return lineNumber;
        } catch (NumberFormatException e) {
            int terminateValue = Integer.parseInt(LoadProperties.terminateProperty());
            poemLineLogger.warn("The input is invalid.");
            return terminateValue; //Invalid input (not an integer)
        }
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
    public static String readPoem(int lineNumber) throws IOException {
        int lineCounter = 0;
        try (BufferedReader fileReader = new BufferedReader(new FileReader(filePath))) {
            while ((poemLine = fileReader.readLine()) != null) {
                lineCounter++;
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
