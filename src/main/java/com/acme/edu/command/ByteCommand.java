package com.acme.edu.command;

import com.acme.edu.saver.LogSaver;

public class ByteCommand implements Command {
    private int message = 0;
    private LogSaver saver;

    public ByteCommand(byte message, LogSaver saver) {
        this.message = message;
        this.saver = saver;
    }

    int getMessage() {
        return message;
    }

    @Override
    public String getDecoratedString() {
        return "primitive: " + this.message;
    }

    @Override
    public boolean equals(Command message) {
        return message instanceof ByteCommand;
    }

    @Override
    public Command save(Command message) {
        if (!equals(message)) {
            flush();
            return message;
        }

        accumulate(message);
        return this;
    }

    @Override
    public void accumulate(Command message) {
        int leftToTypeOverflow = Byte.MAX_VALUE - this.message;
        int messageValue = ((ByteCommand) message).message;
        if (leftToTypeOverflow < messageValue) {
            this.message = messageValue - leftToTypeOverflow;
            saver.save((new ByteCommand(Byte.MAX_VALUE, this.saver)).getDecoratedString());
            return;
        }

        this.message += messageValue;
    }

    @Override
    public void flush() {
        saver.save(getDecoratedString());
    }
}
