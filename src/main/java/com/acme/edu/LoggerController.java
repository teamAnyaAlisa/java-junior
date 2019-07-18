package com.acme.edu;

public class LoggerController {
    private final LogSaver saver;

    LoggerController(LogSaver saver) {
        this.saver = saver;
    }

    public void log(Command message) {
        saver.save(message.getLogString());
    }

    public void log(String message) {
        saver.save(message);
    }
}
