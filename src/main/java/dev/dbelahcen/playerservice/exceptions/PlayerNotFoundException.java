package dev.dbelahcen.playerservice.exceptions;

public class PlayerNotFoundException extends Exception {
    private final String playerId;

    public PlayerNotFoundException(String playerId) {
        super(playerId);
        this.playerId = playerId;
    }

    public String getPlayerId() {
        return playerId;
    }
}
