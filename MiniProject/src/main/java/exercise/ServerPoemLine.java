package exercise;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ServerPoemLine {
    public static int getPoemLine() throws IOException {
        String filepath = "C:\\Users\\brent\\IdeaProjects\\MiniProject\\Poem.txt";
        BufferedReader fileReader = new BufferedReader(new FileReader(filepath));
        Server.clientInput = new BufferedReader(new InputStreamReader(Server.clientSocket.getInputStream()));
        int lineNumber = Integer.parseInt(Server.clientInput.readLine());
        int lineCounter = 0;
        while ((Server.poemLine = fileReader.readLine()) != null) {
            lineCounter += 1;
            if (lineCounter == lineNumber) {
                System.out.println("Poem line " + lineNumber + ": " + Server.poemLine);
                break;
            }
        }
        fileReader.close();
        return lineNumber;
    }
}
