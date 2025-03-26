package com.java.playground.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class Deck {
    private final Stack<Card> drawPile = new Stack<>();
    private final Stack<Card> discardPile = new Stack<>();

    public void addCard(Card card) {
        drawPile.push(card);
    }

    public void shuffle() {
        Collections.shuffle(drawPile);
    }

    public List<Card> drawCards(int amount) {
        List<Card> drawn = new ArrayList<>();
        for (int i = 0; i < amount; i++) {
            if (drawPile.isEmpty()) {
                reshuffleDiscard();
            }
            if (!drawPile.isEmpty()) {
                drawn.add(drawPile.pop());
            }
        }
        return drawn;
    }

    private void reshuffleDiscard() {
        drawPile.addAll(discardPile);
        discardPile.clear();
        shuffle();
    }

    public void discard(Card card) {
        discardPile.push(card);
    }
}
