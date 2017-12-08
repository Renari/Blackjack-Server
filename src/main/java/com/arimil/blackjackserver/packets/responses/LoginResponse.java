package com.arimil.blackjackserver.packets.responses;

import com.arimil.blackjackserver.packets.Message;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;

public class LoginResponse extends Message {

    public LoginResponse() {

    }

    @Override
    public boolean Process(Connection c, Listener l) {
        //server does not need to process responses
        return true;
    }
}
