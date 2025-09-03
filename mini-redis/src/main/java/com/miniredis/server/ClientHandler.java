package com.miniredis.server;

import com.miniredis.command.Command;
import com.miniredis.command.CommandFactory;
import com.miniredis.storage.DataStore;

import java.io.*;
import java.net.Socket;

public class ClientHandler implements Runnable {
    private final Socket socket;
    private final DataStore dataStore;

    public ClientHandler(Socket socket, DataStore dataStore) {
        this.socket = socket;
        this.dataStore = dataStore;
    }

    @Override
    public void run() {
        try (
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true)
        ) {
            String line;
            while ((line = in.readLine()) != null) {
                Command command = CommandFactory.create(line, dataStore);
                String response = command.execute();
                out.println(response);

                if ("QUIT".equalsIgnoreCase(line.trim())) {
                    socket.close();
                    break;
                }
            }
        } catch (IOException e) {
            System.out.println("Connection closed: " + socket);
        }
    }
}
