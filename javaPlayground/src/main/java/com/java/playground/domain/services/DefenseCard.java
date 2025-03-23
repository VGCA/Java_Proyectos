package com.java.playground.domain.services;

import com.java.playground.domain.model.Card;

public class DefenseCard extends Card {
    private int defenseValue;

    @Override
    public void applyEffect(Player player, List<Enemy> enemies) {
        player.addDefense(defenseValue);
    }
}

