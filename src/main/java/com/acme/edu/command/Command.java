package com.acme.edu.command;

public interface Command {
    String getDecoratedString();

    boolean equals(Command message);
}
