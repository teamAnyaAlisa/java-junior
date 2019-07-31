package com.acme.edu.network.skeleton;

import com.acme.edu.command.Command;
import com.acme.edu.command.FlushCommand;
import com.acme.edu.customExceptions.LogSaverException;
import com.acme.edu.customExceptions.server.FailEstablishConnectionException;
import com.acme.edu.customExceptions.server.LoggerServerException;
import com.acme.edu.logger.LoggerController;

import java.io.*;
import java.net.Socket;

public class ClientLoggingSession extends Thread {
    private Socket client;
    private LoggerController loggerController;
    private ClientConnectionService clientConnectionService;

    ClientLoggingSession(Socket client, LoggerController loggerController) throws LoggerServerException {
        this.loggerController = loggerController;
        this.client = client;
        clientConnectionService = new ClientConnectionService(client);
    }

    @Override
    public void run() {
        logClientMessages();
    }

    public void logClientMessages() {
        String loggingExecutionStatus = "Message logged successfully";

        try {
            while (!isInterrupted()) {
                String messageClientRepresentation = clientConnectionService.getClientLogMessage();
                Command messageInternalRepresentation = LogRequestHandler.parseClientMessage(messageClientRepresentation);
                try {
                    if (messageInternalRepresentation instanceof FlushCommand) {
                        loggerController.flush();
                    } else {
                        loggerController.log(messageInternalRepresentation);
                    }
                } catch (LogSaverException e) {
                    loggingExecutionStatus = e.getMessage();
                }

                clientConnectionService.passCommandExecutionStatus(loggingExecutionStatus);
                loggingExecutionStatus = "Message logged successfully";
            }
        } catch (Exception e) {
            System.out.println("Client closed connection");
        }

        close();
    }

    public void close() {
        try {
            if (client != null) {
                client.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (clientConnectionService != null) {
            try {
                clientConnectionService.close();
            } catch (LoggerServerException e) {
                e.printStackTrace();
            }
        }
    }
}
