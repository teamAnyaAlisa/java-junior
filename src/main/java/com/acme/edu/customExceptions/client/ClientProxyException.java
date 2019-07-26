package com.acme.edu.customExceptions.client;

public class ClientProxyException extends Exception {
    public ClientProxyException() {
        super();
    }

    public ClientProxyException(String message) {
        super(message);
    }

    public ClientProxyException(String message, Throwable cause) {
        super(message, cause);
    }

    public ClientProxyException(Throwable cause) {
        super(cause);
    }

    protected ClientProxyException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
