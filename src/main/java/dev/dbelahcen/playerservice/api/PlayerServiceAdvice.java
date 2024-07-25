package dev.dbelahcen.playerservice.api;

import dev.dbelahcen.playerservice.api.model.ErrorResponse;
import dev.dbelahcen.playerservice.exceptions.PlayerNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class PlayerServiceAdvice {

    private static final Logger logger = LoggerFactory.getLogger(PlayerServiceAdvice.class);

    @ExceptionHandler(PlayerNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handlePlayerNotFound(PlayerNotFoundException playerNotFoundException) {
        logger.error("Player: {} does not exist", playerNotFoundException.getPlayerId());
        return new ErrorResponse("Player: " + playerNotFoundException.getPlayerId() + " does not exist");
    }

    @ExceptionHandler(Throwable.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse handleGeneralException(Throwable t) {
        logger.error("An unexpected error occurred", t);
        return new ErrorResponse("An unexpected error occurred");
    }

}
