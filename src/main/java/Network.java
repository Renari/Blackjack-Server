import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.EndPoint;
import com.esotericsoftware.minlog.Log;
import packets.requests.LoginRequest;
import packets.response.LoginResponse;

class Network {
    static final int PORT = 50236;
    static final int TIMEOUT = 15000;

    static void register(EndPoint endpoint) {
        Log.debug("Registering classes");

        Kryo kryo = endpoint.getKryo();
        // Register all requests
        kryo.register(LoginRequest.class);

        // Register all responses
        kryo.register(LoginResponse.class);
    }
}
