package com.acme.edu.skeleton;

public class LoggerServer {
    AcceptorConnection acceptorConnection;
    LogRequestHandler logRequestHandler;

    LoggerServer(AcceptorConnection acceptorConnection, LogRequestHandler logRequestHandler) {
        this.acceptorConnection = acceptorConnection;
        this.logRequestHandler = logRequestHandler;
    }
}
