package com.example.hellojavafx.controllers;

import com.example.hellojavafx.models.Card;
import com.example.hellojavafx.models.Deck;
import com.example.hellojavafx.models.Player;
import com.example.hellojavafx.view.alert.AlertBox;
import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;

/**
 * Controller class for managing the game logic and UI interactions.
 */
public class GameController {

    @FXML
    private Pane paneView1;
    @FXML
    private Pane paneView2;
    @FXML
    private Pane paneView3;
    @FXML
    private Label labelScore, lblCurrentPlayer, lblCountDeck;
    @FXML
    private ImageView cardView00, cardView01, cardView02, cardView03;
    @FXML
    private ImageView cardView10, cardView11, cardView12, cardView13;
    @FXML
    private ImageView cardView20, cardView21, cardView22, cardView23;
    @FXML
    private ImageView cardView30, cardView31, cardView32, cardView33;
    @FXML
    private ImageView imageCardPrincipal;
    @FXML
    private Button cardHuman0, cardHuman1, cardHuman2, cardHuman3;

    private List<Player> players;
    private Deck deck;
    private int currentPlayerIndex = 0, score = 0;

    /**
     * Constructs a new GameController with the specified number of players.
     *
     * @param numPlayers the number of players in the game
     */
    public GameController(int numPlayers) {
        players = new ArrayList<>();
        deck = new Deck();
    }

    /**
     * Initializes the game controller.
     * Sets up the game board and UI elements.
     */
    @FXML
    public void initialize() {
    }

    /**
     * Updates the score label, current player label, and deck count label.
     */
    private void updateScoreLabel() {
        labelScore.setText(String.valueOf(score));
        lblCurrentPlayer.setText(players.get(currentPlayerIndex).getName());
        lblCountDeck.setText(String.valueOf(deck.getRemainingCards()));
    }

    /**
     * Initializes the game with the specified number of players.
     *
     * @param numPlayers the number of players in the game
     */
    public void initGame(int numPlayers) {
        paneView1.setVisible(numPlayers >= 2);
        paneView2.setVisible(numPlayers >= 3);
        paneView3.setVisible(numPlayers == 4);

        players.add(new Player("Human", false));
        for (int i = 1; i < numPlayers; i++) {
            players.add(new Player("Machine " + i, true));
        }

        for (Player player : players) {
            for (int j = 0; j < 4; j++) {
                player.drawCard(deck);
            }
        }

        Card card = deck.drawCard();
        if (card != null) {
            imageCardPrincipal.setImage(new Image(getClass().getResourceAsStream(card.getImg())));
            score = card.getValue();
            updateScoreLabel();
            deck.addCardToUsed(card);
        }
        drawCardsBoard();
    }

    /**
     * Draws the cards on the game board.
     */
    public void drawCardsBoard() {
        boolean showMachineCards = false;

        List<Card> player0Hand = players.get(0).getHand();
        cardView00.setImage(new Image(getClass().getResourceAsStream(player0Hand.get(0).getImg())));
        cardView01.setImage(new Image(getClass().getResourceAsStream(player0Hand.get(1).getImg())));
        cardView02.setImage(new Image(getClass().getResourceAsStream(player0Hand.get(2).getImg())));
        cardView03.setImage(new Image(getClass().getResourceAsStream(player0Hand.get(3).getImg())));

        if (showMachineCards) {
            List<Card> player1Hand = players.get(1).getHand();
            cardView10.setImage(new Image(getClass().getResourceAsStream(player1Hand.get(0).getImg())));
            cardView11.setImage(new Image(getClass().getResourceAsStream(player1Hand.get(1).getImg())));
            cardView12.setImage(new Image(getClass().getResourceAsStream(player1Hand.get(2).getImg())));
            cardView13.setImage(new Image(getClass().getResourceAsStream(player1Hand.get(3).getImg())));

            if (players.size() > 2) {
                List<Card> player2Hand = players.get(2).getHand();
                cardView20.setImage(new Image(getClass().getResourceAsStream(player2Hand.get(0).getImg())));
                cardView21.setImage(new Image(getClass().getResourceAsStream(player2Hand.get(1).getImg())));
                cardView22.setImage(new Image(getClass().getResourceAsStream(player2Hand.get(2).getImg())));
                cardView23.setImage(new Image(getClass().getResourceAsStream(player2Hand.get(3).getImg())));

                if (players.size() > 3) {
                    List<Card> player3Hand = players.get(3).getHand();
                    cardView30.setImage(new Image(getClass().getResourceAsStream(player3Hand.get(0).getImg())));
                    cardView31.setImage(new Image(getClass().getResourceAsStream(player3Hand.get(1).getImg())));
                    cardView32.setImage(new Image(getClass().getResourceAsStream(player3Hand.get(2).getImg())));
                    cardView33.setImage(new Image(getClass().getResourceAsStream(player3Hand.get(3).getImg())));
                }
            }
        }
    }

    /**
     * Processes the next turn for the current player.
     *
     * @param card the card played by the current player
     */
    public void nextTurn(Card card) {
        blockHumanCards(true);
        boolean next = true;
        if (card != null) {
            if (players.get(0).canPlayCard(score, card)) {
                imageCardPrincipal.setImage(new Image(getClass().getResourceAsStream(card.getImg())));
                score += card.getValue();
                updateScoreLabel();
                deck.addCardToUsed(card);
                drawCardsBoard();
            } else {
                new AlertBox().showAlert("Error", "You cannot play that card", "Choose another one");
                next = false;
            }
        }
        if (next) {
            currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
            processNextPlayer();
        }
    }

    /**
     * Blocks or unblocks the human player's cards.
     *
     * @param status true to block the cards, false to unblock
     */
    public void blockHumanCards(boolean status) {
        cardHuman0.setDisable(status);
        cardHuman1.setDisable(status);
        cardHuman2.setDisable(status);
        cardHuman3.setDisable(status);
    }

    /**
     * Processes the next player in the game.
     */
    private void processNextPlayer() {
        Runnable task = () -> {
            Player winner = checkForWinner();
            if (winner != null) {
                Platform.runLater(() -> {
                    new AlertBox().showAlert("Winner", "We have a winner!", "The winner is " + winner.getName());
                });
                return;
            }
            Platform.runLater(() -> {
                updateScoreLabel();
            });
            if (currentPlayerIndex == 0) {
                if (!players.get(0).getIsDeleted()) {
                    if (!hasPlayableCard(players.get(0))) {
                        players.get(0).setDeleted(true);
                        Platform.runLater(() -> {
                            new AlertBox().showAlert("Error", "You have been eliminated", "You have no cards to play.");
                        });
                        currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
                        processNextPlayer();
                    } else {
                        blockHumanCards(false);
                    }
                } else {
                    currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
                    processNextPlayer();
                }
            } else if (currentPlayerIndex < players.size() && currentPlayerIndex > 0) {
                if (players.get(currentPlayerIndex).getIsDeleted()) {
                    hidePaneView(currentPlayerIndex);
                    currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
                    processNextPlayer();
                } else {
                    PauseTransition pause = new PauseTransition(Duration.seconds(2));
                    pause.setOnFinished(event -> {
                        Card cardMachine = playTurnMachine();
                        if (cardMachine != null) {
                            imageCardPrincipal.setImage(new Image(getClass().getResourceAsStream(cardMachine.getImg())));
                            score += cardMachine.getValue();
                            updateScoreLabel();
                            deck.addCardToUsed(cardMachine);
                            drawCardsBoard();
                        }
                        currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
                        processNextPlayer();
                    });
                    pause.play();
                }
            } else {
                drawCardsBoard();
            }
        };

        Thread thread = new Thread(task);
        thread.start();
    }

    /**
     * Hides the pane view for the specified player index.
     *
     * @param playerIndex the index of the player
     */
    private void hidePaneView(int playerIndex) {
        switch (playerIndex) {
            case 1:
                paneView1.setVisible(false);
                break;
            case 2:
                paneView2.setVisible(false);
                break;
            case 3:
                paneView3.setVisible(false);
                break;
            default:
                break;
        }
    }

    /**
     * Plays the turn for the machine player.
     *
     * @return the card played by the machine player
     */
    public Card playTurnMachine() {
        Player currentPlayer = players.get(currentPlayerIndex);
        if (currentPlayer.getIsDeleted()) {
            currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
            return null;
        }
        for (int i = 0; i < 4; i++) {
            Card card = currentPlayer.getHand().get(i);
            if (currentPlayer.canPlayCard(score, card)) {
                currentPlayer.playCard(card);
                currentPlayer.drawCard(deck);
                return card;
            }
        }
        currentPlayer.setDeleted(true);
        Platform.runLater(() -> {
            new AlertBox().showAlert("Error", "A player has been eliminated", "Player " + currentPlayer.getName() + " has been eliminated.");
        });
        return null;
    }

    /**
     * Checks if the player has a playable card.
     *
     * @param player the player to check
     * @return true if the player has a playable card, false otherwise
     */
    private boolean hasPlayableCard(Player player) {
        for (Card card : player.getHand()) {
            if (player.canPlayCard(score, card)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Handles the click event for the first human card.
     *
     * @throws InterruptedException if the thread is interrupted
     */
    @FXML
    private void handleCardHuman0Click() throws InterruptedException {
        System.out.println("Card Human 0 clicked");
        Player currentPlayer = players.get(0);
        Card card = currentPlayer.getHand().get(0);
        if (currentPlayer.canPlayCard(score, card)) {
            currentPlayer.playCard(card);
            currentPlayer.drawCard(deck);
            nextTurn(card);
        } else {
            currentPlayer.setDeleted(true);
            nextTurn(null);
        }
    }

    /**
     * Handles the click event for the second human card.
     *
     * @throws InterruptedException if the thread is interrupted
     */
    @FXML
    private void handleCardHuman1Click() throws InterruptedException {
        System.out.println("Card Human 1 clicked");
        Player currentPlayer = players.get(0);
        Card card = currentPlayer.getHand().get(1);
        if (currentPlayer.canPlayCard(score, card)) {
            currentPlayer.playCard(card);
            currentPlayer.drawCard(deck);
            nextTurn(card);
        } else {
            currentPlayer.setDeleted(true);
            nextTurn(null);
        }
    }

    /**
     * Handles the click event for the third human card.
     *
     * @throws InterruptedException if the thread is interrupted
     */
    @FXML
    private void handleCardHuman2Click() throws InterruptedException {
        System.out.println("Card Human 2 clicked");
        Player currentPlayer = players.get(0);
        Card card = currentPlayer.getHand().get(2);
        if (currentPlayer.canPlayCard(score, card)) {
            currentPlayer.playCard(card);
            currentPlayer.drawCard(deck);
            nextTurn(card);
        } else {
            currentPlayer.setDeleted(true);
            nextTurn(null);
        }
    }

    /**
     * Handles the click event for the fourth human card.
     *
     * @throws InterruptedException if the thread is interrupted
     */
    @FXML
    private void handleCardHuman3Click() throws InterruptedException {
        System.out.println("Card Human 3 clicked");
        Player currentPlayer = players.get(0);
        Card card = currentPlayer.getHand().get(3);
        if (currentPlayer.canPlayCard(score, card)) {
            currentPlayer.playCard(card);
            currentPlayer.drawCard(deck);
            nextTurn(card);
        } else {
            currentPlayer.setDeleted(true);
            nextTurn(null);
        }
    }

    /**
     * Checks for a winner in the game.
     *
     * @return the winning player, or null if there is no winner yet
     */
    private Player checkForWinner() {
        Player winner = null;
        int activePlayers = 0;

        for (Player player : players) {
            if (!player.getIsDeleted()) {
                activePlayers++;
                winner = player;
            }
        }

        if (activePlayers == 1) {
            return winner;
        } else {
            return null;
        }
    }
}