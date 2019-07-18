package com.acme.edu;

import com.acme.edu.command.*;
import com.acme.edu.saver.LogConsoleSaver;

public class Logger {
    private static String primitivePrefix = "primitive: ";
    private static int sumOfInts = 0;
    private static int sumOfBytes = 0;
    private static int repeatSavedStringNumber = 0;
    private static String lastSavedString = "";
    private static LoggerState state = null;
    private static LoggerController loggerController = new LoggerController(new LogConsoleSaver());

    private static boolean isTypeOverflowed(int maxTypeValue,
                                            int currentTypeSum,
                                            int message,
                                            setMaxTypeValueToTypeSum changeTypeSum) {
        if (maxTypeValue - currentTypeSum >= message) return false;

        changeTypeSum.apply();
//        flush();
        return true;
    }

    public static void log(int message) {
        loggerController.log(new IntCommand(message));
//        int possibleIntLeft = Integer.MAX_VALUE - sumOfInts;
//        if (isTypeOverflowed(Integer.MAX_VALUE, sumOfInts, message,
//                                () -> {sumOfInts = Integer.MAX_VALUE;})) {
//            sumOfInts = message - possibleIntLeft;
//        } else {
//            sumOfInts += message;
//        }
    }

    public static void log(byte message) {
        loggerController.log(new ByteCommand(message));
//        int possibleByteLeft = Byte.MAX_VALUE - sumOfBytes;
//        if (isTypeOverflowed(Byte.MAX_VALUE, sumOfBytes, message,
//                () -> {sumOfBytes = Byte.MAX_VALUE;})) {
//            sumOfBytes = message - possibleByteLeft;
//        } else {
//            sumOfBytes += message;
//        }
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

@FunctionalInterface
interface setMaxTypeValueToTypeSum {
    void apply();
}