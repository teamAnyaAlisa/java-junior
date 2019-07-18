package com.acme.edu;

import com.acme.edu.command.Command;
import com.acme.edu.saver.LogSaver;

public class LoggerController {
    private final LogSaver saver;
    private Command currentMessage = null;

    LoggerController(LogSaver saver) {
        this.saver = saver;
    }

    public void log(Command message) {
        if (currentMessage == null) {
            currentMessage = message;
            return;
        }

        if (currentMessage.equals(message) && currentMessage.isAccumulatable()) {
            currentMessage.accumulate(message);
        } else {
            saver.save(currentMessage.getDecoratedString());
            currentMessage = message;
        }
    }

    public void log(String message) {
        saver.save(message);
    }

    public void flush() {
        if (currentMessage == null) return;

        saver.save(currentMessage.getDecoratedString());
        currentMessage = null;
    }
}
