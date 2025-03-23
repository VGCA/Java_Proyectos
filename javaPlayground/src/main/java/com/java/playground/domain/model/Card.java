package com.java.playground.domain.model;

import lombok.Data;

@Data
public class Card {

    private final String name;
    private final com.java.playground.domain.model.CardType type;
    private final int value;
}
