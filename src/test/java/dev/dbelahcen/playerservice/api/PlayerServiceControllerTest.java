package dev.dbelahcen.playerservice.api;

import dev.dbelahcen.playerservice.api.model.ServiceResponse;
import dev.dbelahcen.playerservice.service.PlayerService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PlayerServiceControllerTest {

    @Mock
    private PlayerService playerService;

    @InjectMocks
    private PlayerServiceController playerServiceController;

    @Mock
    private ServiceResponse serviceResponse;

    @Test
    void getPlayersHappyPath() {
        when(playerService.getPlayers(anyInt(), anyInt())).thenReturn(Mono.just(serviceResponse));

        StepVerifier.create(playerServiceController.getPlayers(0, 5))
                .assertNext(entity -> {
                    assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
                    assertThat(entity.getBody()).isEqualTo(serviceResponse);
                })
                .verifyComplete();
    }

    @Test
    void getPlayerById() {
        String playerId = "";
        when(playerService.getPlayerById(playerId)).thenReturn(Mono.just(serviceResponse));

        StepVerifier.create(playerServiceController.getPlayerById(playerId))
                .assertNext(entity -> {
                    assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
                    assertThat(entity.getBody()).isEqualTo(serviceResponse);
                })
                .verifyComplete();
    }

}