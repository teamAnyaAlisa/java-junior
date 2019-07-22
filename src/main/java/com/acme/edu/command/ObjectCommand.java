package com.acme.edu.command;

import com.acme.edu.command.Command;
import com.acme.edu.saver.LogSaver;
import sun.rmi.runtime.Log;

public class ObjectCommand implements Command {
    private Object message;
    private LogSaver saver;

    public ObjectCommand(Object message, LogSaver saver) {
        this.message = message;
        this.saver = saver;
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
    public Command save(Command message) {
        flush();
        return message;
    }

    @Override
    public void accumulate(Command message) {
    }

    @Override
    public void flush() {
        saver.save(getDecoratedString());
    }
}
