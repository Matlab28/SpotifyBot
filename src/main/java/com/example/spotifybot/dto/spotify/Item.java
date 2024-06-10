package com.example.spotifybot.dto.spotify;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Setter
@Getter
public class Item {
    private Data data;
    private ArrayList<Source> sources;
    private String uri;
    private Profile profile;
}
