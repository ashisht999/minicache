package com.miniredis;

import com.miniredis.server.ClientHandler;
import com.miniredis.storage.DataStore;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class MiniRedisServer {
    private static final int PORT = 6379;

    public static void main(String[] args) {
        DataStore dataStore = new DataStore();

        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("MiniRedis started on port " + PORT);

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connected: " + clientSocket);

                ClientHandler handler = new ClientHandler(clientSocket, dataStore);
                new Thread(handler).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
