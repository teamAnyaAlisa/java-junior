package com.acme.edu.logger;

import com.acme.edu.command.*;
import com.acme.edu.customExceptions.LogSaverException;
import com.acme.edu.customExceptions.NullSaverException;
import com.acme.edu.saver.LogConsoleSaver;
import com.acme.edu.saver.LogSaver;

public class Logger {
    private static LogSaver logConsoleSaver = new LogConsoleSaver();
    private static LoggerController loggerController;

    static {
        try {
            loggerController = new LoggerController(logConsoleSaver);
        } catch (NullSaverException e) {
            e.printStackTrace();
        }
    }

    public static void log(int message) throws LogSaverException {
        loggerController.log(new IntCommand(message));
    }

    public static void log(byte message) throws LogSaverException {
        loggerController.log(new ByteCommand(message));
    }

    private static String arrayMessageToString(int[] message) {
        StringBuilder result = new StringBuilder("{");
        for (int index = 0; index < message.length-1; index++) {
            result.append(String.valueOf(message[index])).append(", ");
        }
        result.append(message[message.length-1]).append("}");
        return result.toString();
    }

    public static void log(int[] message) throws LogSaverException {
        loggerController.log("primitives array: " + arrayMessageToString(message));
    }

    public static void log(char message) throws LogSaverException {
        loggerController.log(new CharCommand(message));
    }

    public static void log(String message) throws LogSaverException {
        loggerController.log(new StringCommand(message));
    }

    public static void log(boolean message) throws LogSaverException {
        loggerController.log(new BooleanCommand(message));
    }

    public static void log(Object message) throws LogSaverException {
        loggerController.log(new ObjectCommand(message));
    }

    public static void flush() throws LogSaverException {
        loggerController.flush();
    }
}
