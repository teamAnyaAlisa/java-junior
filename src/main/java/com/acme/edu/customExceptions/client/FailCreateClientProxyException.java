package com.acme.edu.customExceptions.client;

import java.io.IOException;

public class FailCreateClientProxyException extends ClientProxyException {
    public FailCreateClientProxyException() {
        super();
    }

    public FailCreateClientProxyException(String message) {
        super(message);
    }

    public FailCreateClientProxyException(String message, Throwable cause) {
        super(message, cause);
    }

    public FailCreateClientProxyException(Throwable cause) {
        super(cause);
    }

    protected FailCreateClientProxyException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
