package com.acme.edu;

public class LogConsoleSaver implements LogSaver {
    @Override
    public void save(String message) {
        System.out.println(message);
    }
}
