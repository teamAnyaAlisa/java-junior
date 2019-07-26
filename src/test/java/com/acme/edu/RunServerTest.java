package com.acme.edu;

import com.acme.edu.customExceptions.LogSaverException;
import com.acme.edu.customExceptions.server.LoggerServerException;
import com.acme.edu.network.skeleton.AcceptorConnection;
import com.acme.edu.network.skeleton.LogRequestHandler;
import com.acme.edu.network.skeleton.LoggerServer;
import org.junit.Test;

public class RunServerTest {
    @Test
    public void runServer() throws LoggerServerException, LogSaverException {
        LoggerServer server = new LoggerServer(new AcceptorConnection(8080), new LogRequestHandler());
        server.logClientMessages();
    }
}
