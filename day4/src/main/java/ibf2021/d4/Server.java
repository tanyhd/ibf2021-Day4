package ibf2021.d4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {

        try (ServerSocket serverSocket = new ServerSocket(5000)) {
            Socket socket = serverSocket.accept();
            System.out.println("Client Connected");

            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter output = new PrintWriter(socket.getOutputStream(), true);

            String text = "";

            while (true) {
                String userInput = input.readLine();
                if (userInput.equals("close")) {
                    break;
                } else if (userInput.equals("get-cookie")) {
                    Cookie cookieText = new Cookie();
                    text = cookieText.getText();
                }

                output.println(text);

            }
              
        } catch (IOException e) {
            System.out.println("Server exception " + e.getMessage());
        }
    }
}
