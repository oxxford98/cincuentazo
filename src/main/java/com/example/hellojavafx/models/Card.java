package com.example.hellojavafx.models;

/**
 * Represents a playing card with a rank, suit, value, and image.
 */
public class Card {
    private String img;
    private String rank;
    private String suit;
    private int value;

    /**
     * Constructs a new Card with the specified rank, suit, and value.
     *
     * @param rank  the rank of the card (e.g., Ace, King, Queen)
     * @param suit  the suit of the card (e.g., Hearts, Diamonds)
     * @param value the value of the card (e.g., 1 for Ace)
     */
    public Card(String rank, String suit, int value) {
        this.rank = rank;
        this.suit = suit;
        this.value = value;
        if (suit == "Hearts") {
            this.img = "/com/example/hellojavafx/images/" + rank.toLowerCase() + "c.jpg";
        } else if (suit == "Diamonds") {
            this.img = "/com/example/hellojavafx/images/" + rank.toLowerCase() + "d.jpg";
        } else if (suit == "Spades") {
            this.img = "/com/example/hellojavafx/images/" + rank.toLowerCase() + "p.jpg";
        } else {
            this.img = "/com/example/hellojavafx/images/" + rank.toLowerCase() + "t.jpg";
        }
    }

    /**
     * Returns the rank of the card.
     *
     * @return the rank of the card
     */
    public String getRank() {
        return rank;
    }

    /**
     * Returns the suit of the card.
     *
     * @return the suit of the card
     */
    public String getSuit() {
        return suit;
    }

    /**
     * Returns the value of the card.
     *
     * @return the value of the card
     */
    public int getValue() {
        return value;
    }

    /**
     * Returns the image path of the card.
     *
     * @return the image path of the card
     */
    public String getImg() {
        return img;
    }

    /**
     * Returns a string representation of the card.
     *
     * @return a string representation of the card in the format "rank of suit"
     */
    @Override
    public String toString() {
        return rank + " of " + suit;
    }
}