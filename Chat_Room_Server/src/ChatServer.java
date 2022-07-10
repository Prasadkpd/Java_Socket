import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.HashSet;

public class ChatServer {

    private static final int PORT = 9001;
    //  Make hashset to uniquely handle usernames
    private static HashSet<String> names = new HashSet<String>();
    //   Make client to broadcast msg
    private static HashSet<PrintWriter> writers = new HashSet<PrintWriter>()

    public static void main(String[] args) throws Exception {

    }

    //    To handle with single client and his broadcasting messages
    private static class Handle implements Runnable {
        private String name;
        private Socket socket;
        private BufferedReader inputMsg;
        private PrintWriter outFromClient;

        public Handler(Socket socket) {
            this.socket = socket;
        }

        // Run until client enter unique name
        public void run() {

            try {
                inputMsg = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                outFromClient = new PrintWriter(socket.getOutputStream(), true);

                while (true) {
                    outFromClient.println("Enter Suitable Name");
                    name = inputMsg.readLine();

                    if (name == null) {
                        return;
                    }

                    if (!names.contains(name)) {
                        names.add(name);
                        break;
                    }
                }
                outFromClient.println("Name Accepted");
                writers.add(outFromClient);

                while (true) {
//                    Accept the client msg and broadcast
                    String input = inputMsg.readLine();
                    if (input == null) {
                        return;
                    }
                    for (PrintWriter writer : writers) {
                        writer.println("Message from " + name + ":" + input);
                    }
                }

            } catch (IOException e) {
                System.out.println(e);
            } finally {
                if (names != null) {
                    names.remove(name);
                }
                if (outFromClient != null) {
                    writers.remove(outFromClient);
                }
                try {
                    socket.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}

