package com._98labs.exercises.sockets.server;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.HashMap;

import java.io.*;
import java.util.Map;

public class ServerPoemLazy {
    private static String filePath = ServerPoemLazy.class.getClassLoader().getResource("Poem.txt").getPath();
    //getPath() -> convert URL object to string
    //getResources() -> returns a URL object representing the location of the resource
    //getClassLoader() -> loads classes from the target folder
    private static Map<Integer, String> poemLines = new HashMap<>();
    private static final Logger poemLineLogger = LogManager.getLogger(ServerPoemLazy.class);

    public static String getPoemLine(int lineNumber) {
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
