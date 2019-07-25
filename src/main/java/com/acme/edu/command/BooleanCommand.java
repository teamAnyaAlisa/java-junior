package com.acme.edu.command;

import com.acme.edu.customExceptions.LogSaverException;
import com.acme.edu.saver.LogSaver;

public class BooleanCommand implements Command {
    private boolean message;
    private LogSaver saver;

    public BooleanCommand(boolean message, LogSaver saver) {
        this.message = message;
        this.saver = saver;
    }

    boolean getMessage() {
        return message;
    }

    @Override
    public String getDecoratedString() {
        return "primitive: " + this.message;
    }

    @Override
    public boolean equals(Command message) {
        return message instanceof BooleanCommand;
    }

    @Override
    public Command save(Command message) throws LogSaverException {
        flush();
        return message;
    }

    @Override
    public void accumulate(Command message) {
    }

    @Override
    public void flush() throws LogSaverException {
        saver.save(getDecoratedString());
    }
}
