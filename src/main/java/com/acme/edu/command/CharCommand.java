package com.acme.edu.command;

import com.acme.edu.command.Command;
import com.acme.edu.saver.LogSaver;

public class CharCommand implements Command {
    private char message = 0;
    private LogSaver saver;

    public CharCommand(char message, LogSaver saver) {
        this.message = message;
        this.saver = saver;
    }

    @Override
    public String getDecoratedString() {
        return "char: " + this.message;
    }

    @Override
    public boolean equals(Command message) {
        return message instanceof CharCommand;
    }

    @Override
    public Command save(Command message) {
        saver.save(getDecoratedString());
        return message;
    }

    @Override
    public void accumulate(Command message) {
    }
}
