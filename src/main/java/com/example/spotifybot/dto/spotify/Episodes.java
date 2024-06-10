package com.example.spotifybot.dto.spotify;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Setter
@Getter
public class Episodes {
    private Integer totalCount;
    private ArrayList<Item> items;
}
