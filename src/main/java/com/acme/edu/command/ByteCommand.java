package com.acme.edu.command;

import com.acme.edu.command.Command;

public class ByteCommand implements Command {
    private byte message = 0;

    ByteCommand(byte message) {
        this.message = message;
    }

    @Override
    public String getDecoratedString() {
        return "primitive: " + this.message;
    }
}
