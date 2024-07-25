package dev.dbelahcen.playerservice.repository;

import com.opencsv.bean.CsvBindByName;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PlayerDO {
    @CsvBindByName private String playerID;
    @CsvBindByName private Integer birthYear;
    @CsvBindByName private Integer birthMonth;
    @CsvBindByName private Integer birthDay;
    @CsvBindByName private String birthCountry;
    @CsvBindByName private String birthState;
    @CsvBindByName private String birthCity;
    @CsvBindByName private Integer deathYear;
    @CsvBindByName private Integer deathMonth;
    @CsvBindByName private Integer deathDay;
    @CsvBindByName private String deathCountry;
    @CsvBindByName private String deathState;
    @CsvBindByName private String deathCity;
    @CsvBindByName private String nameFirst;
    @CsvBindByName private String nameLast;
    @CsvBindByName private String nameGiven;
    @CsvBindByName private Integer weight;
    @CsvBindByName private Integer height;
    @CsvBindByName private String bats;
    @CsvBindByName(column = "throws") private String throwsHand;
    @CsvBindByName private String debut;
    @CsvBindByName private String finalGame;
    @CsvBindByName private String retroID;
    @CsvBindByName private String bbrefID;
}
