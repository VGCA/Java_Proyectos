package com.java.playground.application.ports;

import com.java.playground.domain.model.Card;

import java.util.List;

public interface GameUI {

    void updatePlayerStats(int health, int defense);
    void displayCards(List<Card> hand);
    void showBattleResult(boolean victory);
}
