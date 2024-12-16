package com.example.hellojavafx.models;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a player in the game.
 */
public class Player implements Runnable {
    private String name;
    private List<Card> hand;
    private boolean isComputer, isDeleted;

    /**
     * Constructs a new Player with the specified name and type.
     *
     * @param name the name of the player
     * @param isComputer true if the player is a computer, false otherwise
     */
    public Player(String name, boolean isComputer) {
        this.name = name;
        this.hand = new ArrayList<>();
        this.isComputer = isComputer;
        this.isDeleted = false;
    }

    /**
     * Returns the name of the player.
     *
     * @return the name of the player
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the hand of the player.
     *
     * @return the hand of the player
     */
    public List<Card> getHand() {
        return hand;
    }

    /**
     * Draws a card from the deck and adds it to the player's hand.
     *
     * @param deck the deck to draw a card from
     */
    public void drawCard(Deck deck) {
        hand.add(deck.drawCard());
    }

    /**
     * Plays a card from the player's hand.
     *
     * @param card the card to play
     */
    public void playCard(Card card) {
        hand.remove(card);
    }

    /**
     * Checks if the player can play the specified card.
     *
     * @param currentSum the current sum of card values
     * @param card the card to check
     * @return true if the player can play the card, false otherwise
     */
    public boolean canPlayCard(int currentSum, Card card) {
        int newSum = currentSum + card.getValue();
        return newSum <= 50;
    }

    /**
     * Adds a card to the player's hand.
     *
     * @param card the card to add
     */
    public void addCardToHand(Card card) {
        hand.add(card);
    }

    /**
     * Sets the deleted status of the player.
     *
     * @param isDeleted true to mark the player as deleted, false otherwise
     */
    public void setDeleted(boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    /**
     * Returns the deleted status of the player.
     *
     * @return true if the player is deleted, false otherwise
     */
    public boolean getIsDeleted() {
        return isDeleted;
    }

    /**
     * Runs the player's actions in a separate thread.
     */
    @Override
    public void run() {
        while (!isDeleted) {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}