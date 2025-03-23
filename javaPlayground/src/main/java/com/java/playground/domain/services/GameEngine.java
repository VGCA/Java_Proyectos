package com.java.playground.domain.services;

import com.java.playground.domain.model.Card;
import com.java.playground.domain.model.Enemy;
import com.java.playground.domain.model.Player;

import java.util.List;

public class GameEngine {

    private final Player player;
    private final List<Enemy> currentEnemies;

    public GameEngine(Player player, List<Enemy> currentEnemies) {
        this.player = player;
        this.currentEnemies = currentEnemies;
    }

    public void playCard(Card card, Enemy target) {
        // Lógica de aplicación de cartas
    }
}
