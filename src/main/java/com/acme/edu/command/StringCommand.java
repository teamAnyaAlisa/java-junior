package com.acme.edu.command;

import com.acme.edu.saver.LogSaver;

public class StringCommand implements Command {
    private String message;
    private int repetitionMessageCounter = 1;
    private LogSaver saver;

    public StringCommand(String message, LogSaver saver) {
        this.message = message;
        this.saver = saver;
    }

    int getRepetitionMessageCounter() {
        return repetitionMessageCounter;
    }

    @Override
    public String getDecoratedString() {
        if (repetitionMessageCounter == 1) {
            return "string: " + this.message;
        } else {
            return "string: " + this.message + " (x" + repetitionMessageCounter + ")";
        }
    }

    @Override
    public boolean equals(Command message) {
        return message instanceof StringCommand &&
                this.message.equals(((StringCommand) message).message);
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
        repetitionMessageCounter += 1;
    }

    @Override
    public void flush() {
        saver.save(getDecoratedString());
    }
}
