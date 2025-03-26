package com.java.playground.model;

public abstract class Card {

    private final String name;

    public Card(String name) {
        this.name = name;
    }

    public abstract void play(GameState state);
}
