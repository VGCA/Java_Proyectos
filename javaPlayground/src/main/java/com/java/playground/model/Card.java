package com.java.playground.model;

public abstract class Card {

    private String name;

    protected Card(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public abstract void play(GameState state);
}
