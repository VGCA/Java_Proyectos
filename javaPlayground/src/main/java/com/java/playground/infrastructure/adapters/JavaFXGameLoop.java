package com.java.playground.infrastructure.adapters;

import com.java.playground.application.ports.GameLoop;

public class JavaFXGameLoop implements GameLoop {

    private final AnimationTimer gameLoop = new AnimationTimer() {
        @Override
        public void handle(long now) {
            updateGame();
            render();
        }
    };

    public void start() { gameLoop.start(); }
    public void stop() { gameLoop.stop(); }
}