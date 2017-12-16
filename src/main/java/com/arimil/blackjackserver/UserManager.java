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
            //winner
            if ((u.hand > dealer && u.hand <= 21) ||
                    u.hand <= 21 && dealer > 21) {
                u.currency += u.bet;
            }
            //lose
            else if (dealer > u.hand) {
                u.currency -= u.bet;
            }
            u.bet = 0;
        }
    }
}
