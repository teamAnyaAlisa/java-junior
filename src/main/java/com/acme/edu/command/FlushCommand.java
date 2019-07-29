package com.acme.edu.command;

import com.acme.edu.customExceptions.LogSaverException;

public class FlushCommand implements Command {
    @Override
    public String getDecoratedString() {
        return null;
    }

    @Override
    public boolean equals(Command message) {
        return false;
    }

    @Override
    public Command accumulate(Command message) throws LogSaverException {
        return null;
    }
}
