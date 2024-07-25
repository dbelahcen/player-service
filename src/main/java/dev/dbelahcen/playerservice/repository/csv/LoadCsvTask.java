package dev.dbelahcen.playerservice.repository.csv;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoadCsvTask implements Runnable {
    private static final Logger logger = LoggerFactory.getLogger(LoadCsvTask.class);

    //Assumption: In case the CSV could not be loaded, we keep the previous instead of returning empty.
    // We may decide otherwise though.
    @Override
    public void run() {
//        logger.info("Going to load CSV");
//        Map<String, Player> tempPlayersMap;
//        try {
//            tempPlayersMap = playerCsvFileReader
//                    .getPlayersFromFile(playerCsvFileLocation)
//                    .entrySet().stream()
//                    .collect(Collectors.toMap(Map.Entry::getKey, entry -> playerDoConverter.convertPlayerDo(entry.getValue())));
//        } catch (IOException e) {
//            logger.error("Error", e);
//            throw new RuntimeException(e);
//        }
//        if (MapUtils.isNotEmpty(tempPlayersMap)) {
//            playerMap = tempPlayersMap;
//        }
//        logger.info("CSV loaded. Size: {}", playerIdToPlayer.size());
    }
}
