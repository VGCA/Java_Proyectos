package com.java.playground.model;

public class DefenseCard extends Card {
    private final int defense;

    public DefenseCard(String name, int defense) {
        super(name);
        this.defense = defense;
    }

    @Override
    public void play(GameState state) {
        state.addDefense(defense);
    }
}