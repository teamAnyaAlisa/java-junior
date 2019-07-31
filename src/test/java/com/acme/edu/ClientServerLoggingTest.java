package com.acme.edu;

import com.acme.edu.customExceptions.LogSaverException;
import com.acme.edu.customExceptions.client.ClientProxyException;
import com.acme.edu.customExceptions.server.LoggerServerException;
import com.acme.edu.network.client.ClientProxy;
import org.junit.Ignore;
import org.junit.Test;

@Ignore
public class ClientServerLoggingTest {
    @Test
    public void LogMessageFromClientThroughClientProxy() throws LogSaverException, LoggerServerException, ClientProxyException {
        ClientProxy client = new ClientProxy(8080);

        client.log(2);
        String loggingStatus = client.getLogMessageStatus();
        System.out.println(loggingStatus);
        client.close();
    }
}
