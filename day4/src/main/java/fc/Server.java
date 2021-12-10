package fc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {

        int port = 5000;
        String fileName = "cookie_file.txt";

        if (args.length != 0) {
            port = Integer.parseInt(args[0]);
            fileName = args[1];
            System.out.println("Server listening at port: " + port);
        } else {
            System.out.println("Server listening at port: " + port);
        }

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            Socket socket = serverSocket.accept();
            System.out.println("Client Connected");

            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream())); // information
                                                                                                       // get from
                                                                                                       // client
            PrintWriter output = new PrintWriter(socket.getOutputStream(), true); // information to sent to client

            String text = "";

            while (true) {
                String userInput = input.readLine();
                // System.out.println(userInput); //can be use to print user input from client
                if (userInput.equals("close")) {
                    System.out.println("Server close ...");
                    break;
                } else if (userInput.equals("get-cookie")) {
                    System.out.println("Sending a cookie ...");
                    Cookie cookieText = new Cookie();
                    cookieText.setUserFile(fileName);
                    text = cookieText.getText();
                } else {
                    text = "Incorrect request";
                }

                output.println("cookie-text: " + text);

            }

        } catch (IOException e) {
            System.out.println("Server exception " + e.getMessage());
        }
    }
}
