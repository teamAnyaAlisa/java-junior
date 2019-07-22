package com.acme.edu.command;

public interface Command {
    String getDecoratedString();

    boolean equals(Command message);

    public Command save(Command message);

    void accumulate(Command message);

    void flush();
}
