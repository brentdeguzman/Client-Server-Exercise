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
        poemLine = ServerPoemLine.readPoem();
        //allows server to send data back to the client
        if (lineNumber == 0){
            String invalidInput = "The input is invalid. Poem line starts at 1.";
            resultLogger.warn(invalidInput);
            serverOutput.println(invalidInput);
        }else if (ServerPoemLine.readPoem() == null){
            String nullPoem = "The requested line number exceeds the total number of lines in the poem.";
            resultLogger.warn(nullPoem);
            serverOutput.println(nullPoem);
        } else if (lineNumber >= 11 && lineNumber <= 13){
            serverOutput.println(lineNumber + "th Line: " + poemLine);
        } else switch (lineNumber % 10) {//remainder when lineNumber is divided by 10
            case 1:
                resultLogger.info(lineNumber + "st Line: " + poemLine);
                serverOutput.println(lineNumber + "st Line: " + poemLine);
                break;
            case 2:
                resultLogger.info(lineNumber + "nd Line: " + poemLine);
                serverOutput.println(lineNumber + "nd Line: " + poemLine);
                break;
            case 3:
                resultLogger.info(lineNumber + "rd Line: " + poemLine);
                serverOutput.println(lineNumber + "rd Line: " + poemLine);
                break;
            default:
                resultLogger.info(lineNumber + "th Line: " + poemLine);
                serverOutput.println(lineNumber + "th Line: " + poemLine);
                break;
        }
    }
}
