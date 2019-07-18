package com.acme.edu;

public class BooleanCommand implements Command {
    private boolean message;

    BooleanCommand(boolean message) {
        this.message = message;
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
