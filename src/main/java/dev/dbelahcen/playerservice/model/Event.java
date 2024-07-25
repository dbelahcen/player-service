package dev.dbelahcen.playerservice.model;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Event {
    private Integer year;
    private Integer month;
    private Integer day;
    private String country;
    private String state;
    private String city;
}
