package com.arimil.blackjackserver;

import java.util.ArrayList;

public class User {
    public String name;
    public int currency;
    public int bet;
    public int hand;
    public ArrayList<String> cards = new ArrayList<>();
    public ArrayList<String> dealersCards = new ArrayList<>();

    public User(String n, int c) {
        name = n;
        currency = c;
    }
}
