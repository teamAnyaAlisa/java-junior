package com.acme.edu.customExceptions.client;

import java.io.IOException;

public class FailCloseClientProxyConnectionException extends ClientProxyException {
    public FailCloseClientProxyConnectionException() {
        super();
    }

    public FailCloseClientProxyConnectionException(String message) {
        super(message);
    }

    public FailCloseClientProxyConnectionException(String message, Throwable cause) {
        super(message, cause);
    }

    public FailCloseClientProxyConnectionException(Throwable cause) {
        super(cause);
    }

    protected FailCloseClientProxyConnectionException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
