package com.java.playground.domain.services;

import com.java.playground.domain.model.Card;

public class SpecialCard extends Card {
    private boolean usedInBattle = false;

    @Override
    public void applyEffect(Player player, List<Enemy> enemies) {
        if(!usedInBattle) {
            // Efecto especial
            usedInBattle = true;
        }
    }

    public void resetForNewBattle() {
        usedInBattle = false;
    }
}

