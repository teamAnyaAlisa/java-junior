package com.acme.edu.saver;

import com.acme.edu.customExceptions.LogSaverException;
import com.acme.edu.saver.LogSaver;

public class LogConsoleSaver implements LogSaver {
    @Override
    public void save(String message){
        System.out.println(message);
    }

    @Override
    public void close() throws LogSaverException {

    }
}
