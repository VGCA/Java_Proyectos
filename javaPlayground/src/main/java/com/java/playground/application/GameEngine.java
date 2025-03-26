package com.java.playground.application;

import com.java.playground.model.Card;
import com.java.playground.model.Deck;
import com.java.playground.model.GameState;
import com.java.playground.model.Player;
import com.java.playground.model.in.GameInput;
import com.java.playground.model.out.GameOutput;

public class GameEngine {
    private final GameInput input;
    private final GameOutput output;
    private GameState state;

    public GameEngine(GameInput input, GameOutput output) {
        this.input = input;
        this.output = output;
    }

    public void startBattle() {
        state = new GameState(new Player(30), new Deck());
        state.drawInitialHand();

        while(!battleFinished()) {
            state.refillHand();
            output.displayGameState(state);
            Card selected = input.selectCard(state.getHand());
            selected.play(state);
            state.discardCard(selected);
        }
    }

    private boolean battleFinished() {
        return state.getPlayer().isDefeated();
    }
}
