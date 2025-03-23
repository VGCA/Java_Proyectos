package com.java.playground.application;

import com.java.playground.domain.model.Battle;
import com.java.playground.domain.model.Card;
import com.java.playground.domain.model.Deck;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GameService {

    private final DeckRepository deckRepository;
    private final BattleManager battleManager;

    public void startNewGame() {
        Deck initialDeck = createStarterDeck();
        deckRepository.save(initialDeck);

        Battle newBattle = battleManager.createBattle(
                initialDeck,
                List.of(new BasicEnemy())
        );

        newBattle.startBattle();
    }

    private Deck createStarterDeck() {
        List<Card> cards = new ArrayList<>();
        // Añadir 5 cartas de ataque y 5 de defensa
        return new Deck(cards);
    }
}

