package com.acme.edu.command;

import com.acme.edu.customExceptions.LogSaverException;

public interface Command {
    String getDecoratedString();

    boolean equals(Command message);

    public Command save(Command message) throws LogSaverException;

    void accumulate(Command message) throws LogSaverException;

    void flush() throws LogSaverException;
}
