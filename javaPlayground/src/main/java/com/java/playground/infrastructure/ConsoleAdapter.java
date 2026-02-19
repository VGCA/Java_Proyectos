package com.java.playground.infrastructure;

import com.java.playground.model.Card;
import com.java.playground.model.GameState;
import com.java.playground.model.in.GameInput;
import com.java.playground.model.out.GameOutput;

import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

// Console implementation example
public class ConsoleAdapter implements GameInput, GameOutput {

    private final Scanner scanner = new Scanner(System.in);
    private static final Logger logger = Logger.getLogger(ConsoleAdapter.class.getName());

    @Override
    public Card selectCard(List<Card> hand) {
        logger.info("Select a card (0-" + (hand.size()-1) + "):");
        int choice = scanner.nextInt();
        return hand.get(choice);
    }

    @Override
    public void displayGameState(GameState state) {
        // Implement console display
    }
}
