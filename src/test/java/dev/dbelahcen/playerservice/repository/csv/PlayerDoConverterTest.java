package dev.dbelahcen.playerservice.repository.csv;

import dev.dbelahcen.playerservice.model.Player;
import dev.dbelahcen.playerservice.repository.PlayerDO;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;

class PlayerDoConverterTest {

    private final PlayerDoConverter playerDoConverter = new PlayerDoConverter();

    @Test
    void happyPath() {
        PlayerDO playerDO = PlayerDO.builder()
                .playerID("qwert")
                .nameFirst("name")
                .nameLast("last")
                .birthCity("birth city")
                .birthCountry("birth country")
                .birthState("birth state")
                .birthDay(1)
                .birthMonth(2)
                .birthYear(3)
                .deathDay(4)
                .deathMonth(5)
                .deathYear(6)
                .deathCity("dcity")
                .deathCountry("dcountry")
                .deathState("dstate")
                .bats("bats")
                .throwsHand("throw")
                .bbrefID("qwerty")
                .retroID("qwerty")
                .build();
        Player player = playerDoConverter.convertPlayerDo(playerDO);
        assertAll(
                () -> Assertions.assertThat(player.getPlayerId()).isEqualTo(playerDO.getPlayerID()),
                () -> Assertions.assertThat(player.getGivenName()).isEqualTo(playerDO.getNameGiven()),
                () -> Assertions.assertThat(player.getFirstName()).isEqualTo(playerDO.getNameFirst()),
                () -> Assertions.assertThat(player.getLastName()).isEqualTo(playerDO.getNameLast()),
                () -> Assertions.assertThat(player.getPhysicalData().getHeightInCms()).isEqualTo(playerDO.getHeight())
                        //... Time limit
        );
    }

}