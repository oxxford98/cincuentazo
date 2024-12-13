package com.example.hellojavafx.models;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private Deck deck;
    private List<Player> players;
    private int currentSum;
    private int currentPlayerIndex;

    public Game(int numPlayers) {
        deck = new Deck();
        players = new ArrayList<>();
        for (int i = 0; i < numPlayers; i++) {
            players.add(new Player("Player " + (i + 1)));
        }
        initializeGame();
    }

    private void initializeGame() {
        for (Player player : players) {
            for (int i = 0; i < 4; i++) {
                player.drawCard(deck);
            }
        }
        Card initialCard = deck.drawCard();
        currentSum = initialCard.getValue();
        currentPlayerIndex = 0;
    }

    public Player getCurrentPlayer() {
        return players.get(currentPlayerIndex);
    }

    public int getCurrentSum() {
        return currentSum;
    }

    public void playTurn(Card card) {
        Player currentPlayer = getCurrentPlayer();
        if (currentPlayer.canPlayCard(currentSum, card)) {
            currentPlayer.playCard(card);
            currentSum += card.getValue();
            currentPlayer.drawCard(deck);
            if (currentPlayer.getHand().isEmpty()) {
                players.remove(currentPlayer);
            }
            currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
        } else {
            players.remove(currentPlayer);
        }
    }

    public boolean isGameOver() {
        return players.size() == 1;
    }

    public Player getWinner() {
        return players.get(0);
    }
}