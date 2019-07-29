package com.acme.edu.command;

import com.acme.edu.customExceptions.LogSaverException;
import com.acme.edu.saver.LogSaver;

public class StringCommand implements Command {
    private String message;
    private int repetitionMessageCounter = 1;

    public StringCommand(String message) {
        this.message = message;
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
    public Command accumulate(Command message) {
        repetitionMessageCounter += 1;
        return null;
    }
}
