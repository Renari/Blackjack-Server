import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import packets.Message;
import packets.requests.LoginRequest;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class LoginResponseTest {

    private CountDownLatch lock = new CountDownLatch(1);
    private Message response;

    @Before
    public void setUp() throws IOException {
        //start the server
        BlackjackServer.getInstance();
    }

    @Test
    public void testLoginResponse() throws IOException, InterruptedException {
        Client client = new Client();
        Network.register(client);
        client.start();
        client.connect(Network.TIMEOUT, "127.0.0.1", Network.PORT);


        client.addListener(new Listener() {
            @Override
            public void received(Connection connection, Object o) {
                response = (Message) o;
                lock.countDown();
            }
        });

        client.sendTCP(new LoginRequest("testuser", "testpassword"));

        lock.await(Network.TIMEOUT, TimeUnit.MILLISECONDS);

        Assert.assertNotNull(response);
    }
}
