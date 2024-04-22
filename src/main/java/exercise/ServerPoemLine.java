package exercise;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ServerPoemLine {
    public static int retrieveLineNumber() throws IOException {
        String filepath = "C:\\Users\\brent\\OneDrive\\Documents\\GitHub\\Client-Server-Exercise\\src\\main\\resources\\Poem.txt";
        BufferedReader fileReader = new BufferedReader(new FileReader(filepath));
        Server.clientInput = new BufferedReader(new InputStreamReader(Server.clientSocket.getInputStream()));
        int lineNumber;

        try {
            lineNumber = Integer.parseInt(Server.clientInput.readLine());
        } catch (NumberFormatException e) {
            Server.logger.info("The connection was terminated.");
            return -1;
        }

        int lineCounter = 0;
        while ((Server.poemLine = fileReader.readLine()) != null) {
            lineCounter++;
            if (lineCounter == lineNumber) {
                Server.logger.info("Poem line " + lineNumber + ": " + Server.poemLine + "\n");
                break;
            }
        }

        if (lineCounter < lineNumber) {
            Server.logger.warn("Poem line does not exist.");
        }

        fileReader.close();
        return lineNumber;
    }
}
