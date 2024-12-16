package com.example.hellojavafx.models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Represents a deck of playing cards.
 */
public class Deck {
    public static Deck instance;
    private List<Card> cards;
    private List<Card> cardsUsed;

    /**
     * Constructs a new Deck and initializes it with cards.
     */
    public Deck() {
        cards = new ArrayList<>();
        cardsUsed = new ArrayList<>();
        initializeDeck();
        shuffle();
    }

    /**
     * Returns the singleton instance of the Deck.
     *
     * @return the singleton instance of the Deck
     */
    public static Deck getInstance() {
        if (instance == null) {
            synchronized (Deck.class) {
                if (instance == null) {
                    instance = new Deck();
                }
            }
        }
        return instance;
    }

    /**
     * Initializes the deck with a standard set of playing cards.
     */
    public void initializeDeck() {
        cards.clear();
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
        System.out.println("Deck initialized with " + cards.size() + " cards.");
    }

    /**
     * Shuffles the deck of cards.
     */
    public void shuffle() {
        Collections.shuffle(cards);
    }

    /**
     * Draws a card from the top of the deck.
     *
     * @return the drawn card
     */
    public Card drawCard() {
        if (cards.isEmpty()) {
            addCardUsedToDeck();
        }
        return cards.remove(0);
    }

    /**
     * Adds used cards back to the deck and shuffles it.
     */
    public void addCardUsedToDeck() {
        cards.addAll(cardsUsed.subList(0, cardsUsed.size() - 1));
        cardsUsed.clear();
        shuffle();
    }

    /**
     * Adds a card to the list of used cards.
     *
     * @param card the card to add to the used cards
     */
    public void addCardToUsed(Card card) {
        cardsUsed.add(card);
    }

    /**
     * Returns the number of remaining cards in the deck.
     *
     * @return the number of remaining cards
     */
    public int getRemainingCards() {
        return cards.size();
    }

    /**
     * Returns the number of used cards.
     *
     * @return the number of used cards
     */
    public int getUsedCards() {
        return cardsUsed.size();
    }

    /**
     * Adds a list of cards to the bottom of the deck.
     *
     * @param newCards the list of cards to add
     */
    public void addCardsToBottom(List<Card> newCards) {
        cards.addAll(newCards);
    }

    /**
     * Checks if the deck is empty.
     *
     * @return true if the deck is empty, false otherwise
     */
    public boolean isEmpty() {
        return cards.isEmpty();
    }
}