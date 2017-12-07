package com.arimil.blackjackserver.packets.requests;

import com.arimil.blackjackserver.packets.response.LoginResponse;
import com.arimil.blackjackserver.packets.Message;
import com.esotericsoftware.kryonet.Connection;

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
    public boolean Process(Connection c) {
        System.out.println("login packet received with details: " + username + ", " + password);
        c.sendTCP(new LoginResponse());
        return true;
    }
}
