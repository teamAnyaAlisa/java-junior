// отвечает за установку (accept) сетевого соединения, инкапсулирует все до бизнес-логики

package com.acme.edu.network.skeleton;

import com.acme.edu.customExceptions.server.*;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ClientConnectionService {
    private int port;
    private Socket client;
    private BufferedReader clientInputStream;
    private BufferedWriter clientOutputStream;

    public ClientConnectionService(Socket client) throws LoggerServerException {
        try {
            clientInputStream = new BufferedReader(
                    new InputStreamReader(
                            new BufferedInputStream(
                                    client.getInputStream())));
            clientOutputStream = new BufferedWriter(
                    new OutputStreamWriter(
                            new BufferedOutputStream(
                                    client.getOutputStream())));
        } catch (IOException e) {
            e.printStackTrace();
            close();
            throw new FailEstablishConnectionException("can`t establish server connection to client", e);
        }
    }

    public String getClientLogMessage() throws LoggerServerException {
        try {
            return clientInputStream.readLine();
        } catch (IOException e) {
            e.printStackTrace();
            throw new FailReceiveClientMessageException("can`t read client message", e);
        }
    }

    public void passCommandExecutionStatus(String commandExecutionStatus) throws LoggerServerException {
        try {
            clientOutputStream.write(commandExecutionStatus);
            clientOutputStream.newLine();
            clientOutputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
            throw new FailSendLogStatusToClientException("can`t send log execution status to client", e);
        }
    }

    public void close() throws LoggerServerException {
        try {
            if (clientOutputStream != null) {
                clientOutputStream.close();
            }
            if (clientInputStream != null) {
                clientInputStream.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new ShutdownServerException("can`t shutdown server", e);
        }
    }
}
