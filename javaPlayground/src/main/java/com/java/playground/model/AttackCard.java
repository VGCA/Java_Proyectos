package com.java.playground.model;

public class AttackCard extends Card {
    private final int damage;

    public AttackCard(String name, int damage) {
        super(name);
        this.damage = damage;
    }

    @Override
    public void play(GameState state) {
        state.applyDamage(damage);
    }
}
