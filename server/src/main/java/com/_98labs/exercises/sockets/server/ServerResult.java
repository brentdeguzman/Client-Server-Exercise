package com._98labs.exercises.sockets.server;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.PrintWriter;

public class ServerResult {
    private static final Logger resultLogger = LogManager.getLogger(ServerResult.class);
    private static PrintWriter serverOutput;
    private static  String poemLine;
    public static void sendResult(int lineNumber) throws IOException {
        serverOutput = new PrintWriter(Server.clientSocket.getOutputStream(), true);
        poemLine = ServerPoemLine.readPoem(lineNumber);

        //allows server to send data back to the client
        if (lineNumber == 0){
            String invalidInput = "The input is invalid. Poem line starts at 1.";
            resultLogger.warn(invalidInput);
            serverOutput.println(invalidInput);
        }
        if (ServerPoemLine.readPoem(lineNumber) == null){
            String nullPoem = "The requested line number exceeds the total number of lines in the poem.";
            resultLogger.warn(nullPoem);
            serverOutput.println(nullPoem);
        }
        String suffix;
        if (lineNumber >= 11 && lineNumber <= 13){
            suffix = "th";
        } else switch (lineNumber % 10) {//remainder when lineNumber is divided by 10
            case 1:
                suffix = "st";
                break;
            case 2:
                suffix = "nd";
                break;
            case 3:
                suffix = "rd";
                break;
            default:
                suffix = "th";
                break;
        }
        resultLogger.info(lineNumber + suffix + " " + poemLine);
        serverOutput.println(lineNumber + suffix + " " + poemLine);
    }
}
