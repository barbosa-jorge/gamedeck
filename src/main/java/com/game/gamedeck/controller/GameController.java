package com.game.gamedeck.controller;

import com.game.gamedeck.model.Game;
import com.game.gamedeck.requests.AddPlayerRequest;
import com.game.gamedeck.requests.CreateGameRequest;
import com.game.gamedeck.services.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class GameController {

    @Autowired
    private GameService gameService;

    @GetMapping("/games")
    public ResponseEntity<List<Game>> getAllGames() {
        return new ResponseEntity<>(gameService.getAllGames(), HttpStatus.OK);
    }

    @PostMapping("/games")
    public ResponseEntity<Game> createGame(@RequestBody CreateGameRequest createGameRequest) {
        return new ResponseEntity<>(gameService.createGame(createGameRequest), HttpStatus.CREATED);
    }

    @DeleteMapping("/games/{game-id}")
    public ResponseEntity deleteGame(@PathVariable("game-id") String gameId) {
        gameService.deleteGame(gameId);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/games/{game-id}/players/{player-name}/deal-cards")
    public ResponseEntity<Game> dealCards(@PathVariable("game-id") String gameId,
                                          @PathVariable("player-name") String playerName) {

        return new ResponseEntity<>(gameService.dealCards(gameId, playerName), HttpStatus.OK);
    }

    @PostMapping("/games/{game-id}/players")
    public ResponseEntity<Game> addPlayer(@PathVariable("game-id") String gameId,
                                          @RequestBody AddPlayerRequest addPlayerRequest) {

        return new ResponseEntity<>(gameService.addPlayer(gameId, addPlayerRequest),
                HttpStatus.OK);
    }

    @DeleteMapping("/games/{game-id}/players/{player-name}")
    public ResponseEntity<Game> removePlayer(@PathVariable("game-id") String gameId,
                                             @PathVariable("player-name") String playerName) {

        return new ResponseEntity<>(gameService.removePlayer(gameId, playerName),
                HttpStatus.OK);
    }

    @PostMapping("/games/{game-id}/decks")
    public ResponseEntity<Game> addDeck(@PathVariable("game-id") String gameId) {
        return new ResponseEntity<>(gameService.addDeck(gameId), HttpStatus.OK);
    }
}
