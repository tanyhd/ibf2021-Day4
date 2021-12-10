package fc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        int port = 5000;
        String host = "localhost";
        String[] hostAndPort;

        if (args.length != 0) {
            hostAndPort = args[0].split(":");
            host = hostAndPort[0];
            port = Integer.parseInt(hostAndPort[1]);
            System.out.println("Connecting to " + host + " at port" + port);
        } else {
            System.out.println("Connecting to default " + host + " at port: " + port);
        }


        try (Socket socket = new Socket(host, port)) {
            BufferedReader serverOutput = new BufferedReader(new InputStreamReader(socket.getInputStream())); // infomation from sever
            PrintWriter stringToSend = new PrintWriter(socket.getOutputStream(), true); // information to send to server

            Scanner scanner = new Scanner(System.in);
            String userInput;
            String outputFromServer; 

            do {
                System.out.println("Enter request");
                userInput = scanner.nextLine();

                stringToSend.println(userInput);
                if (!userInput.equals("close")) {
                    outputFromServer = serverOutput.readLine();
                    System.out.println(outputFromServer);
                }
            } while (!userInput.equals("close"));

        } catch (IOException e) {
        System.out.println("Client Error: " + e.getMessage());
        }
    }
}
