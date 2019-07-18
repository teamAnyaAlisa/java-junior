package com.acme.edu;

public class Logger {
    private static String primitivePrefix = "primitive: ";
    private static int sumOfInts = 0;
    private static int sumOfBytes = 0;
    private static int repeatSavedStringNumber = 0;
    private static String lastSavedString = "";
    private static LoggerState state = null;
    private static LoggerController loggerController = new LoggerController(new LogConsoleSaver());

    private static void isStateChanged(LoggerState newState) {
        if (state != null && !state.equals(newState)) {
            flush();
        }
    }

    private static boolean isTypeOverflowed(int maxTypeValue,
                                            int currentTypeSum,
                                            int message,
                                            setMaxTypeValueToTypeSum changeTypeSum) {
        if (maxTypeValue - currentTypeSum >= message) return false;

        changeTypeSum.apply();
        flush();
        return true;
    }

    public static void log(int message) {
        isStateChanged(LoggerState.INT);
        int possibleIntLeft = Integer.MAX_VALUE - sumOfInts;
        if (isTypeOverflowed(Integer.MAX_VALUE, sumOfInts, message,
                                () -> {sumOfInts = Integer.MAX_VALUE;})) {
            sumOfInts = message - possibleIntLeft;
        } else {
            sumOfInts += message;
        }
        state = LoggerState.INT;
    }

    public static void log(byte message) {
        isStateChanged(LoggerState.BYTE);
        int possibleByteLeft = Byte.MAX_VALUE - sumOfBytes;
        if (isTypeOverflowed(Byte.MAX_VALUE, sumOfBytes, message,
                () -> {sumOfBytes = Byte.MAX_VALUE;})) {
            sumOfBytes = message - possibleByteLeft;
        } else {
            sumOfBytes += message;
        }
        state = LoggerState.BYTE;
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
        loggerController.log("char: " + message);
    }

    public static void log(String message) {
        isStateChanged(LoggerState.STRING);
        state = LoggerState.STRING;
        if (lastSavedString.equals("")) {
            lastSavedString = message;
        }else if (!message.equals(lastSavedString)) {
            flush();
            lastSavedString = message;
        }
        repeatSavedStringNumber += 1;
    }

    public static void log(boolean message) {
        loggerController.log(primitivePrefix + message);
    }

    public static void log(Object message) {
        loggerController.log("reference: " + message);
    }

    private static void flushInt() {
        loggerController.log(primitivePrefix + sumOfInts);
        sumOfInts = 0;
    }

    private static void flushByte() {
        loggerController.log(primitivePrefix + sumOfBytes);
        sumOfBytes = 0;
    }

    private static void flushString() {
        if (repeatSavedStringNumber == 1) {
            loggerController.log("string: " + lastSavedString);
        } else if (repeatSavedStringNumber > 1) {
            loggerController.log("string: " + lastSavedString + " (x" + repeatSavedStringNumber + ")");
        }
        lastSavedString = "";
        repeatSavedStringNumber = 0;
    }

    public static void flush() {
        switch (state) {
            case INT: flushInt(); state = null; break;
            case STRING: flushString(); state = null; break;
            case BYTE: flushByte(); state = null; break;
        }
    }
}

@FunctionalInterface
interface setMaxTypeValueToTypeSum {
    void apply();
}