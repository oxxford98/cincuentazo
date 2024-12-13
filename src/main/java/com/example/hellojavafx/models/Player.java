package com.example.hellojavafx.models;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private String name;
    private List<Card> hand;

    public Player(String name) {
        this.name = name;
        this.hand = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public List<Card> getHand() {
        return hand;
    }

    public void drawCard(Deck deck) {
        if (!deck.isEmpty()) {
            hand.add(deck.drawCard());
        }
    }

    public void playCard(Card card) {
        hand.remove(card);
    }

    public boolean canPlayCard(int currentSum, Card card) {
        int newSum = currentSum + card.getValue();
        return newSum <= 50;
    }
}