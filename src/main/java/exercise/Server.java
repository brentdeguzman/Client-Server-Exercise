package exercise;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static ServerSocket serverSocket;
    public static Socket clientSocket;
    public static BufferedReader clientInput;
    public static PrintWriter serverOutput;
    public static String poemLine;

    public static void main(String[] args){
        try {
            ServerConnection.establishConnection();
            while (true) {
                int lineNumber = ServerPoemLine.retrieveLineNumber();
                if(lineNumber == -1)
                    break;
                ServerResult.sendResult(lineNumber);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
