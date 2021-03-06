package com.arimil.blackjackserver.packets.responses;

import com.arimil.blackjackserver.packets.Message;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;

public class BetResponse extends Message {

    String card;
    String dealerCard;

    public BetResponse() {

    }

    public BetResponse(String card, String dealerCard) {
        this.card = card;
        this.dealerCard = dealerCard;
    }
    @Override
    public boolean Process(Connection c, Listener l) {
        //server does not need to process responses
        return true;
    }
}
