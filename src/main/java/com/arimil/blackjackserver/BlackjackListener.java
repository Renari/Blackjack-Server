package com.arimil.blackjackserver;

import com.arimil.blackjackserver.packets.Message;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;

public class BlackjackListener extends Listener {
    @Override
    public void connected(Connection connection) {
        super.connected(connection);
        System.out.println("Connection received from " + connection.getRemoteAddressTCP().toString());
    }

    @Override
    public void received(Connection connection, Object o) {
        //handle message here
        if (o instanceof Message) {
            Message p = (Message) o;
            p.Process(connection, this);
        }
    }

    @Override
    public void disconnected(Connection connection) {
        super.disconnected(connection);
        User u = UserManager.getUser(connection.getID());
        if (u.bet != 0) {
            u.currency -= u.bet;
        }
        //TODO: query to save user details
        UserManager.removeUser(connection.getID());
    }
}