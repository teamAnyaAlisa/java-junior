package com.acme.edu;

public class ObjectCommand implements Command {
    private Object message;

    ObjectCommand(boolean message) {
        this.message = message;
    }

    @Override
    public String decorateMessage() {
        return "reference: ";
    }

    @Override
    public String getLogString() {
        return decorateMessage() + this.message;
    }
}
