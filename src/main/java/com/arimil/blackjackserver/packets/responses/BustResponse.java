package com.arimil.blackjackserver.packets.responses;


import com.arimil.blackjackserver.packets.Message;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;

public class BustResponse extends Message {
    String card;
    int currency;

    public BustResponse() {

    }

    public BustResponse(String card, int currency) {
        this.card = card;
    }

    @Override
    public boolean Process(Connection c, Listener l) {
        return false;
    }
}