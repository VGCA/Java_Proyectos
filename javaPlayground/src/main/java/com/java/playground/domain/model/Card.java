package com.java.playground.domain.model;

import lombok.Data;

import java.util.UUID;

@Data
public abstract class Card {
    private UUID id;
    private String name;
    private boolean isExhausted;

    public abstract void applyEffect(Player player, List<Enemy> enemies);


}
