package com.acme.edu.customExceptions.server;

import java.io.IOException;

public class FailSendLogStatusToClientException extends LoggerServerException {
    public FailSendLogStatusToClientException() {
        super();
    }

    public FailSendLogStatusToClientException(String message) {
        super(message);
    }

    public FailSendLogStatusToClientException(String message, Throwable cause) {
        super(message, cause);
    }

    public FailSendLogStatusToClientException(Throwable cause) {
        super(cause);
    }

    protected FailSendLogStatusToClientException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
