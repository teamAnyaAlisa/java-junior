package com.acme.edu.customExceptions.client;

import java.io.IOException;

public class FailPassMessageToServerException extends ClientProxyException {
    public FailPassMessageToServerException() {
        super();
    }

    public FailPassMessageToServerException(String message) {
        super(message);
    }

    public FailPassMessageToServerException(String message, Throwable cause) {
        super(message, cause);
    }

    public FailPassMessageToServerException(Throwable cause) {
        super(cause);
    }

    protected FailPassMessageToServerException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
