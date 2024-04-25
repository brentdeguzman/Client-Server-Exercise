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
        poemLine = ServerPoemLine.readPoem(lineNumber); //allows server to send data back to the client
        resultLogger.info(lineNumber + getSuffix(lineNumber) + "Line " + poemLine);
        serverOutput.println(lineNumber + getSuffix(lineNumber) + "Line " + poemLine);
    }
    public static String getSuffix(int lineNumber) {
        String suffix;
        if (lineNumber >= 11 && lineNumber <= 13) {
            suffix = "th";
        } else {
            switch (lineNumber % 10) {
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
        }
        return suffix;
    }
}
