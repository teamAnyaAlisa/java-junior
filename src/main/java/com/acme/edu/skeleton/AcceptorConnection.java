// отвечает за установку (accept) сетевого соединения, инкапсулирует все до бизнес-логики

package com.acme.edu.skeleton;

import com.acme.edu.customExceptions.server.*;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class AcceptorConnection {
    private int port;
    private ServerSocket serverSocket;
    private Socket client;

    AcceptorConnection(int port) throws LoggerServerException {
        this.port = port;
        try {
            serverSocket = new ServerSocket(this.port);
        } catch (IOException e) {
            e.printStackTrace();
            throw new FailRaiseServerListenerException("something went wrong with establishing server listener", e);
        }
    }

    public void establishConnection() throws LoggerServerException {
        try {
            Socket client = serverSocket.accept();
        } catch (IOException e) {
            e.printStackTrace();
            throw new FailEstablishConnectionException("something went wrong with establishing server listener", e);
        }
    }

    public String getClientLogCommend() throws LoggerServerException {
        try (BufferedReader clientInputStream =
                     new BufferedReader(
                             new InputStreamReader(
                                     new BufferedInputStream(
                                             client.getInputStream())))) {
            return clientInputStream.readLine();
        } catch (IOException e) {
            e.printStackTrace();
            throw new FailCreateClientReaderException("something went wrong with creating client reader", e);
        }
    }

    public void passCommandExecutionStatus(String commandExecutionStatus) throws LoggerServerException {
        try (BufferedWriter clientOutputStream =
                     new BufferedWriter(
                             new OutputStreamWriter(
                                     new BufferedOutputStream(
                                             client.getOutputStream())))) {
            clientOutputStream.write(commandExecutionStatus);
            clientOutputStream.newLine();
            clientOutputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
            throw new FailCreateClientWriterException("something went wrong with creating client writer", e);
        }
    }

    // нормально обработать ошибки при закрытии
    public void close() throws LoggerServerException {
        try {
            client.close();
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
