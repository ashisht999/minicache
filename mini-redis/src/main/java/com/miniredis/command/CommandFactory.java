package com.miniredis.command;

import com.miniredis.server.CommandParser;
import com.miniredis.storage.DataStore;

import java.util.List;

public class CommandFactory {
    public static Command create(String input, DataStore dataStore) {
        List<String> tokens = CommandParser.parse(input);
        if (tokens.isEmpty()) {
            return new UnknownCommand("");
        }

        String cmd = tokens.get(0).toUpperCase();
        List<String> args = tokens.subList(1, tokens.size());

        switch (cmd) {
            case "SET":
                return new SetCommand(dataStore, args);
            case "GET":
                return new GetCommand(dataStore, args);
            case "DEL":
                return new DelCommand(dataStore, args);
            case "QUIT":
                return () -> "OK";
            default:
                return new UnknownCommand(cmd);
        }
    }
}
