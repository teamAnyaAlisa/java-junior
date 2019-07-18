package com.acme.edu.command;

import com.acme.edu.command.Command;

public class CharCommand implements Command {
    private char message = 0;

    CharCommand(char message) {
        this.message = message;
    }

    @Override
    public String getDecoratedString() {
        return "char: " + this.message;
    }
}
