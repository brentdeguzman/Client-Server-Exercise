package exercise;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Server {
    public static ServerSocket serverSocket;
    public static Socket clientSocket;
    public static BufferedReader clientInput;
    public static PrintWriter serverOutput;
    public static String poemLine;
    public static final Logger logger = LogManager.getLogger(Server.class);

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
