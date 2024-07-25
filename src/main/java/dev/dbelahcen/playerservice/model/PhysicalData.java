package dev.dbelahcen.playerservice.model;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class PhysicalData {
    private Integer weightInCms;
    private Integer heightInCms;
    private String batHand;
    private String throwHand;

    public enum Hand {
        R,L,B
    }
}
