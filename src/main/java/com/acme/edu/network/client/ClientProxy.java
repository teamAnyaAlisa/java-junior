// отвечает за отправку данных

package com.acme.edu.network.client;

import com.acme.edu.customExceptions.client.ClientProxyException;
import com.acme.edu.customExceptions.client.FailCloseClientProxyConnectionException;
import com.acme.edu.customExceptions.client.FailEstablishClientConnectionException;
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
            throw new FailEstablishClientConnectionException("can`t establish client connection to server", e);
        }
    }

    public String getLogMessageStatus() throws ClientProxyException {
        try {
            return in.readLine();
        } catch (IOException e) {
            e.printStackTrace();
            throw new FailPassMessageToServerException("can`t receive message with log status", e);
        }
    }

    public void log(int message) throws ClientProxyException {
        try {
            out.write("int " + message);
            out.newLine();
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
            throw new FailPassMessageToServerException("can`t send int message for logging to server", e);
        }
    }

    public void log(byte message) throws ClientProxyException {
        try {
            out.write("byte " + message);
            out.newLine();
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
            throw new FailPassMessageToServerException("can`t send byte message for logging to server", e);
        }
    }

    public void log(char message) throws ClientProxyException {
        try {
            out.write("char " + message);
            out.newLine();
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
            throw new FailPassMessageToServerException("can`t send char message for logging to server", e);
        }
    }

    public void log(boolean message) throws ClientProxyException {
        try {
            out.write("boolean " + message);
            out.newLine();
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
            throw new FailPassMessageToServerException("can`t send boolean message for logging to server", e);
        }
    }

    public void log(String message) throws ClientProxyException {
        try {
            out.write("string " + message);
            out.newLine();
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
            throw new FailPassMessageToServerException("can`t string message for logging to server", e);
        }
    }

    // TODO: Object, array

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
            in.close();
            server.close();
        } catch (IOException e) {
            e.printStackTrace();
            throw new FailCloseClientProxyConnectionException("can`t close connection to server", e);
        }
    }
}
