package com.acme.edu.command;

import com.acme.edu.command.Command;

public class IntCommand extends AccumulatableCommand {
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
    public Command accumulate(Command message) {
        if (Integer.MAX_VALUE - this.message < ((IntCommand) message).message) {
            this.message = ((IntCommand) message).message - (Integer.MAX_VALUE - this.message);
            return new IntCommand(Integer.MAX_VALUE);
        }

        this.message += ((IntCommand) message).message;
        return null;
    }
}
