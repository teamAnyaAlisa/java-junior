package com.acme.edu.command;

import com.acme.edu.command.Command;

public class ByteCommand implements Command {
    private byte message = 0;

    public ByteCommand(byte message) {
        this.message = message;
    }

    @Override
    public String getDecoratedString() {
        return "primitive: " + this.message;
    }

    @Override
    public boolean equals(Command message) {
        return message instanceof ByteCommand;
    }

    @Override
    public boolean isAccumulatable() {
        return true;
    }

    @Override
    public void accumulate(Command message) {
        this.message += ((ByteCommand) message).message;
    }
}
