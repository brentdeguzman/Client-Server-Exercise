package com._98labs.exercises.sockets.server;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ServerPoemLineEager {
    private static final Logger poemLineLogger = LogManager.getLogger(ServerPoemLineEager.class);
    private static String filePath = ServerPoemLineEager.class.getClassLoader().getResource("Poem.txt").getPath();
    //getPath() -> convert URL object to string
    //getResources() -> returns a URL object representing the location of the resource
    //getClassLoader() -> loads classes from the target folder
    private static BufferedReader clientInput;
    private static int lineNumber;
    private static int defaultValue;
    private static List<String> poemLines = new ArrayList<>();//dynamic array for storing the elements

    public static void loadPoem() {
        poemLines = new ArrayList<>();
        try (BufferedReader fileReader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = fileReader.readLine()) != null) {
                poemLines.add(line);
            }//line is successfully read and is not null, it is added to list
            poemLineLogger.info("Poem file loaded into memory.");
        } catch (IOException e) {
            poemLineLogger.error("Error reading poem file: " + e.getMessage());
        }
    }
    static {loadPoem();
        System.out.println(poemLines);}//initialize the loadPoem into memory

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

    public static String readPoem(int lineNumber) {
        if (lineNumber < 1) {
            poemLineLogger.warn("Poem line does not exist.");
            return null;
        } else if (lineNumber > poemLines.size()) {//number of element from list
            poemLineLogger.warn("Requested poem line exceed.");
            return null;
        }
        String line = poemLines.get(lineNumber - 1);//adjusted for zero-based index array
        poemLineLogger.info(line);
        return line;
    }
}
