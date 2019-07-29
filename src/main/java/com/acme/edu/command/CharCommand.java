package com.acme.edu.command;

import com.acme.edu.command.Command;
import com.acme.edu.customExceptions.LogSaverException;
import com.acme.edu.saver.LogSaver;

public class CharCommand implements Command {
    private char message = 0;

    public CharCommand(char message) {
        this.message = message;
    }

    char getMessage() {
        return message;
    }

    @Override
    public String getDecoratedString() {
        return "char: " + this.message;
    }

    @Override
    public boolean equals(Command message) {
        return message instanceof CharCommand;
    }

    public Command accumulate(Command message) {
        return null;
    }
}
