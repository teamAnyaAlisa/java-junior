package com.acme.edu.command;

import com.acme.edu.command.Command;

public class ObjectCommand implements Command {
    private Object message;

    ObjectCommand(boolean message) {
        this.message = message;
    }

    @Override
    public String getDecoratedString() {
        return "reference: " + this.message;
    }
}
