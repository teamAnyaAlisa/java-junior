package com.acme.edu.customExceptions.server;

import java.io.IOException;

public class FailRaiseServerListenerException extends LoggerServerException {
    public FailRaiseServerListenerException() {
        super();
    }

    public FailRaiseServerListenerException(String message) {
        super(message);
    }

    public FailRaiseServerListenerException(String message, Throwable cause) {
        super(message, cause);
    }

    public FailRaiseServerListenerException(Throwable cause) {
        super(cause);
    }

    protected FailRaiseServerListenerException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
