package com.miniredis.command;

public class UnknownCommand implements Command {
    private final String command;

    public UnknownCommand(String command) {
        this.command = command;
    }

    @Override
    public String execute() {
        return "(error) unknown command '" + command + "'";
    }
}
