package com.java.playground.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

public class GameState {
    private final Player player;
    private final Deck deck;
    private List<Card> hand = new ArrayList<>();

    public GameState(Player player, Deck deck) {
        this.player = player;
        this.deck = deck;
        initializeDeck();
    }

    private void initializeDeck() {
        // Add 5 attack and 5 defense cards
        for (int i = 0; i < 5; i++) {
            deck.addCard(new AttackCard("Basic Attack", 5));
            deck.addCard(new DefenseCard("Basic Defense", 3));
        }
        deck.shuffle();
    }

    public void drawInitialHand() {
        hand = deck.drawCards(5);
    }

    public void refillHand() {
        int cardsNeeded = 5 - hand.size();
        if (cardsNeeded > 0) {
            List<Card> newCards = deck.drawCards(cardsNeeded);
            hand.addAll(newCards);
        }
    }

    public void applyDamage(int damage) {
        player.takeDamage(damage);
    }

    public void addDefense(int defense) {
        player.addDefense(defense);
    }

    public void discardCard(Card card) {
        hand.remove(card);
        deck.discard(card);
    }

    // Getters
    public Player getPlayer() { return player; }
    public List<Card> getHand() { return Collections.unmodifiableList(hand); }
    public boolean isPlayerDefeated() { return player.isDefeated(); }
}

