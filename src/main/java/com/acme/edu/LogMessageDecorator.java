package com.acme.edu;

public class LogMessageDecorator {
    public String decorate(String message, LoggerState state) {
        switch (state) {
            case INT:
            case BYTE:
            case BOOLEAN:
                return "primitive: " + message;
            case STRING:
                return "string: " + message;
            case OBJECT:
                return "reference: " + message;
            case CHAR:
                return "char: " + message;
            default:
                return message;
        }
    }
}
