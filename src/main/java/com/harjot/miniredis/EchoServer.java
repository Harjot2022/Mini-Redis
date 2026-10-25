package com.harjot.miniredis;

import java.io.*;
import java.net.*;

public class EchoServer {
    public static void main(String[] args) {
        int port = 9000;
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server is listening on port" + port);

            try (Socket clientSocket = serverSocket.accept()) {
                System.out.println("Client connected" + clientSocket.getInetAddress().getHostAddress());

                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

                String inputLine;

                while ((inputLine = in.readLine()) != null) {
                    out.println(inputLine);
                }

                System.out.println("Client disconnected");
            }

        } catch (IOException e) {
            System.out.println("Error handling client connection: " + e.getMessage());
        }
        System.out.println("Echo Server is running...");
        // Add your echo server implementation here
    }
}