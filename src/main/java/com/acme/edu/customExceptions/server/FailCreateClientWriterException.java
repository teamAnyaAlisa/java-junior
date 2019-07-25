package com.acme.edu.customExceptions.server;

import java.io.IOException;

public class FailCreateClientWriterException extends LoggerServerException {
    public FailCreateClientWriterException() {
        super();
    }

    public FailCreateClientWriterException(String message) {
        super(message);
    }

    public FailCreateClientWriterException(String message, Throwable cause) {
        super(message, cause);
    }

    public FailCreateClientWriterException(Throwable cause) {
        super(cause);
    }

    protected FailCreateClientWriterException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
