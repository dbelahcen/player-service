package dev.dbelahcen.playerservice.repository.csv;

import dev.dbelahcen.playerservice.exceptions.InitializationException;
import dev.dbelahcen.playerservice.model.Player;
import dev.dbelahcen.playerservice.repository.PlayerRepository;
import jakarta.annotation.PostConstruct;
import org.apache.commons.collections4.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class PlayerCsvRepository implements PlayerRepository {
    private final String playerCsvFileLocation;
    private final PlayerCsvFileReader playerCsvFileReader;
    private final PlayerDoConverter playerDoConverter;
    private Map<String, Player> playerIdToPlayer = new HashMap<>();
    private static final Logger logger = LoggerFactory.getLogger(PlayerCsvRepository.class);

    public PlayerCsvRepository(String playerCsvFileLocation, PlayerCsvFileReader playerCsvFileReader,
                               PlayerDoConverter playerDoConverter) {
        this.playerCsvFileLocation = playerCsvFileLocation;
        this.playerCsvFileReader = playerCsvFileReader;
        this.playerDoConverter = playerDoConverter;
    }

    @PostConstruct
    public void onInit() {
        loadCsv();
    }

    @Scheduled(initialDelay = 60000 * 10L, fixedRate = 60000 * 10L)
    public void loadCsv() {
        logger.info("Loading CSV");
        Map<String, Player> tempMap; // Assumption - it's preferable to keep old data in case we can't read the data for some reason.
        try {
            tempMap = playerCsvFileReader.getPlayersFromFile(playerCsvFileLocation)
                    .entrySet().stream()
                    .collect(Collectors.toMap(Map.Entry::getKey, entry -> playerDoConverter.convertPlayerDo(entry.getValue())));
        } catch (FileNotFoundException e) {
            logger.error("CSV File {} not found!", playerCsvFileLocation);
            throw new InitializationException("CSV File not found!", e);
        }
        if (MapUtils.isNotEmpty(tempMap)) {
            playerIdToPlayer = tempMap;
        }
        logger.info("CSV loaded. Size: {}", playerIdToPlayer.size());
    }

    @Override
    public Mono<Player> findPlayerById(String playerId) {
        return Mono.justOrEmpty(playerIdToPlayer.get(playerId));
    }

    @Override
    public Flux<Player> getAllPlayers() {
        return Flux.fromStream(playerIdToPlayer.values().stream());
    }
}
