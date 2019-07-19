package com.acme.edu.logger;

import com.acme.edu.command.*;
import com.acme.edu.saver.LogConsoleSaver;

public class Logger {
    private static LoggerController loggerController = new LoggerController(new LogConsoleSaver());

    public static void log(int message) {
        loggerController.log(new IntCommand(message));
    }

    public static void log(byte message) {
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

    public static void log(int[] message) {
        loggerController.log("primitives array: " + arrayMessageToString(message));
    }

    public static void log(char message) {
        loggerController.log(new CharCommand(message));
    }

    public static void log(String message) {
        loggerController.log(new StringCommand(message));
    }

    public static void log(boolean message) {
        loggerController.log(new BooleanCommand(message));
    }

    public static void log(Object message) {
        loggerController.log(new ObjectCommand(message));
    }

    public static void flush() {
        loggerController.flush();
    }
}
