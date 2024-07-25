package dev.dbelahcen.playerservice;

import dev.dbelahcen.playerservice.repository.PlayerRepository;
import dev.dbelahcen.playerservice.repository.csv.PlayerCsvFileReader;
import dev.dbelahcen.playerservice.repository.csv.PlayerCsvRepository;
import dev.dbelahcen.playerservice.repository.csv.PlayerDoConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PlayerServiceConfiguration {

    @Bean
    public PlayerRepository playerRepository(@Value("${players.csv-file.path}") String playerCsvFileLocation) {
        return new PlayerCsvRepository(playerCsvFileLocation, new PlayerCsvFileReader(), new PlayerDoConverter());
    }

}
