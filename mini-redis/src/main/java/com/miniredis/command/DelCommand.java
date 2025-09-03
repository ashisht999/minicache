package com.miniredis.command;

import com.miniredis.storage.DataStore;

import java.util.List;

public class DelCommand implements Command {
    private final DataStore dataStore;
    private final List<String> args;

    public DelCommand(DataStore dataStore, List<String> args) {
        this.dataStore = dataStore;
        this.args = args;
    }

    @Override
    public String execute() {
        if (args.size() < 1) {
            return "(error) wrong number of arguments for 'DEL'";
        }
        String key = args.get(0);
        boolean deleted = dataStore.del(key);
        return deleted ? "OK" : "(nil)";
    }
}
