import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args) {

        try {
            System.out.println("Waiting For Clients Requests");
//            Creating an object from ServerSocket for create a socket in server
            ServerSocket serverSocket = new ServerSocket(9806);
//            Creating blocking call wait until a request from client
            Socket socket = serverSocket.accept();
            System.out.println("Connection is established");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
