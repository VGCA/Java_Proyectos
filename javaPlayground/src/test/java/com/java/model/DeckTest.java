package com.java.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DeckTest {

    @Test
    void shouldRefillHandWhenEmpty() {
        Deck deck = new Deck(/* cartas */);
        deck.drawInitialHand();

        assertEquals(5, deck.getCurrentHand().size());
    }
}

