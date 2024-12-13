package com.example.hellojavafx.models;

public class Card {
    private String  img;
    private String rank;
    private String suit;
    private int value;

    public Card(String rank, String suit, int value) {
        this.rank = rank;
        this.suit = suit;
        this.value = value;
        if (suit == "Hearts") {
            this.img = "/com/example/hellojavafx/images/"+rank.toLowerCase()+"c.jpg";
        } else if (suit == "Diamonds") {
            this.img = "/com/example/hellojavafx/images/"+rank.toLowerCase()+"d.jpg";
        } else if (suit == "Spades") {
            this.img = "/com/example/hellojavafx/images/"+rank.toLowerCase()+"p.jpg";
        } else {
            this.img = "/com/example/hellojavafx/images/"+rank.toLowerCase()+"t.jpg";
        }
    }

    public String getRank() {
        return rank;
    }

    public String getSuit() {
        return suit;
    }

    public int getValue() {
        return value;
    }

    public String getImg() {
        return img;
    }

    @Override
    public String toString() {
        return rank + " of " + suit;
    }
}