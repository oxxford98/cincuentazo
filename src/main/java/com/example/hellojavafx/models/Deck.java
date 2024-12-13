package com.example.hellojavafx.models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
    private List<Card> cards;

    public Deck() {
        cards = new ArrayList<>();
        initializeDeck();
        shuffle();
    }

    private void initializeDeck() {
        String[] ranks = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"};
        String[] suits = {"Clovers", "Hearts", "Spades", "Diamonds"};
        for (String suit : suits) {
            for (String rank : ranks) {
                int value;
                switch (rank) {
                    case "9":
                        value = 0;
                        break;
                    case "J":
                    case "Q":
                    case "K":
                        value = -10;
                        break;
                    case "A":
                        value = 1; // A can be 1 or 10, will handle in game logic
                        break;
                    default:
                        value = Integer.parseInt(rank);
                }
                cards.add(new Card(rank, suit, value));
            }
        }
    }

    public void shuffle() {
        Collections.shuffle(cards);
    }

    public Card drawCard() {
        if (cards.isEmpty()) {
            return null;
        }
        return cards.remove(0);
    }

    public void addCardsToBottom(List<Card> newCards) {
        cards.addAll(newCards);
    }

    public boolean isEmpty() {
        return cards.isEmpty();
    }
}