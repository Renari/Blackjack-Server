package com.arimil;

import com.arimil.BlackjackServer;
import com.arimil.packets.Message;
import com.arimil.packets.requests.LoginRequest;
import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.minlog.Log;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class LoginRequestTest {

    private CountDownLatch lock = new CountDownLatch(1);
    private Boolean result;

    @Before
    public void setUp() {
        Log.set(Log.LEVEL_TRACE);
        BlackjackServer.getInstance();
    }

    @Test
    public void testLoginResponse() throws IOException, InterruptedException {
        Client client = new Client();
        client.addListener(new TestListener());
        Network.register(client);
        client.start();
        client.connect(Network.TIMEOUT, "127.0.0.1", Network.PORT);
        client.sendTCP(new LoginRequest("testuser", "testpassword"));

        lock.await(Network.TIMEOUT, TimeUnit.MILLISECONDS);

        Assert.assertNotNull(result);
        Assert.assertEquals(result, true);
    }

    private class TestListener extends BlackjackListener {
        @Override
        public void received(Connection connection, Object o) {
            result = ((Message) o).Process(connection);
            lock.countDown();
        }
    }
}
