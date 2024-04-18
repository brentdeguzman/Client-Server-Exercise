package exercise;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Paths;

public class ServerPoemLine {
    public static int retrieveLineNumber() throws IOException {
        String filepath = "C:\\Users\\brent\\OneDrive\\Documents\\GitHub\\Client-Server-Exercise\\src\\main\\resources\\Poem.txt";
        BufferedReader fileReader = new BufferedReader(new FileReader(filepath));
        Server.clientInput = new BufferedReader(new InputStreamReader(Server.clientSocket.getInputStream()));
        int lineNumber;
        try {
            lineNumber = Integer.parseInt(Server.clientInput.readLine());
        } catch (NumberFormatException e) {
            System.out.println("The connection was terminated.");
            return -1;
        }
        int lineCounter = 0;
        while ((Server.poemLine = fileReader.readLine()) != null) {
            lineCounter++;
            if (lineCounter == lineNumber) {
                System.out.println("Poem line " + lineNumber + ": " + Server.poemLine + "\n");
                break;
            }
        }
        if (lineCounter < lineNumber) {
            System.out.println("Poem line does not exist.");
        }
        fileReader.close();
        return lineNumber;
    }
}
