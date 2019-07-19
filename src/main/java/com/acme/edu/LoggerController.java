package com.acme.edu;

import com.acme.edu.command.AccumulatableCommand;
import com.acme.edu.command.Command;
import com.acme.edu.saver.LogSaver;

public class LoggerController {
    private final LogSaver saver;
    private Command currentMessage = null;

    LoggerController(LogSaver saver) {
        this.saver = saver;
    }

    // TODO спросить про момент сброса значения для неаккумулирующих типов
    public void log(Command message) {
        if (currentMessage == null) {
            currentMessage = message;
            return;
        }

        if (currentMessage.equals(message) && (currentMessage instanceof AccumulatableCommand)) {
            Command messageToFlush = ((AccumulatableCommand) currentMessage).accumulate(message);
            if (messageToFlush != null) {
                saver.save(messageToFlush.getDecoratedString());
            }
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
