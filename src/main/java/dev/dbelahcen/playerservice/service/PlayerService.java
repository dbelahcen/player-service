package dev.dbelahcen.playerservice.service;

import dev.dbelahcen.playerservice.api.model.ServiceResponse;
import dev.dbelahcen.playerservice.exceptions.PlayerNotFoundException;
import dev.dbelahcen.playerservice.repository.PlayerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;

/*
Some assumptions that I did:
1- empty DB is a valid use case in "Get Players" api.
2- the service response is just a List<Player> to emphasize that I tend to separate the external model exposed by the API to the internal one.
    So for example, we could filter sensitive / internal data from the object from the repository here. In that case, it's better to add a converter
    that is in charge to convert from Player to ServiceResponse object.
3-  This class should add counters to monitor the traffic, like callCounts, errorCounts, and other business metrics that may be valuable.
 */
@Service
public class PlayerService {
    private static final Logger logger = LoggerFactory.getLogger(PlayerService.class);
    private final PlayerRepository playerRepository;

    public PlayerService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    public Mono<ServiceResponse> getPlayers(int offset, int limit) {
        return playerRepository.getAllPlayers()
                .skip(offset)
                .take(limit)
                .collectList()
                .map(ServiceResponse::new);
    }

    public Mono<ServiceResponse> getPlayerById(String playerId) {
        return playerRepository.findPlayerById(playerId)
                .map(player -> new ServiceResponse(List.of(player)))
                .doOnError(throwable -> logger.error("An error occurred", throwable))
                //Assumption: caller to find by ID API is expected to send existing IDs. In case an ID was not found we throw an exception to return 404
                .switchIfEmpty(Mono.error(new PlayerNotFoundException(playerId)));
    }
}
