package com.java.playground.domain.services;

import com.java.playground.domain.model.Card;

public class AttackCard extends Card {

    private int damage;

    @Override
    public void applyEffect(Player player, List<Enemy> enemies) {
        enemies.get(0).takeDamage(damage + player.getAttackBonus());
    }
}

