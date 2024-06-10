package com.example.spotifybot.dto.spotify;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Setter
@Getter
public class Genres {
    private Integer totalCount;
    private ArrayList<Object> items;
}
