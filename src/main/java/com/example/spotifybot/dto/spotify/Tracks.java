package com.example.spotifybot.dto.spotify;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Setter
@Getter
public class Tracks {
    private Integer totalCount;
    private ArrayList<Item> items;
}
