package com.acme.edu.logger;

import com.acme.edu.command.*;
import com.acme.edu.saver.LogConsoleSaver;
import com.acme.edu.saver.LogSaver;

public class Logger {
    private static LogSaver logConsoleSaver = new LogConsoleSaver();
    private static LoggerController loggerController = new LoggerController(logConsoleSaver);

    public static void log(int message) {
        loggerController.log(new IntCommand(message, logConsoleSaver));
    }

    public static void log(byte message) {
        loggerController.log(new ByteCommand(message, logConsoleSaver));
    }

    private static String arrayMessageToString(int[] message) {
        StringBuilder result = new StringBuilder("{");
        for (int index = 0; index < message.length-1; index++) {
            result.append(String.valueOf(message[index])).append(", ");
        }
        result.append(message[message.length-1]).append("}");
        return result.toString();
    }

    public static void log(int[] message) {
        loggerController.log("primitives array: " + arrayMessageToString(message));
    }

    public static void log(char message) {
        loggerController.log(new CharCommand(message, logConsoleSaver));
    }

    public static void log(String message) {
        loggerController.log(new StringCommand(message, logConsoleSaver));
    }

    public static void log(boolean message) {
        loggerController.log(new BooleanCommand(message, logConsoleSaver));
    }

    public static void log(Object message) {
        loggerController.log(new ObjectCommand(message, logConsoleSaver));
    }

    public static void flush() {
        loggerController.flush();
    }
}
