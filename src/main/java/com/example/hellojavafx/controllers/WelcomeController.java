package com.example.hellojavafx.controllers;
import com.example.hellojavafx.view.GameView;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.awt.event.ActionEvent;
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

    private void startGame(int numPlayers) throws IOException {
        GameView gameView = new GameView(numPlayers);
        gameView.show();
    }

    public void handleTwoPlayers(javafx.event.ActionEvent actionEvent) throws IOException {
        startGame(2);
    }

    public void handleThreePlayers(javafx.event.ActionEvent actionEvent) throws IOException {
        startGame(3);
    }

    public void handleFourPlayers(javafx.event.ActionEvent actionEvent) throws IOException {
        startGame(4);
    }
}