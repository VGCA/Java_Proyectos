package com.java.playground;

import com.java.playground.domain.services.GameEngine;
import com.java.playground.infrastructure.adapters.JavaFXGameLoop;
import com.java.playground.infrastructure.adapters.JavaFXGameUI;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JavaPlaygroundApplication extends Application {

    private GameEngine gameEngine;
    private JavaFXGameUI gameUI;

    @Override
    public void start(Stage stage) {
        gameEngine = new GameEngine();
        gameUI = new JavaFXGameUI();

        Scene scene = new Scene(gameUI.getMainView(), 800, 600);
        stage.setScene(scene);
        stage.show();

        new JavaFXGameLoop(gameEngine, gameUI).start();
    }
}

