package com.arimil.packets.response;

import com.arimil.packets.Message;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.minlog.Log;

public class LoginResponse extends Message {

    public LoginResponse() {

    }

    @Override
    public boolean Process(Connection c) {
        Log.info("Login processed");
        return true;
    }
}
