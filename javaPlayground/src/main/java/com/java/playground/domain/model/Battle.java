package com.java.playground.domain.model;

public class Battle {
    private Player player;
    private List<Enemy> enemies;
    private int currentTurn;

    public void startBattle() {
        player.getDeck().shuffle();
        player.drawInitialHand();
    }

    public void processTurn(Card selectedCard) {
        selectedCard.applyEffect(player, enemies);
        player.getDeck().discardCard(selectedCard);
    }
}

