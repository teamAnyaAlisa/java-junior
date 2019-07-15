package com.acme.edu;

public class Logger {
    private static String primitivePrefix = "primitive: ";
    private static int sumOfInts = 0;

    private static void printLogToConsole(String message) {
        System.out.println(message);
    }

    public static void log(int message) {
        sumOfInts += message;
    }

    public static void log(byte message) {
        printLogToConsole(primitivePrefix + message);
    }

    public static void log(char message) {
        printLogToConsole("char: " + message);
    }

    public static void log(String message) {
        printLogToConsole("string: " + message);
    }

    public static void log(boolean message) {
        printLogToConsole(primitivePrefix + message);
    }

    public static void log(Object message) {
        printLogToConsole("reference: " + message);
    }

    public static void flush() {
        printLogToConsole(primitivePrefix + sumOfInts);
        sumOfInts = 0;
    }
}
