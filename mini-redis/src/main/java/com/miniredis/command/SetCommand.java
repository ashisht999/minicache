package com.miniredis.command;

import com.miniredis.storage.DataStore;

import java.util.List;

public class SetCommand implements Command {
    private final DataStore dataStore;
    private final List<String> args;

    public SetCommand(DataStore dataStore, List<String> args) {
        this.dataStore = dataStore;
        this.args = args;
    }

    @Override
    public String execute() {
        if (args.size() < 2) {
            return "(error) wrong number of arguments for 'SET'";
        }
        String key = args.get(0);
        String value = args.get(1);
        dataStore.set(key, value);
        return "OK";
    }
}
