import java.io.*;
import java.net.*;

public class Collaboration {
    private static final String SERVER_IP = "localhost";
    private static final int SERVER_PORT = 5555;
    private static Socket socket;
    private static BufferedReader in;
    private static PrintWriter out;

    public static void connectToServer() {
        try {
            socket = new Socket(SERVER_IP, SERVER_PORT);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);
            System.out.println("Connected to collaboration server.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void sendMessage(String message) {
        out.println(message);
    }

    public static String receiveMessage() {
        try {
            return in.readLine();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void disconnect() {
        try {
            if (socket != null) {
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
