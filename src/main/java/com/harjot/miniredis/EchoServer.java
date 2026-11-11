package com.harjot.miniredis;

import java.io.*;
import java.net.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class EchoServer {
    public static void main(String[] args) {
        ExecutorService pool = Executors.newFixedThreadPool(10);

        int port = 9000;

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server is listening on port" + port);
            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("New client connected: " + clientSocket.getRemoteSocketAddress());
                pool.execute(() -> handleClient(clientSocket));
            }

        } catch (IOException e) {
            System.out.println("Error handling client connection: " + e.getMessage());
        } finally {
            pool.shutdown();
        }
    }

    public static void handleClient(Socket Socket) {
        try (Socket clientSocket = Socket;
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)) {
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                out.println(Thread.currentThread().getName() + " received: " + inputLine);
            }
        } catch (IOException e) {
            System.out.println("Error handling client: " + e.getMessage());
        }
        System.out.println("Client disconnected.");
    }
}