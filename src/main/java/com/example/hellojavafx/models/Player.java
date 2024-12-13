package com.example.hellojavafx.models;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private String name;
    private List<Card> hand;
    private boolean isComputer, isDeleted;

    public Player(String name, boolean isComputer) {
        this.name = name;
        this.hand = new ArrayList<>();
        this.isComputer = isComputer;
        this.isDeleted = false;
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
        return newSum <= 30;
    }

    public void addCardToHand(Card card) {
        hand.add(card);
    }

    public void setDeleted(boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public boolean getIsDeleted() {
        return isDeleted;
    }
}