package com.acme.edu;
/*
public class LoggerState {
    private static int sumOfInts = 0;
    private static int sumOfBytes = 0;
    private static int repeatSavedStringNumber = 0;
    private static String lastSavedString = "";
    private static LoggerStateEnum state = null;

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

*/

public enum LoggerState {
    INT, BYTE, STRING, BOOLEAN, OBJECT, CHAR
}
