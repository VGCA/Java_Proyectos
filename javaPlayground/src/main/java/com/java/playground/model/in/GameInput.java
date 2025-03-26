package com.java.playground.model.in;

import com.java.playground.model.Card;

import java.util.List;

public interface GameInput {
    Card selectCard(List<Card> hand);
}