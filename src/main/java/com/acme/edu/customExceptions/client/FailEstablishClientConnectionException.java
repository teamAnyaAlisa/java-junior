package com.acme.edu.customExceptions.client;

import java.io.IOException;

public class FailEstablishClientConnectionException extends ClientProxyException {
    public FailEstablishClientConnectionException() {
        super();
    }

    public FailEstablishClientConnectionException(String message) {
        super(message);
    }

    public FailEstablishClientConnectionException(String message, Throwable cause) {
        super(message, cause);
    }

    public FailEstablishClientConnectionException(Throwable cause) {
        super(cause);
    }

    protected FailEstablishClientConnectionException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
