package com.example.spotifybot.dto.spotify;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Setter
@Getter
public class Artists {
    private ArrayList<Item> items;
    private Integer totalCount;
}
