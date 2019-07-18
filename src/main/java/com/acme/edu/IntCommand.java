package com.acme.edu;

public class IntCommand implements Command {
    private int message = 0;

    IntCommand(int message) {
        this.message = message;
    }

    public int getMessage() {
        return message;
    }

    @Override
    public String decorateMessage() {
        return "primitive: ";
    }

    @Override
    public String getLogString() {
        return decorateMessage() + this.message;
    }
}
