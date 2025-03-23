package com.java.playground.domain.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
    private List<Card> mainDeck = new ArrayList<>();
    private List<Card> discardPile = new ArrayList<>();
    private List<Card> currentHand = new ArrayList<>();

    public void shuffle() {
        Collections.shuffle(mainDeck);
    }

    public void refillHand() {
        while(currentHand.size() < 5 && !mainDeck.isEmpty()) {
            currentHand.add(mainDeck.remove(0));
        }

        if(mainDeck.isEmpty()) {
            mainDeck.addAll(discardPile);
            discardPile.clear();
            shuffle();
        }
    }
}

