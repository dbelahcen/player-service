package dev.dbelahcen.playerservice.service;

import dev.dbelahcen.playerservice.api.model.ServiceResponse;
import dev.dbelahcen.playerservice.exceptions.PlayerNotFoundException;
import dev.dbelahcen.playerservice.model.Player;
import dev.dbelahcen.playerservice.repository.PlayerRepository;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.List;

import static java.util.Collections.emptyList;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PlayerServiceTest {

    @Mock
    private PlayerRepository playerRepository;

    @InjectMocks
    private PlayerService playerService;

    @Mock
    private Player player1;

    @Mock
    private Player player2;

    @Nested
    class getPlayers {
        @Test
        void happyPath() {
            when(playerRepository.getAllPlayers()).thenReturn(Flux.just(player1, player2));

            StepVerifier.create(playerService.getPlayers(0, 2))
                    .expectNext(new ServiceResponse(List.of(player1, player2)))
                    .verifyComplete();
        }

        @Test
        void repositoryReturnsEmptyResponse() {
            when(playerRepository.getAllPlayers()).thenReturn(Flux.empty());

            StepVerifier.create(playerService.getPlayers(0, 2))
                    .expectNext(new ServiceResponse(emptyList()))
                    .verifyComplete();
        }

        @Test
        void repositoryReturnsAnError() {
            when(playerRepository.getAllPlayers()).thenReturn(Flux.error(new RuntimeException("Error flux")));

            StepVerifier.create(playerService.getPlayers(0, 2))
                    .expectError(RuntimeException.class)
                    .verify();
        }
    }

    @Nested
    class getPlayerById {
        private static final String PLAYER_ID = "player_id";
        @Test
        void happyPath() {
            when(playerRepository.findPlayerById(PLAYER_ID)).thenReturn(Mono.just(player1));

            StepVerifier.create(playerService.getPlayerById(PLAYER_ID))
                    .expectNext(new ServiceResponse(List.of(player1)))
                    .verifyComplete();
        }

        @Test
        void repositoryReturnsEmptyResponse() {
            when(playerRepository.findPlayerById(PLAYER_ID)).thenReturn(Mono.empty());

            StepVerifier.create(playerService.getPlayerById(PLAYER_ID))
                    .expectError(PlayerNotFoundException.class)
                    .verify();
        }

        @Test
        void repositoryReturnsAnError() {
            when(playerRepository.findPlayerById(PLAYER_ID)).thenReturn(Mono.error(new RuntimeException("Error Mono")));

            StepVerifier.create(playerService.getPlayerById(PLAYER_ID))
                    .expectError(RuntimeException.class)
                    .verify();
        }
    }

}