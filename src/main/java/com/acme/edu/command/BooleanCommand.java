package com.acme.edu.command;

public class BooleanCommand implements Command {
    private boolean message;

    public BooleanCommand(boolean message) {
        this.message = message;
    }

    @Override
    public String getDecoratedString() {
        return "primitive: " + this.message;
    }

    @Override
    public boolean equals(Command message) {
        return message instanceof BooleanCommand;
    }

    @Override
    public boolean isAccumulatable() {
        return false;
    }

    @Override
    public void accumulate(Command message) {

    }
}
