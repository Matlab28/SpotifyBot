package com.example.spotifybot.dto.spotify;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Date {
//    private Integer year;

    private String isoString;

    public Date() {
    }

    public Date(String isoString) {
        this.isoString = isoString;
    }

    public static Date fromIsoString(String isoString) {
        return new Date(isoString);
    }
}
