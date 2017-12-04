package packets;

import com.esotericsoftware.kryonet.Connection;

public abstract class Message {
    public abstract boolean Process(Connection c);
}
