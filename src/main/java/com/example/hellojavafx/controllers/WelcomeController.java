package com.example.hellojavafx.controllers;

import com.example.hellojavafx.view.GameView;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Controller class for the welcome screen of the Battleship game.
 */
public class WelcomeController {

    /**
     * Initializes the controller.
     */
    @FXML
    public void initialize() {
    }

    /**
     * Starts the game with the specified number of players.
     *
     * @param numPlayers the number of players
     * @throws IOException if an I/O error occurs
     */
    private void startGame(int numPlayers) throws IOException {
        GameView gameView = new GameView(numPlayers);
        gameView.show();
    }

    /**
     * Handles the action event for starting a game with two players.
     *
     * @param actionEvent the action event
     * @throws IOException if an I/O error occurs
     */
    public void handleTwoPlayers(javafx.event.ActionEvent actionEvent) throws IOException {
        startGame(2);
    }

    /**
     * Handles the action event for starting a game with three players.
     *
     * @param actionEvent the action event
     * @throws IOException if an I/O error occurs
     */
    public void handleThreePlayers(javafx.event.ActionEvent actionEvent) throws IOException {
        startGame(3);
    }

    /**
     * Handles the action event for starting a game with four players.
     *
     * @param actionEvent the action event
     * @throws IOException if an I/O error occurs
     */
    public void handleFourPlayers(javafx.event.ActionEvent actionEvent) throws IOException {
        startGame(4);
    }
}