package com.acme.edu.command;

import com.acme.edu.customExceptions.LogSaverException;
import com.acme.edu.saver.LogSaver;

public class ByteCommand implements Command {
    private int message = 0;

    public ByteCommand(byte message) {
        this.message = message;
    }

    int getMessage() {
        return message;
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
    public Command accumulate(Command message) throws LogSaverException {
        int leftToTypeOverflow = Byte.MAX_VALUE - this.message;
        int messageValue = ((ByteCommand) message).message;
        if (leftToTypeOverflow < messageValue) {
            this.message = messageValue - leftToTypeOverflow;
            return new ByteCommand(Byte.MAX_VALUE);
        }

        this.message += messageValue;
        return null;
    }
}
