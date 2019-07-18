package com.acme.edu;

public class ByteCommand implements Command {
    private byte message = 0;

    ByteCommand(byte message) {
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
