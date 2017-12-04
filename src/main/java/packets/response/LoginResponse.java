package packets.response;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.minlog.Log;
import packets.Message;

public class LoginResponse extends Message {

    public LoginResponse() {

    }

    @Override
    public boolean Process(Connection c) {
        Log.info("Login processed");
        return true;
    }
}
