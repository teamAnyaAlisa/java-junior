package com.acme.edu;

public class Logger {
    private static String primitivePrefix = "primitive: ";
    private static int sumOfInts = 0;
    private static int sumOfBytes = 0;
    private static int repeatSavedStringNumber = 0;
    private static String lastSavedString = "";
    private static String state = null;

    private static void printLogToConsole(String message) {
        System.out.println(message);
    }

    private static void isStateChanged(String newState) {
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
        isStateChanged("int");
        int possibleIntLeft = Integer.MAX_VALUE - sumOfInts;
        if (isTypeOverflowed(Integer.MAX_VALUE, sumOfInts, message,
                                () -> {sumOfInts = Integer.MAX_VALUE;})) {
            sumOfInts = message - possibleIntLeft;
        } else {
            sumOfInts += message;
        }
        state = "int";
    }

    public static void log(byte message) {
        isStateChanged("byte");
        int possibleByteLeft = Byte.MAX_VALUE - sumOfBytes;
        if (isTypeOverflowed(Byte.MAX_VALUE, sumOfBytes, message,
                () -> {sumOfBytes = Byte.MAX_VALUE;})) {
            sumOfBytes = message - possibleByteLeft;
        } else {
            sumOfBytes += message;
        }
        state = "byte";
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
        printLogToConsole("primitives array: " + arrayMessageToString(message));
    }

    public static void log(char message) {
        printLogToConsole("char: " + message);
    }

    public static void log(String message) {
        isStateChanged("string");
        state = "string";
        if (lastSavedString.equals("")) {
            lastSavedString = message;
        }else if (!message.equals(lastSavedString)) {
            flush();
            lastSavedString = message;
        }
        repeatSavedStringNumber += 1;
    }

    public static void log(boolean message) {
        printLogToConsole(primitivePrefix + message);
    }

    public static void log(Object message) {
        printLogToConsole("reference: " + message);
    }

    private static void flushInt() {
        printLogToConsole(primitivePrefix + sumOfInts);
        sumOfInts = 0;
    }

    private static void flushByte() {
        printLogToConsole(primitivePrefix + sumOfBytes);
        sumOfBytes = 0;
    }

    private static void flushString() {
        if (repeatSavedStringNumber == 1) {
            printLogToConsole("string: " + lastSavedString);
        } else if (repeatSavedStringNumber > 1) {
            printLogToConsole("string: " + lastSavedString + " (x" + repeatSavedStringNumber + ")");
        }
        lastSavedString = "";
        repeatSavedStringNumber = 0;
    }

    public static void flush() {
        switch (state) {
            case "int": flushInt(); state = null; break;
            case "string": flushString(); state = null; break;
            case "byte": flushByte(); state = null; break;
        }
    }
}

@FunctionalInterface
interface setMaxTypeValueToTypeSum {
    void apply();
}