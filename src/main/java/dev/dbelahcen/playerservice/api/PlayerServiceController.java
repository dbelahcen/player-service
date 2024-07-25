package dev.dbelahcen.playerservice.api;

import dev.dbelahcen.playerservice.api.model.ServiceResponse;
import dev.dbelahcen.playerservice.service.PlayerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/players")
public class PlayerServiceController {

    private final PlayerService playerService;

    public PlayerServiceController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @GetMapping
    public Mono<ResponseEntity<ServiceResponse>> getPlayers() {
        return playerService.getPlayers()
                .map(flux -> ResponseEntity.ok().body(flux))
                .switchIfEmpty(Mono.just(ResponseEntity.noContent().build()));
    }

    @GetMapping("/{playerId}")
    public Mono<ResponseEntity<ServiceResponse>> getPlayerById(@PathVariable("playerId") String playerId) {
        return playerService
                .getPlayerById(playerId)
                .map(mono -> ResponseEntity.ok().body(mono));
    }
}
