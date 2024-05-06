package com._98labs.exercises.sockets.server;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.HashMap;

import java.io.*;
import java.util.Map;

public class ServerPoemLineLazy {
    private static final Logger poemLineLogger = LogManager.getLogger(ServerPoemLineLazy.class);
    private static String filePath = ServerPoemLineLazy.class.getClassLoader().getResource("Poem.txt").getPath();
    //getPath() -> convert URL object to string
    //getResources() -> returns a URL object representing the location of the resource
    //getClassLoader() -> loads classes from the target folder
    private static BufferedReader clientInput;
    private static int lineNumber;
    private static int defaultValue;
    private static Map<Integer, String> poemLines = new HashMap<>();

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
    public static String lazyGetPoemLine(int lineNumber) {
        try (BufferedReader fileReader = new BufferedReader(new FileReader(filePath))) {
            int currentLineNumber = 0;
            String line;
            while ((line = fileReader.readLine()) != null) {
                currentLineNumber++;
                if (currentLineNumber == lineNumber) {
                    poemLines.put(lineNumber,line);
                    poemLineLogger.info(poemLines);
                    return line;
                }
            }
            poemLineLogger.warn("Line " + lineNumber + " not found.");
        } catch (IOException e) {
            poemLineLogger.error("Poem file error: " + e.getMessage());
        }
        return null; //lineNumber exceeds
    }

}
