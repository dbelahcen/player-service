package dev.dbelahcen.playerservice.repository.csv;

import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.enums.CSVReaderNullFieldIndicator;
import dev.dbelahcen.playerservice.repository.PlayerDO;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.nio.file.Paths;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class PlayerCsvFileReader {

    public Map<String, PlayerDO> getPlayersFromFile(String playerFileLocation) throws FileNotFoundException {
        return new CsvToBeanBuilder<PlayerDO>(getFileReader(playerFileLocation))
                .withType(PlayerDO.class)
                .withIgnoreLeadingWhiteSpace(true)
                .withFieldAsNull(CSVReaderNullFieldIndicator.EMPTY_SEPARATORS)
                .withThrowExceptions(false)
                .withExceptionHandler(new MismatchExceptionHandler())
                .build()
                .stream()
                .collect(Collectors.toMap(PlayerDO::getPlayerID, Function.identity()));
    }

    private BufferedReader getFileReader(String playerFileLocation) throws FileNotFoundException {
        File playersFile = Paths.get(playerFileLocation).toFile();
        return new BufferedReader(new FileReader(playersFile));
    }

}
