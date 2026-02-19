package com.java.playground.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ArrayDeque;
import java.util.Deque;

public class Deck {

    private final Deque<Card> drawPile = new ArrayDeque<>();
    private final Deque<Card> discardPile = new ArrayDeque<>();

    public void addCard(Card card) {
        drawPile.push(card);
    }

    public void shuffle() {
        Collections.shuffle((List<?>) drawPile);
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
