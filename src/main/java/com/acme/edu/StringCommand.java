package com.acme.edu;

public class StringCommand implements Command {
    private String message;

    @Override
    public String decorateMessage() {
        return "string: ";
    }

    @Override
    public String getLogString() {
        return decorateMessage() + this.message;
    }
}
