package dev.dbelahcen.playerservice.model;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Builder
@Getter
public class Player {
    private String playerId;
    private String firstName;
    private String lastName;
    private String givenName;
    private Event birthData;
    private Event deathData;
    private PhysicalData physicalData;
    private String retroId;
    private String bbRefId;
    private LocalDate debutDate;
    private LocalDate lastGameDate;
}
