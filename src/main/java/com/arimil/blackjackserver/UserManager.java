package com.arimil.blackjackserver;

import java.util.HashMap;
import java.util.Map;

public class UserManager {
    private static Map<Integer, User> userMap = new HashMap<>();

    public static User getUser(int id) {
        return userMap.get(id);
    }

    public static void putUser(int id, User user) {
        userMap.put(id, user);
    }

    public static void removeUser(int id) {

    }

    public static void processWinners(int dealer) {
        for (Map.Entry<Integer, User> entry : userMap.entrySet()){
            User u = entry.getValue();
            int handValue = GameManager.getHandValue(u.cards);
            //winner
            if ((handValue > dealer && handValue <= 21) ||
                    handValue <= 21 && dealer > 21) {
                u.currency += u.bet;
            }
            //lose
            else if (dealer > handValue) {
                u.currency -= u.bet;
            }
            u.bet = 0;
        }
    }
}
