// отвечает за отправку данных

package com.acme.edu.network.client;

import com.acme.edu.customExceptions.client.ClientProxyException;
import com.acme.edu.customExceptions.client.FailCloseClientProxyConnectionException;
import com.acme.edu.customExceptions.client.FailCreateClientProxyException;
import com.acme.edu.customExceptions.client.FailPassMessageToServerException;

import java.io.*;
import java.net.Socket;

public class ClientProxy {
    private Socket server;
    private BufferedWriter out;
    private BufferedReader in;

    public ClientProxy(int port) throws ClientProxyException {
        try {
            server = new Socket("localhost", port);

            try {
                out = new BufferedWriter(
                          new OutputStreamWriter(
                              new BufferedOutputStream(
                                  server.getOutputStream())));

                in = new BufferedReader(
                        new InputStreamReader(
                                new BufferedInputStream(
                                        server.getInputStream())));
            } catch (IOException e) {
                e.printStackTrace();
                throw new FailCreateClientProxyException("something went wrong with creating client writer", e);
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new FailCreateClientProxyException("can`t create connection with server", e);
        }
    }

    public <T> void log(T message) throws ClientProxyException {
        try {
            out.write(message.getClass().getName() + " " + message);
            out.newLine();
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
            throw new FailPassMessageToServerException("can`t send message for logging to server", e);
        }
    }

    public String getLogMessageStatus() throws ClientProxyException {
        try {
            return in.readLine();
        } catch (IOException e) {
            e.printStackTrace();
            throw new FailPassMessageToServerException("can`t send message for logging to server", e);
        }
    }

    public void log(int message) throws ClientProxyException {
        try {
            out.write("int " + message);
            out.newLine();
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
            throw new FailPassMessageToServerException("can`t send message for logging to server", e);
        }
    }

    // TODO add non primitive types handlers

    public void flush() throws ClientProxyException {
        try {
            out.write("flush");
            out.newLine();
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
            throw new FailPassMessageToServerException("can`t send flush command to server", e);
        }
    }

    public void close() throws ClientProxyException {
        try {
            out.close();
            server.close();
        } catch (IOException e) {
            e.printStackTrace();
            throw new FailCloseClientProxyConnectionException("can`t close connection to server", e);
        }
    }
}
