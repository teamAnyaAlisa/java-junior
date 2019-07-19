package com.acme.edu.command;

import com.acme.edu.saver.LogSaver;

public class IntCommand implements Command {
    private int message = 0;
    private LogSaver saver;

    public IntCommand(int message, LogSaver saver) {
        this.message = message;
        this.saver = saver;
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
    public Command save(Command message) {
        if (!equals(message)) {
            saver.save(getDecoratedString());
            return message;
        }
        
        accumulate(message);
        return this;
    }

    @Override
    public void accumulate(Command message) {
        int leftToTypeOverflow = Integer.MAX_VALUE - this.message;
        int messageValue = ((IntCommand) message).message;
        if (leftToTypeOverflow < messageValue) {
            this.message = messageValue - leftToTypeOverflow;
            saver.save((new IntCommand(Integer.MAX_VALUE, this.saver)).getDecoratedString());
            return;
        }

        this.message += messageValue;
    }
}
