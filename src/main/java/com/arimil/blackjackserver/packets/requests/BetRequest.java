package com.arimil.blackjackserver.packets.requests;

import com.arimil.blackjackserver.GameManager;
import com.arimil.blackjackserver.User;
import com.arimil.blackjackserver.UserManager;
import com.arimil.blackjackserver.packets.Message;
import com.arimil.blackjackserver.packets.responses.BetResponse;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;


public class BetRequest extends Message {

    public int amount = 0;

    BetRequest() {

    }

    public BetRequest(int amount) {
        this.amount = amount;
    }

    @Override
    public boolean Process(Connection c, Listener l) {
        User u = UserManager.getUser(c.getID());
        //they bet more money than they have, ignore it
        if (u.currency < amount)
            return false;
        u.bet = amount;

        String card = GameManager.generateRandomCard();
        u.cards.add(card);

        String dealerCard = GameManager.generateRandomCard();
        GameManager.dealersCards.add(dealerCard);

        c.sendTCP(new BetResponse(card, dealerCard));
        return true;
    }
}