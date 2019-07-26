package com.acme.edu.network.skeleton;

import com.acme.edu.command.Command;
import com.acme.edu.customExceptions.LogSaverException;
import com.acme.edu.customExceptions.NullSaverException;
import com.acme.edu.customExceptions.server.LoggerServerException;
import com.acme.edu.logger.LoggerController;
import com.acme.edu.saver.LogConsoleSaver;
import com.acme.edu.saver.LogSaver;

public class LoggerServer {
    private AcceptorConnection acceptorConnection;
    private LogRequestHandler logRequestHandler;
    private static LogSaver logConsoleSaver = new LogConsoleSaver();
    private static LoggerController loggerController;

    static {
        try {
            loggerController = new LoggerController(logConsoleSaver);
        } catch (NullSaverException e) {
            e.printStackTrace();
        }
    }

    public LoggerServer(AcceptorConnection acceptorConnection, LogRequestHandler logRequestHandler) throws LoggerServerException {
        this.acceptorConnection = acceptorConnection;
        this.logRequestHandler = logRequestHandler;
        this.acceptorConnection.establishConnection();
    }

    public void logClientMessages() throws LoggerServerException {
        String loggingExecutionStatus = "Message logged successfully";

        while (true) {
            try {
                String messageClientRepresentation = acceptorConnection.getClientLogMessage();
                // System.out.println(messageClientRepresentation);
                Command messageInternalRepresentation = logRequestHandler.parseClientMessage(messageClientRepresentation);
                try {
                    loggerController.log(messageInternalRepresentation);
                } catch (LogSaverException e) {
                    loggingExecutionStatus = e.getMessage();
                }

                acceptorConnection.passCommandExecutionStatus(loggingExecutionStatus);
                loggingExecutionStatus = "Message logged successfully";

            } catch (Exception e) {
                System.out.println("Client closed connection");
                break;
            }
        }
    }
}
