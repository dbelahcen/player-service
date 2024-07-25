package dev.dbelahcen.playerservice.repository;

import dev.dbelahcen.playerservice.model.Player;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface PlayerRepository {

    Mono<Player> findPlayerById(String id);
    Flux<Player> getAllPlayers();

}
