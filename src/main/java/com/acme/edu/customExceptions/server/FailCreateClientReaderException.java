package com.acme.edu.customExceptions.server;

public class FailCreateClientReaderException extends LoggerServerException {
    public FailCreateClientReaderException() {
        super();
    }

    public FailCreateClientReaderException(String message) {
        super(message);
    }

    public FailCreateClientReaderException(String message, Throwable cause) {
        super(message, cause);
    }

    public FailCreateClientReaderException(Throwable cause) {
        super(cause);
    }

    protected FailCreateClientReaderException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
