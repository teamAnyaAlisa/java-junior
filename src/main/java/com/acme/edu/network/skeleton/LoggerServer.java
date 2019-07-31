package com.acme.edu.network.skeleton;

import com.acme.edu.customExceptions.LogSaverException;
import com.acme.edu.customExceptions.server.FailRaiseServerException;
import com.acme.edu.customExceptions.server.LoggerServerException;
import com.acme.edu.logger.LoggerController;
import com.acme.edu.saver.LogConsoleSaver;
import com.acme.edu.saver.LogFileSaver;
import com.acme.edu.saver.LogSaver;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class LoggerServer {
    private ServerSocket serverSocket;

    private static LogSaver logSaver;
    private static LoggerController loggerController;

    static {
        try {
            logSaver = new LogFileSaver("test.txt");
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
    }

    public void establishConnection() {
        while (true) {
            try {
                Socket client = serverSocket.accept();
                ClientLoggingSession clientLoggingSession = new ClientLoggingSession(client, loggerController);
                clientLoggingSession.start();
            } catch (LoggerServerException | IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void close() {
        try {
            if (serverSocket != null) {
                serverSocket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            if (loggerController != null) {
                loggerController.close();
            }
        } catch (LogSaverException e) {
            e.printStackTrace();
        }
    }
}
