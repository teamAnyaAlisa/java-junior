package com.acme.edu;

import com.acme.edu.customExceptions.client.ClientProxyException;
import com.acme.edu.customExceptions.server.LoggerServerException;
import com.acme.edu.network.client.ClientProxy;
import com.acme.edu.network.skeleton.LoggerServer;
import org.junit.Before;
import org.junit.Test;

public class ClientServerMultithreadingTest {
    @Before
    public void setUp() throws LoggerServerException {
        LoggerServer server = new LoggerServer(8080);
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                server.establishConnection();
            }
        });

        thread.start();
    }

    @Test
    public void LogOneMessageFromOneClient() throws ClientProxyException {

        ClientProxy client = new ClientProxy(8080);

        client.log(2);
        String loggingStatus = client.getLogMessageStatus();
        System.out.println(loggingStatus);
        client.close();
    }

    @Test
    public void LogThreeMessagesFromOneClient() throws ClientProxyException {
       ClientProxy client = new ClientProxy(8080);

        client.log(2);
        String loggingStatus = client.getLogMessageStatus();
        System.out.println(loggingStatus);
        client.log('a');
        loggingStatus = client.getLogMessageStatus();
        System.out.println(loggingStatus);
        client.log("test string");
        loggingStatus = client.getLogMessageStatus();
        System.out.println(loggingStatus);
        client.flush();
        client.close();
    }

    @Test
    public void LogThreeMessagesFromTwoClients() throws ClientProxyException {
        String loggingStatus;

        ClientProxy client1 = new ClientProxy(8080);
        ClientProxy client2 = new ClientProxy(8080);


        client1.log(2);
        loggingStatus = client1.getLogMessageStatus();
        System.out.println(loggingStatus);

        client1.log('a');
        loggingStatus = client1.getLogMessageStatus();
        System.out.println(loggingStatus);

        client2.log(20);
        loggingStatus = client2.getLogMessageStatus();
        System.out.println(loggingStatus);

        client2.log((byte)10);
        loggingStatus = client2.getLogMessageStatus();
        System.out.println(loggingStatus);

        client1.log("test string 1");
        loggingStatus = client1.getLogMessageStatus();
        System.out.println(loggingStatus);

        client2.log("test string 2");
        loggingStatus = client2.getLogMessageStatus();
        System.out.println(loggingStatus);

        client1.flush();
        loggingStatus = client1.getLogMessageStatus();
        System.out.println(loggingStatus);

        client2.flush();
        loggingStatus = client2.getLogMessageStatus();
        System.out.println(loggingStatus);

        client1.close();
        client2.close();
    }

    @Test
    public void CreateFiveClients() throws ClientProxyException {
        ClientProxy client1 = new ClientProxy(8080);
        ClientProxy client2 = new ClientProxy(8080);
        ClientProxy client3 = new ClientProxy(8080);
        ClientProxy client4 = new ClientProxy(8080);
        ClientProxy client5 = new ClientProxy(8080);

        client1.close();
        client2.close();
        client3.close();
        client4.close();
        client5.close();
    }
}
