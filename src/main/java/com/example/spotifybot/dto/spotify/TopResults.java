package com.example.spotifybot.dto.spotify;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Setter
@Getter
public class TopResults {
    private ArrayList<Item> items;
    private ArrayList<Featured> featured;
}
