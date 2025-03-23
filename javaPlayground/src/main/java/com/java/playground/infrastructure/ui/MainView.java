package com.java.playground.infrastructure.ui;

import com.java.playground.domain.model.Card;

import java.util.List;

public class MainView extends BorderPane {
    private final HBox handContainer = new HBox(10);

    public void displayCards(List<Card> cards) {
        handContainer.getChildren().clear();
        cards.forEach(card -> {
            CardView cardView = new CardView(card);
            handContainer.getChildren().add(cardView);
        });
    }
}
