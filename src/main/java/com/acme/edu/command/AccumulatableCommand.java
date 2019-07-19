package com.acme.edu.command;

public abstract class AccumulatableCommand implements Command {
    public abstract Command accumulate(Command message);
}

