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

    public static void log(int message) {
        if (state != null && state != "int") {
            flush();
        }
        state = "int";
        if (Integer.MAX_VALUE - sumOfInts < message) {
            int intLeft = Integer.MAX_VALUE - sumOfInts;
            sumOfInts = Integer.MAX_VALUE;
            flush();
            state = "int";
            sumOfInts = message - intLeft;
        } else {
            sumOfInts += message;
        }
    }

    public static void log(byte message) {
        if (state != null && state != "byte") {
            flush();
        }
        state = "byte";
        if (Byte.MAX_VALUE - sumOfBytes < message) {
            int byteLeft = Byte.MAX_VALUE - sumOfBytes;
            sumOfBytes = Byte.MAX_VALUE;
            flush();
            state = "byte";
            sumOfBytes = message - byteLeft;
        } else {
            sumOfBytes += message;
        }
    }

    public static void log(char message) {
        printLogToConsole("char: " + message);
    }

    public static void log(String message) {
        if (state != null && state != "string") {
            flush();
        }
        state = "string";
        if (lastSavedString == "") {
            lastSavedString = message;
        }else if (message != lastSavedString) {
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
