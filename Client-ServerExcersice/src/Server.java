import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private static ServerSocket serverSocket;
    private static Socket clientSocket;
    private static BufferedReader clientInput;
    private static PrintWriter serverOutput;
    static String poemLine;

    public static void main(String[] args){
        try {
            System.out.println("Waiting for client to connect...");
            serverSocket = new ServerSocket(9807);//listens for incoming connections on port
            clientSocket = serverSocket.accept();//accept the incoming request to the socket
            System.out.println("Connection between server and client established");
            BufferedReader fileReader = new BufferedReader(new FileReader("Poem.txt"));//reads text from a file
            poemLine = fileReader.readLine();//reads a line of text from the file
            clientInput = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));//reads input from client
            int lineNumber = Integer.parseInt(clientInput.readLine());//convert clientInput as Integer
            int lineCounter = 0;//current line counter
            while(poemLine != null){//read each line as long as the result is not null
                lineCounter += 1;// increase counter by 1 as long as if condition is not met
                if (lineCounter == lineNumber) {//current line number is equal to number given by user
                    System.out.println("Line " + lineNumber + ": " + poemLine);
                    break;
                }
            }
            fileReader.close();
            serverOutput = new PrintWriter(clientSocket.getOutputStream(), true);
            //allows server to send data back to the client
            if (lineNumber == 1 || lineNumber == 21 || lineNumber == 31) {
                serverOutput.println(lineNumber + "st Line: " + poemLine);
            }
            else if (lineNumber == 2 || lineNumber == 22 || lineNumber == 32) {
                serverOutput.println(lineNumber + "nd Line: " + poemLine);
            }
            else if (lineNumber == 3 || lineNumber == 23 || lineNumber == 33) {
                serverOutput.println(lineNumber + "rd Line: " + poemLine);
                }
            else serverOutput.println(lineNumber + "th Line: " + poemLine);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
