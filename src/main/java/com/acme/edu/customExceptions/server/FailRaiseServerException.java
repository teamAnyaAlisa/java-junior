package com.acme.edu.customExceptions.server;

import java.io.IOException;

public class FailRaiseServerException extends LoggerServerException {
    public FailRaiseServerException() {
        super();
    }

    public FailRaiseServerException(String message) {
        super(message);
    }

    public FailRaiseServerException(String message, Throwable cause) {
        super(message, cause);
    }

    public FailRaiseServerException(Throwable cause) {
        super(cause);
    }

    protected FailRaiseServerException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
