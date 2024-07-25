package dev.dbelahcen.playerservice.repository.csv;

import dev.dbelahcen.playerservice.model.Event;
import dev.dbelahcen.playerservice.model.PhysicalData;
import dev.dbelahcen.playerservice.model.Player;
import dev.dbelahcen.playerservice.repository.PlayerDO;
import org.apache.commons.lang3.StringUtils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class PlayerDoConverter {

    public Player convertPlayerDo(PlayerDO playerDO) {
        return Player.builder()
                .playerId(playerDO.getPlayerID())
                .firstName(playerDO.getNameFirst())
                .lastName(playerDO.getNameLast())
                .bbRefId(playerDO.getBbrefID())
                .retroId(playerDO.getRetroID())
                .givenName(playerDO.getNameGiven())
                .debutDate(translateStringToDate(playerDO.getDebut()))
                .lastGameDate(translateStringToDate(playerDO.getFinalGame()))
                .birthData(Event.builder()
                        .country(playerDO.getBirthCountry())
                        .state(playerDO.getBirthState())
                        .city(playerDO.getBirthCity())
                        .year(playerDO.getBirthYear())
                        .month(playerDO.getBirthMonth())
                        .day(playerDO.getBirthDay())
                        .build())
                .deathData(Event.builder()
                        .country(playerDO.getDeathCountry())
                        .state(playerDO.getDeathState())
                        .city(playerDO.getDeathCity())
                        .year(playerDO.getDeathYear())
                        .month(playerDO.getDeathMonth())
                        .day(playerDO.getDeathDay())
                        .build())
                .physicalData(PhysicalData.builder()
                        .weightInCms(playerDO.getWeight())
                        .heightInCms(playerDO.getHeight())
                        .batHand(playerDO.getBats())
                        .throwHand(playerDO.getThrowsHand())
                        .build())
                .build();
    }

    private LocalDate translateStringToDate(String dateStr) {
        if (StringUtils.isNotEmpty(dateStr)){
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            return LocalDate.parse(dateStr, dateTimeFormatter);
        }
        return null;
    }
}
