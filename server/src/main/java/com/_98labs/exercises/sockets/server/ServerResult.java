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
        serverOutput = new PrintWriter(Server.clientSocket.getOutputStream(), true);//allows server to send data back to the client
        if(LoadProperties.eagerProperty().equals("isEnabled")){
            poemLine = ServerPoemLineEager.readPoem(lineNumber);
        }else{
            poemLine = ServerPoemLineLazy.lazyGetPoemLine(lineNumber);
        }
        String result = lineNumber + getSuffix(lineNumber) + " Line: " + poemLine;
        resultLogger.info(result);
        serverOutput.println(result);
    }
    public static String getSuffix(int lineNumber) {
        String suffix;
        if (lineNumber >= 11 && lineNumber <= 13) {
            suffix = "th";
        } else {
            suffix = switch (lineNumber % 10) {//remainder of lineNumber/10
                case 1 -> "st";
                case 2 -> "nd";
                case 3 -> "rd";
                default -> "th";
            };
        }
        return suffix;
    }
}
