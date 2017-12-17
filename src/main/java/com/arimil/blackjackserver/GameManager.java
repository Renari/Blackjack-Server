package com.arimil.blackjackserver;

import java.util.ArrayList;
import java.util.Random;

public class GameManager {
    public static ArrayList<String> dealersCards = new ArrayList<>();

    public static String generateRandomCard() {
        Random random = new Random();
        char[] suits = {'D', 'H', 'C', 'S'};
        char suit = suits[random.nextInt(suits.length)];
        int value = random.nextInt(13) + 1;

        return suit + String.valueOf(value);
    }

    public static int getHandValue(ArrayList<String> hand) {
        int aces = 0;
        int handTotal = 0;
        //add up all cards
        for(String cardString : hand) {
            Card card = new Card(cardString);
            if(card.value == 1) {
                aces++;
                handTotal += 11;
            } else if (card.value > 10) {
                handTotal += 10;
            } else {
                handTotal += card.value;
            }
        }

        //if the user would bust from an ace being 11 make it a 1
        for (int i = 0; i < aces; i++) {
            if(handTotal > 21) {
                handTotal -= 10;
            }
        }
        return handTotal;
    }
}
