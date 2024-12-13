package com.example.hellojavafx.models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
    private List<Card> cards;
    private List<Card> cardsUsed;

    public Deck() {
        cards = new ArrayList<>();
        cardsUsed = new ArrayList<>();
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
                        value = 1;
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
            addCardUsedToDeck();
        }
        return cards.remove(0);
    }

    public void addCardUsedToDeck() {
        cards.addAll(cardsUsed.subList(0, cardsUsed.size() - 1));
        cardsUsed.clear();
        shuffle();
    }

    public void addCardToUsed(Card card) {
        cardsUsed.add(card);
    }

    public void addCardsToBottom(List<Card> newCards) {
        cards.addAll(newCards);
    }

    public boolean isEmpty() {
        return cards.isEmpty();
    }
}