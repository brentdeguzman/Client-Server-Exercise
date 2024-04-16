package exercise;

import java.io.IOException;
import java.io.PrintWriter;

public class ServerResult {
    public static void serverResult(int lineNumber) throws IOException {
        Server.serverOutput = new PrintWriter(Server.clientSocket.getOutputStream(), true);
        //allows server to send data back to the client
        if (lineNumber == 1 || lineNumber == 21 || lineNumber == 31) {
            Server.serverOutput.println(lineNumber + "st Line: " + Server.poemLine);
        } else if (lineNumber == 2 || lineNumber == 22 || lineNumber == 32) {
            Server.serverOutput.println(lineNumber + "nd Line: " + Server.poemLine);
        } else if (lineNumber == 3 || lineNumber == 23 || lineNumber == 33) {
            Server.serverOutput.println(lineNumber + "rd Line: " + Server.poemLine);
        } else Server.serverOutput.println(lineNumber + "th Line: " + Server.poemLine);
    }
}
