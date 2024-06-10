package com.example.spotifybot.dto.spotify;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Data {
    private String uri;
    private String name;
    private Artists artists;
    private CoverArt coverArt;
    private Date date;
    private Profile profile;
    private Visuals visuals;
    private Duration duration;
    private ReleaseDate releaseDate;
    private Podcast podcast;
    private String description;
    private ContentRating contentRating;
    private Images images;
    private Owner owner;
    private String type;
    private Publisher publisher;
    private String mediaType;
    private String id;
    private AlbumOfTrack albumOfTrack;
    private Playability playability;
    private String displayName;
    private String username;
    private Image image;
}
