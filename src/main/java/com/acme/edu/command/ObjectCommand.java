package com.acme.edu.command;

import com.acme.edu.command.Command;
import com.acme.edu.customExceptions.LogSaverException;
import com.acme.edu.saver.LogSaver;
import sun.rmi.runtime.Log;

public class ObjectCommand implements Command {
    private Object message;

    public ObjectCommand(Object message) {
        this.message = message;
    }

    Object getMessage() {
        return message;
    }

    @Override
    public String getDecoratedString() {
        return "reference: " + this.message;
    }

    @Override
    public boolean equals(Command message) {
        return message instanceof ObjectCommand;
    }

    @Override
    public Command accumulate(Command message) {
        return null;
    }
}
