package com.acme.edu.logger;

import com.acme.edu.command.Command;
import com.acme.edu.customExceptions.LogSaverException;
import com.acme.edu.customExceptions.NullSaverException;
import com.acme.edu.saver.LogSaver;

import java.util.Objects;

public class LoggerController {
    private Command currentMessage = null;
    private LogSaver saver;

    public LoggerController(LogSaver saver) throws LogSaverException{
        if (saver == null) {
            throw new NullSaverException("saver can`t be null");
        }
        this.saver = saver;
    }

    public Command getCurrentMessage() {
        return currentMessage;
    }

    public void log(Command message) throws LogSaverException {
        if (currentMessage == null) {
            currentMessage = message;
            return;
        }

        if (!Objects.equals(currentMessage, message)) {
            saver.save(currentMessage.getDecoratedString());
            currentMessage = message;
            return;
        }

        Command messageToFlush = currentMessage.accumulate(message);
        if (messageToFlush != null) {
            saver.save(messageToFlush.getDecoratedString());
        }
    }

    public void log(String message) throws LogSaverException {
        saver.save(message);
    }

    public void flush() throws LogSaverException {
        if (currentMessage == null) return;

        saver.save(currentMessage.getDecoratedString());
        currentMessage = null;
    }

    public void close() throws LogSaverException {
        saver.close();
    }
}