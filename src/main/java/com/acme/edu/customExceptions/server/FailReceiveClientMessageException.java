package com.acme.edu.customExceptions.server;

public class FailReceiveClientMessageException extends LoggerServerException {
    public FailReceiveClientMessageException() {
        super();
    }

    public FailReceiveClientMessageException(String message) {
        super(message);
    }

    public FailReceiveClientMessageException(String message, Throwable cause) {
        super(message, cause);
    }

    public FailReceiveClientMessageException(Throwable cause) {
        super(cause);
    }

    protected FailReceiveClientMessageException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
