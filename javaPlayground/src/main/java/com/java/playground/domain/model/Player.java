package com.java.playground.domain.model;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class Player {

    private int health;
    private int defense;
    private final Deque<Card> deck = new ArrayDeque<>();
    private final List<Card> hand = new ArrayList<>();
    private final List<Card> discardPile = new ArrayList<>();

    public void drawCards(int quantity) {
        // Lógica para robar cartas
    }
}
