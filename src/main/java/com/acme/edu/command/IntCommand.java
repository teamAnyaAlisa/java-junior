package com.acme.edu.command;

import com.acme.edu.command.Command;

public class IntCommand implements Command {
    private int message = 0;

    IntCommand(int message) {
        this.message = message;
    }

    public int getMessage() {
        return message;
    }

    @Override
    public String getDecoratedString() {
        return "primitive: " + this.message;
    }
}
