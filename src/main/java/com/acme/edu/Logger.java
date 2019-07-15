package com.acme.edu;

public class Logger {
    private static String primitivePrefix = "primitive: ";

    private static void logPrint(String message) {
        System.out.println(message);
    }

    public static void log(int message) {
        logPrint(primitivePrefix + message);
    }

    public static void log(byte message) {
        logPrint(primitivePrefix + message);
    }

    public static void log(char message) {
        logPrint("char: " + message);
    }

    public static void log(String message) {
        logPrint("string: " + message);
    }

    public static void log(boolean message) {
        logPrint(primitivePrefix + message);
    }

    public static void log(Object message) {
        logPrint("reference: " + message);
    }
}
