package com.arimil.blackjackserver.packets.requests;

import com.arimil.blackjackserver.User;
import com.arimil.blackjackserver.UserManager;
import com.arimil.blackjackserver.packets.Message;
import com.arimil.blackjackserver.packets.responses.LoginResponse;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;

public class LoginRequest extends Message {
    private String username;
    private String password;

    public LoginRequest() {

    }

    public LoginRequest(String user, String pass) {
        this.username = user;
        this.password = pass;
    }

    @Override
    public boolean Process(Connection c, Listener l) {
        System.out.println("login packet received with details: " + username + ", " + password);
        //TODO: change currency to query after checking username and password
        c.sendTCP(new LoginResponse(50000));
        UserManager.putUser(c.getID(), new User(username, 50000));
        return true;
    }
}
