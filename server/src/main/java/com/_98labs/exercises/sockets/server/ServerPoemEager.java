package com._98labs.exercises.sockets.server;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ServerPoemEager {
    private static final Logger poemLineLogger = LogManager.getLogger(ServerPoemEager.class);
    private static String filePath = ServerPoemEager.class.getClassLoader().getResource("Poem.txt").getPath();
    //getPath() -> convert URL object to string
    //getResources() -> returns a URL object representing the location of the resource
    //getClassLoader() -> loads classes from the target folder
    private static List<String> poemLines;//dynamic array for storing the elements

    public static void loadPoem() {
        if (poemLines != null)
            return;
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
        poemLineLogger.info(poemLines);}//initialize the loadPoem into memory

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
