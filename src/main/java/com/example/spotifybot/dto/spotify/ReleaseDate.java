package com.example.spotifybot.dto.spotify;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class ReleaseDate {

    @JsonProperty("isoString")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
    private LocalDateTime isoString;

    public LocalDateTime getIsoString() {
        return isoString;
    }

    public void setIsoString(LocalDateTime isoString) {
        this.isoString = isoString;
    }
}
