
package com.harjot.miniredis;

import java.io.*;
import java.net.Socket;

public class EchoClient {

    public static void main(String[] args) throws IOException {

        try (Socket socket = new Socket("localhost", 9000);
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(socket.getInputStream()));
                PrintWriter out = new PrintWriter(
                        socket.getOutputStream(), true);
                BufferedReader keyboard = new BufferedReader(
                        new InputStreamReader(System.in))) {

            System.out.println("Connected. Type a message:");

            String message;

            while ((message = keyboard.readLine()) != null) {
                out.println(message);

                String response = in.readLine();
                System.out.println("Server: " + response);
            }
        }
    }
}
