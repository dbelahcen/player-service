package dev.dbelahcen.playerservice.api.model;

import dev.dbelahcen.playerservice.model.Player;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
@EqualsAndHashCode
public class ServiceResponse {
    private List<Player> players;
}
