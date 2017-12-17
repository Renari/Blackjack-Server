package com.arimil.blackjackserver.packets.requests;

import com.arimil.blackjackserver.GameManager;
import com.arimil.blackjackserver.User;
import com.arimil.blackjackserver.UserManager;
import com.arimil.blackjackserver.packets.Message;
import com.arimil.blackjackserver.packets.responses.HoldResponse;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;

public class HoldRequest extends Message {
    @Override
    public boolean Process(Connection c, Listener l) {
        User u = UserManager.getUser(c.getID());
        while (GameManager.getHandValue(u.dealersCards) < 17) {
            String card = GameManager.generateRandomCard();
            u.dealersCards.add(card);
        }
        int userHandValue = GameManager.getHandValue(u.cards);
        int dealerHandValue = GameManager.getHandValue(u.dealersCards);
        String result;
        if (userHandValue > dealerHandValue || dealerHandValue > 21) {
            //win
            u.currency += u.bet;
            result = "Win";
        } else if (userHandValue < dealerHandValue) {
            //lose
            u.currency -= u.bet;
            result = "Lose";
        } else {
            result = "Tie";
        }
        c.sendTCP(new HoldResponse(u.dealersCards.toArray(new String[u.dealersCards.size()]), u.currency, result));
        u.cards.clear();
        u.bet = 0;
        u.dealersCards.clear();
        return true;
    }
}
