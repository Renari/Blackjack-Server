package com.arimil.packets.requests;

import com.arimil.packets.Message;
import com.arimil.packets.response.LoginResponse;
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
