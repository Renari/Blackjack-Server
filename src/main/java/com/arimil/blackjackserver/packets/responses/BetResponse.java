package com.arimil.blackjackserver.packets.responses;

import com.arimil.blackjackserver.packets.Message;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;

import java.util.Random;

public class BetResponse extends Message {

    String card;
    String dealerCard;

    public BetResponse() {
        Random random = new Random();
        char[] suits = {'D', 'H', 'C', 'S'};
        char suit = suits[random.nextInt(suits.length)];
        int value = random.nextInt(13) + 1;

        card = suit + String.valueOf(value);

        suit = suits[random.nextInt(suits.length)];
        value = random.nextInt(13) + 1;

        dealerCard = suit + String.valueOf(value);
    }

    @Override
    public boolean Process(Connection c, Listener l) {
        //server does not need to process responses
        return true;
    }
}
