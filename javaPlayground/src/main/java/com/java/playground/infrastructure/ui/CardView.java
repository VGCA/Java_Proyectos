package com.java.playground.infrastructure.ui;

import com.java.playground.domain.model.Card;
import org.w3c.dom.Text;

import java.awt.*;

public class CardView extends StackPane {
    public CardView(Card card) {
        Rectangle bg = new Rectangle(100, 140);
        bg.setFill(Color.LIGHTGRAY);

        Text text = new Text(card.getName());
        this.getChildren().addAll(bg, text);

        this.setOnMouseClicked(e ->
                GameController.playCard(card)
        );
    }
}