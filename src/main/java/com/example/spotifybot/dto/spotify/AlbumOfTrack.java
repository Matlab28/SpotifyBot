package com.example.spotifybot.dto.spotify;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AlbumOfTrack {
    private String uri;
    private String name;
    private CoverArt coverArt;
    private String id;
    private SharingInfo sharingInfo;
}
