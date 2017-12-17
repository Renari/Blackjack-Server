package com.arimil.blackjackserver.packets.responses;

import com.arimil.blackjackserver.packets.Message;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;

public class HoldResponse extends Message {
    String[] cards;
    int currency;
    String result;

    public HoldResponse() {

    }

    public HoldResponse(String[] dealer, int currency, String result) {
        this.cards = dealer;
        this.currency = currency;
        this.result = result;
    }

    @Override
    public boolean Process(Connection c, Listener l) {
        return false;
    }
}