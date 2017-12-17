package com.arimil.blackjackserver.packets.responses;

import com.arimil.blackjackserver.packets.Message;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;

public class HitResponse extends Message {

    String card;

    HitResponse() {

    }

    public HitResponse(String card) {
        this.card = card;
    }

    @Override
    public boolean Process(Connection c, Listener l) {
        return false;
    }
}
