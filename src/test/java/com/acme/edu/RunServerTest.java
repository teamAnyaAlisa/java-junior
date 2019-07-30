package com.acme.edu;

import com.acme.edu.customExceptions.LogSaverException;
import com.acme.edu.customExceptions.server.LoggerServerException;
import com.acme.edu.network.skeleton.AcceptorConnection;
import com.acme.edu.network.skeleton.LogRequestHandler;
import com.acme.edu.network.skeleton.LoggerServer;
import org.junit.Ignore;
import org.junit.Test;

@Ignore
public class RunServerTest {
    @Test
    public void runServer() throws LoggerServerException, LogSaverException {
        LoggerServer server = new LoggerServer(8080);
        server.establishConnection();
    }
}
