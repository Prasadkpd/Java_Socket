import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServer {
    public static void main(String[] args) throws Exception{
        String clientInput;
        String serverOutput;

        System.out.println("Waiting for clients");

        ServerSocket welcomeSocket = new ServerSocket(9806);

        while (true){
            Socket connectionSocket = welcomeSocket.accept();
            System.out.println("Connection is established");
            System.out.println("Getting Client Data");
//            For read client input
//            Read the input from socket input stream, and it stores in Buffer reader type object
            BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
//            To send data to client from socket output stream
            DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());

            clientInput = inFromClient.readLine();
            serverOutput = clientInput.toUpperCase()+ '\n';
//            This output send byte by byte
            outToClient.writeBytes(serverOutput);
            System.out.println("Client Input:" + clientInput);

        }
    }
}
