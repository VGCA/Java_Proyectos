package com.java.playground.infrastructure.adapters;

import com.java.playground.application.ports.GameUI;
import com.java.playground.domain.model.Card;

import java.util.List;

public class JavaFXGameUI implements GameUI {

    private final ObservableList<Card> hand = FXCollections.observableArrayList();

    public void updatePlayerStats(int health, int defense) {
        Platform.runLater(() -> {
            healthLabel.setText("Salud: " + health);
            defenseLabel.setText("Defensa: " + defense);
        });
    }

    @Override
    public void displayCards(List<Card> hand) {

    }

    @Override
    public void showBattleResult(boolean victory) {

    }
}
