package com.acme.edu.command;

import com.acme.edu.customExceptions.LogSaverException;
import com.acme.edu.saver.LogSaver;

public class IntCommand implements Command {
    private int message = 0;

    public IntCommand(int message) {
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
        return message instanceof IntCommand;
    }

    @Override
    public Command accumulate(Command message) throws LogSaverException {
        int leftToTypeOverflow = Integer.MAX_VALUE - this.message;
        int messageValue = ((IntCommand) message).message;
        if (leftToTypeOverflow < messageValue) {
            this.message = messageValue - leftToTypeOverflow;
            return new IntCommand(Integer.MAX_VALUE);
        }

        this.message += messageValue;
        return null;
    }
}
