package com.miniredis.command;

import com.miniredis.storage.DataStore;

import java.util.List;

public class GetCommand implements Command {
    private final DataStore dataStore;
    private final List<String> args;

    public GetCommand(DataStore dataStore, List<String> args) {
        this.dataStore = dataStore;
        this.args = args;
    }

    @Override
    public String execute() {
        if (args.size() < 1) {
            return "(error) wrong number of arguments for 'GET'";
        }
        String key = args.get(0);
        String value = dataStore.get(key);
        return value == null ? "(nil)" : value;
    }
}
