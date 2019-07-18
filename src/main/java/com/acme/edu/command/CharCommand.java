package com.acme.edu.command;

import com.acme.edu.command.Command;

public class CharCommand implements Command {
    private char message = 0;

    public CharCommand(char message) {
        this.message = message;
    }

    @Override
    public String getDecoratedString() {
        return "char: " + this.message;
    }

    @Override
    public boolean equals(Command message) {
        return false;
    }

    @Override
    public boolean isAccumulatable() {
        return false;
    }

    @Override
    public void accumulate(Command message) {

    }
}
