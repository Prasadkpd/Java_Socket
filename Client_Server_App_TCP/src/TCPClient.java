import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class TCPClient {
    public static void main(String[] args) throws Exception {
        String clientInput, serverInput;

//        To get client input
//        System.in get as input stream type object, and it stores in buffer reader type object
        BufferedReader inFromClient = new BufferedReader(new InputStreamReader(System.in));

        Socket clientSocket = new Socket("localhost", 9806);
        System.out.println("Enter a string:");
//      To send to server
        DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
//      To get data from server
        BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
//      After user input done get that in to string object
        clientInput = inFromClient.readLine();
//        To send it to server
        outToServer.writeBytes(clientInput + '\n');
//        Get come from server
        serverInput = inFromServer.readLine();
        System.out.println("From Server:" + serverInput);
//        Finally close the socket
    }
}
