package com.acme.edu.command;

import com.acme.edu.command.Command;

public class ObjectCommand implements Command {
    private Object message;

    public ObjectCommand(Object message) {
        this.message = message;
    }

    @Override
    public String getDecoratedString() {
        return "reference: " + this.message;
    }

    @Override
    public boolean equals(Command message) {
        return message instanceof ObjectCommand;
    }
}
