package com.acme.edu.command;

import com.acme.edu.command.Command;

public class StringCommand implements Command {
    private String message;

    @Override
    public String getDecoratedString() {
        return "string: " + this.message;
    }
}
