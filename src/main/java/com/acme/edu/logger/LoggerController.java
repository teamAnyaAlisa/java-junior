package com.acme.edu.logger;

import com.acme.edu.command.Command;
import com.acme.edu.saver.LogConsoleSaver;
import com.acme.edu.saver.LogSaver;

public class LoggerController {
    private Command currentMessage = null;
    private LogSaver saver;

    public LoggerController(LogSaver saver) {
        this.saver = saver;
    }

    public Command getCurrentMessage() {
        return currentMessage;
    }

    public void log(Command message) {
        if (currentMessage == null) {
            currentMessage = message;
            return;
        }

        currentMessage = currentMessage.save(message);
    }

    public void log(String message) {
        saver.save(message);
    }

    public void flush() {
        if (currentMessage == null) return;

        currentMessage.flush();
        currentMessage = null;
    }
}
