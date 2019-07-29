package com.acme.edu.command;

import com.acme.edu.customExceptions.LogSaverException;
import com.acme.edu.saver.LogSaver;

public class BooleanCommand implements Command {
    private boolean message;

    public BooleanCommand(boolean message) {
        this.message = message;
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
    public Command accumulate(Command message) {
        return null;
    }
}
