package dev.dbelahcen.playerservice.api;

import dev.dbelahcen.playerservice.api.model.ServiceResponse;
import dev.dbelahcen.playerservice.service.PlayerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/players")
public class PlayerServiceController {

    private final PlayerService playerService;

    public PlayerServiceController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @GetMapping
    public Mono<ResponseEntity<ServiceResponse>> getPlayers(@RequestParam(value = "offset", defaultValue = "0") int offset,
                                                            @RequestParam(value = "limit", defaultValue = "100") int limit) {
        return playerService.getPlayers(offset, limit)
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
