import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;
import com.esotericsoftware.minlog.Log;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static junit.framework.TestCase.fail;

public class TestMessage {
    private Server server;
    private Client client;
    private String response;
    private CountDownLatch lock = new CountDownLatch(1);

    @Before
    public void setUp() {
        Log.set(Log.LEVEL_DEBUG);

        server = new Server();
        try {
            Log.debug("Binding to port: " + 30454 + "... ");
            server.bind(30454);
            Log.debug("Bound to port" + 30454);
        } catch (IOException e) {
            Log.debug("failed");
            Log.error("Unable to bind to port: " + e.getMessage());
            server.stop();
            fail("Unable to start server");
        }
        Kryo kryo = server.getKryo();
        kryo.register(Message.class);
        Log.debug("Adding server listener");
        server.addListener(new TestListener());
        Log.debug("Starting server... ");
        server.start();
        Log.debug("Server started successfully");
    }

    @Test
    public void testPacketSending() throws IOException, InterruptedException {
        client = new Client();
        client.addListener(new TestListener());
        Kryo kryo = client.getKryo();
        kryo.register(Message.class);
        client.start();
        client.connect(5000, "127.0.0.1", 30454);

        client.sendTCP(new Message("RECEIVED"));

        lock.await(5000, TimeUnit.MILLISECONDS);

        Assert.assertNotNull(response);
    }

    private class TestListener extends Listener {
        @Override
        public void received(Connection connection, Object o) {
            Message m = (Message) o;
            response = m.message;
            Log.debug(m.message);
            lock.countDown();
        }
    }

    private class Message {
        String message;

        Message(String message) {
            this.message = message;
        }
    }
}
