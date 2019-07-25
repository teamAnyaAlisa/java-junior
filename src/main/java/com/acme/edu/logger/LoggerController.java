package com.acme.edu.logger;

import com.acme.edu.command.Command;
import com.acme.edu.customExceptions.LogSaverException;
import com.acme.edu.customExceptions.NullSaverException;
import com.acme.edu.saver.LogSaver;

// TODO: выпилить save из Command, сообщать сюда о том, что пора сохранить, например, при помощи возвращаемого значения (boolean или Command)
public class LoggerController {
    private Command currentMessage = null;
    private LogSaver  saver;

    public LoggerController(LogSaver saver) throws NullSaverException{
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

        currentMessage = currentMessage.save(message);
    }

    public void log(String message) throws LogSaverException {
        saver.save(message);
    }

    public void flush() throws LogSaverException {
        if (currentMessage == null) return;

        currentMessage.flush();
        currentMessage = null;
    }

    public void close() {

    }
}