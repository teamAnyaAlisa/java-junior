package com.acme.edu.logger;

import com.acme.edu.command.Command;
import com.acme.edu.saver.LogConsoleSaver;
import com.acme.edu.saver.LogSaver;

public class LoggerController {
    private Command currentMessage = null;
    private LogSaver saver = new LogConsoleSaver();

    // TODO спросить про момент сброса значения для неаккумулирующих типов
    public void log(Command message) {
        if (currentMessage == null) {
            currentMessage = message;
            return;
        }

        currentMessage = currentMessage.save(message);

        /*
        if (currentMessage.equals(message) && (currentMessage instanceof AccumulatableCommand)) {
            Command messageToFlush = ((AccumulatableCommand) currentMessage).accumulate(message);
            if (messageToFlush != null) {
                saver.save(messageToFlush.getDecoratedString());
            }
        } else {
            saver.save(currentMessage.getDecoratedString());
            currentMessage = message;
        }
        */
    }

    public void log(String message) {
        saver.save(message);
    }

    // TODO: подумать, как переместить flush() в Command
    public void flush() {
        if (currentMessage == null) return;

        saver.save(currentMessage.getDecoratedString());
        currentMessage = null;
    }
}
