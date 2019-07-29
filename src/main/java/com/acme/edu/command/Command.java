package com.acme.edu.command;

import com.acme.edu.customExceptions.LogSaverException;

public interface Command {
    String getDecoratedString();

    boolean equals(Command message);

    Command accumulate(Command message) throws LogSaverException;
}
