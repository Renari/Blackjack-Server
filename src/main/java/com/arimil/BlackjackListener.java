package com.arimil;

import com.arimil.packets.Message;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;

public class BlackjackListener extends Listener {
    @Override
    public void received(Connection connection, Object o) {
        //handle message here
        if (o instanceof Message) {
            Message p = (Message) o;
            p.Process(connection);
        }
    }
}