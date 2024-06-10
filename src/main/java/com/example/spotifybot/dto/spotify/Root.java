package com.example.spotifybot.dto.spotify;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Root {
    private Albums albums;
    private Artists artists;
    private Episodes episodes;
    private Genres genres;
    private Playlists playlists;
    private Podcasts podcasts;
    private TopResults topResults;
    private Tracks tracks;
    private Users users;
}
