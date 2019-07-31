package com.acme.edu;

import com.acme.edu.customExceptions.LogSaverException;
import com.acme.edu.customExceptions.server.LoggerServerException;
import com.acme.edu.network.skeleton.LoggerServer;
import org.junit.Ignore;
import org.junit.Test;

@Ignore
public class RunServerTest {
    @Test
    public void runServer() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                LoggerServer server = null;
                try {
                    server = new LoggerServer(8080);
                    server.establishConnection();
                } catch (LoggerServerException e) {
                    e.printStackTrace();
                }
            }
        });

        thread.start();

    }
}
