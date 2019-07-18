package com.acme.edu.command;

import com.acme.edu.command.Command;

public class IntCommand implements Command {
    private int message = 0;

    public IntCommand(int message) {
        this.message = message;
    }

    @Override
    public String getDecoratedString() {
        return "primitive: " + this.message;
    }

    @Override
    public boolean equals(Command message) {
        return message instanceof IntCommand;
    }

    @Override
    public boolean isAccumulatable() {
        return true;
    }

    @Override
    public void accumulate(Command message) {
        this.message += ((IntCommand) message).message;
    }
}
