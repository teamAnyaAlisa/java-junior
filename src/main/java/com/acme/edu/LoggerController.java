package com.acme.edu;

import com.acme.edu.command.Command;
import com.acme.edu.saver.LogSaver;

public class LoggerController {
    private final LogSaver saver;

    LoggerController(LogSaver saver) {
        this.saver = saver;
    }

    public void log(Command message) {
        saver.save(message.getDecoratedString());
    }

    public void log(String message) {
        saver.save(message);
    }
}
