package com.java.playground;

import com.java.playground.application.GameEngine;
import com.java.playground.infrastructure.ConsoleAdapter;
import com.java.playground.model.in.GameInput;
import com.java.playground.model.out.GameOutput;

public class JavaPlaygroundApplication {

    public static void main(String[] args) {
        GameInput input = new ConsoleAdapter();
        GameOutput output = new ConsoleAdapter();
        GameEngine engine = new GameEngine(input, output);
        engine.startBattle();
    }
}

