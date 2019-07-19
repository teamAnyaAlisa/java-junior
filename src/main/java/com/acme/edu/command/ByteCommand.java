package com.acme.edu.command;

import com.acme.edu.command.Command;

public class ByteCommand extends AccumulatableCommand {
    private int message = 0;

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
    public Command accumulate(Command message) {
        if (Byte.MAX_VALUE - this.message < ((ByteCommand) message).message) {
            this.message = ((ByteCommand) message).message - (Byte.MAX_VALUE - this.message);
            return new ByteCommand(Byte.MAX_VALUE);
        }

        this.message += ((ByteCommand) message).message;
        return null;
    }
}
