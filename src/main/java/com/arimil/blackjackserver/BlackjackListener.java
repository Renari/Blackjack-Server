package com.arimil.blackjackserver;

import com.arimil.blackjackserver.packets.Message;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;

import java.sql.PreparedStatement;
import java.sql.SQLException;

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
        System.out.println("Disconnected from " + connection.getID());
        User u = UserManager.getUser(connection.getID());
        if (u.bet != 0) {
            u.currency -= u.bet;
        }

        String query = "UPDATE users SET currency = ? WHERE username = ?";
        try {
            PreparedStatement updateUser = DatabaseManager.getConnection().prepareStatement(query);
            updateUser.setInt(1, u.currency);
            updateUser.setString(2, u.name);
            updateUser.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        UserManager.removeUser(connection.getID());
    }
}