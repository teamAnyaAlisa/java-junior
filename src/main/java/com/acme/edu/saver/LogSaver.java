package com.acme.edu.saver;

import com.acme.edu.customExceptions.LogSaverException;

public interface LogSaver {

    void save(String message) throws LogSaverException;

    void close() throws LogSaverException;
}
