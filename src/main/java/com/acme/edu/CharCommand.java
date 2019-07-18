package com.acme.edu;

public class CharCommand implements Command {
    private char message = 0;

    CharCommand(char message) {
        this.message = message;
    }

    @Override
    public String decorateMessage() {
        return "char: ";
    }

    @Override
    public String getLogString() {
        return decorateMessage() + this.message;
    }
}
