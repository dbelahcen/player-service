package dev.dbelahcen.playerservice.api;

import dev.dbelahcen.playerservice.api.model.ErrorResponse;
import dev.dbelahcen.playerservice.exceptions.PlayerNotFoundException;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PlayerServiceAdviceTest {

    private final PlayerServiceAdvice playerServiceAdvice = new PlayerServiceAdvice();

    @Test
    void handlePlayerNotFound() {
        ErrorResponse errorResponse = playerServiceAdvice.handlePlayerNotFound(new PlayerNotFoundException("Daniel"));
        assertThat(errorResponse.getErrorDetails()).isEqualTo("Player: Daniel does not exist");
    }

    @Test
    void handleGeneralException() {
        ErrorResponse errorResponse = playerServiceAdvice.handleGeneralException(new Throwable());
        assertThat(errorResponse.getErrorDetails()).isEqualTo("An unexpected error occurred");
    }

}