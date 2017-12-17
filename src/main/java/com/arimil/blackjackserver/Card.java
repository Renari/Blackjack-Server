package com.arimil.blackjackserver;

public class Card {
    public String suit;
    public int value;


    Card(String cardString) {
        switch (cardString.charAt(0)) {
            case 'D':
                suit = "diamonds";
                break;
            case 'H':
                suit = "hearts";
                break;
            case 'C':
                suit = "clubs";
                break;
            case 'S':
                suit = "spades";
                break;
            default:
                return;
        }
        value = Integer.valueOf(cardString.substring(1));
    }

}
