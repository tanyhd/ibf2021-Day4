package ibf2021.d4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 5000)) {
            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter stringToSend = new PrintWriter(socket.getOutputStream(), true);

            Scanner scanner = new Scanner(System.in);
            String userInput;
            String response; 

            do {
                System.out.println("Get more cookie? ");
                userInput = scanner.nextLine();

                stringToSend.println(userInput);
                if (!userInput.equals("close")) {
                    response = input.readLine();
                    System.out.println(response);
                }
            } while (!userInput.equals("close"));

        } catch (IOException e) {
        System.out.println("Client Error: " + e.getMessage());
        }
    }
}
