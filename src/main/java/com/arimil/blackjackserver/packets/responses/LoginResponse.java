package com.arimil.blackjackserver.packets.responses;

import com.arimil.blackjackserver.packets.Message;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;

public class LoginResponse extends Message {

    //TODO: query database for users currency
    int currency;
    String errors = "";

    public LoginResponse() {

    }

    public LoginResponse(int currency) {
        this.currency = currency;
    }
    
    public LoginResponse(String errors) {
        this.errors = errors;
    }

    @Override
    public boolean Process(Connection c, Listener l) {
        //server does not need to process responses
        return true;
    }
}
