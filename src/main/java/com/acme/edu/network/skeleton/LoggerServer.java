package com.acme.edu.network.skeleton;

import com.acme.edu.command.Command;
import com.acme.edu.command.FlushCommand;
import com.acme.edu.customExceptions.LogSaverException;
import com.acme.edu.customExceptions.server.FailRaiseServerException;
import com.acme.edu.customExceptions.server.LoggerServerException;
import com.acme.edu.logger.LoggerController;
import com.acme.edu.saver.LogConsoleSaver;
import com.acme.edu.saver.LogSaver;

import java.io.IOException;
import java.net.ServerSocket;

public class LoggerServer {
    private AcceptorConnection acceptorConnection;
    private LogRequestHandler logRequestHandler;
    private ServerSocket serverSocket;
    private static LogSaver logSaver = new LogConsoleSaver();
    private static LoggerController loggerController;

    static {
        try {
            loggerController = new LoggerController(logSaver);
        } catch (LogSaverException e) {
            e.printStackTrace();
        }
    }

    public LoggerServer(int port) throws LoggerServerException {
        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            e.printStackTrace();
            throw new FailRaiseServerException("something went wrong with raise server", e);
        }
        this.acceptorConnection = new AcceptorConnection(serverSocket);
        this.logRequestHandler = new LogRequestHandler();
    }

    public void establishConnection() {
        while (true) {
            try {
                this.acceptorConnection.establishConnection();
                logClientMessages();
            } catch (LoggerServerException e) {
                e.printStackTrace();
            }
        }
    }

    public void logClientMessages() {
        String loggingExecutionStatus = "Message logged successfully";

        try {
            while (true) {
                String messageClientRepresentation = acceptorConnection.getClientLogMessage();
                Command messageInternalRepresentation = logRequestHandler.parseClientMessage(messageClientRepresentation);
                try {
                    if (messageInternalRepresentation instanceof FlushCommand) {
                        loggerController.flush();
                    } else {
                        loggerController.log(messageInternalRepresentation);
                    }
                } catch (LogSaverException e) {
                    loggingExecutionStatus = e.getMessage();
                }

                acceptorConnection.passCommandExecutionStatus(loggingExecutionStatus);
                loggingExecutionStatus = "Message logged successfully";
            }
        } catch (Exception e) {
            System.out.println("Client closed connection");
        }
    }

    public void close() throws LogSaverException, LoggerServerException {
        if (acceptorConnection != null) {
            acceptorConnection.close();
        }
        if (loggerController != null) {
            loggerController.close();
        }
    }
}
