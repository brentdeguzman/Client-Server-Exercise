package com._98labs.exercises.sockets.server;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.PrintWriter;

public class ServerResult {
    private static final Logger resultLogger = LogManager.getLogger(ServerResult.class);
    public static void sendResult(int lineNumber) throws IOException {
        Server.serverOutput = new PrintWriter(Server.clientSocket.getOutputStream(), true);
        //allows server to send data back to the client
        if (lineNumber == 0){
            String invalidInput = "The input is invalid. Poem line starts at 1.";
            resultLogger.warn(invalidInput);
            Server.serverOutput.println(invalidInput);
        }else if (Server.poemLine == null){
            String nullPoem = "The requested line number exceeds the total number of lines in the poem.";
            resultLogger.warn(nullPoem);
            Server.serverOutput.println(nullPoem);
        } else if (lineNumber >= 11 && lineNumber <= 13){
            Server.serverOutput.println(lineNumber + "th Line: " + Server.poemLine);
        } else switch (lineNumber % 10) {//remainder when lineNumber is divided by 10
            case 1:
                Server.serverOutput.println(lineNumber + "st Line: " + Server.poemLine);
                break;
            case 2:
                Server.serverOutput.println(lineNumber + "nd Line: " + Server.poemLine);
                break;
            case 3:
                Server.serverOutput.println(lineNumber + "rd Line: " + Server.poemLine);
                break;
            default:
                Server.serverOutput.println(lineNumber + "th Line: " + Server.poemLine);
                break;
        }
    }
}
