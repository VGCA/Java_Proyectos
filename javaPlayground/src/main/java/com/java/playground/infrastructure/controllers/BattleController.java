package com.java.playground.infrastructure.controllers;

import com.java.playground.application.GameService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/battle")
@RequiredArgsConstructor
public class BattleController {

    private final GameService gameService;

    @PostMapping("/play-card")
    public ResponseEntity<BattleState> playCard(
            @RequestBody PlayCardRequest request
    ) {
        gameService.playCard(request.cardId());
        return ResponseEntity.ok(currentState());
    }
}

