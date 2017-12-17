package com.arimil.blackjackserver.packets.requests;

import com.arimil.blackjackserver.GameManager;
import com.arimil.blackjackserver.User;
import com.arimil.blackjackserver.UserManager;
import com.arimil.blackjackserver.packets.Message;
import com.arimil.blackjackserver.packets.responses.BustResponse;
import com.arimil.blackjackserver.packets.responses.HitResponse;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;

public class HitRequest extends Message {
    @Override
    public boolean Process(Connection c, Listener l) {
        User u = UserManager.getUser(c.getID());

        String card = GameManager.generateRandomCard();
        u.cards.add(card);

        if(GameManager.getHandValue(u.cards) > 21) {
            u.currency -= u.bet;
            u.bet = 0;
            c.sendTCP(new BustResponse(card, u.currency));
        } else {
            c.sendTCP(new HitResponse(card));
        }
        return false;
    }
}
