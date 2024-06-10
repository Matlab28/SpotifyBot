package com.example.spotifybot.service;

import com.example.spotifybot.client.SpotifyApiClient;
import com.example.spotifybot.client.TelegramApiClient;
import com.example.spotifybot.dto.request.RootRequestDto;
import com.example.spotifybot.dto.request.TelegramSendDto;
import com.example.spotifybot.dto.response.ResultResponseDto;
import com.example.spotifybot.dto.response.RootResponseDto;
import com.example.spotifybot.dto.spotify.Item;
import com.example.spotifybot.dto.spotify.Root;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@Setter
@Getter
@Slf4j
public class SpotifyService {
    private final TelegramApiClient telegramApiClient;
    private final SpotifyApiClient spotifyApiClient;
    private final String host = "YOUR_API_HOST";
    private final String key = "YOUR_API_KEY";
    private Long lastUpdateId = 0L;

    public SpotifyService(TelegramApiClient telegramApiClient, SpotifyApiClient spotifyApiClient) {
        this.telegramApiClient = telegramApiClient;
        this.spotifyApiClient = spotifyApiClient;
    }

    public RootRequestDto getUpdateService() {
        RootRequestDto updates = telegramApiClient.getUpdates(0L);
        Integer updateId = updates.getResult().get(updates.getResult().size() - 1).getUpdateId();
        log.info("Message got from - " + updates.getResult().get(0).getMessage().getFrom().getFirstName());
        return telegramApiClient.getUpdates(Long.valueOf(updateId));
    }

    public Root getSpotifyData(String query) {
        return spotifyApiClient.getData(host, key, "multi", query, 0, 10, 5);
    }

    public RootResponseDto sendMessage(TelegramSendDto dto) {
        return telegramApiClient.sendMessage(dto);
    }

    public RootResponseDto searchByName(String name) {
        Root spotifyData = getSpotifyData(name);
        if (spotifyData == null) {
            return null;
        }

        ResultResponseDto resultResponseDto = new ResultResponseDto();
        StringBuilder responseText = new StringBuilder();

        if (spotifyData.getTracks() != null && !spotifyData.getTracks().getItems().isEmpty()) {
            Item track = spotifyData.getTracks().getItems().get(0);
            responseText.append("Song: ").append(track.getData().getName())
                    .append("\nArtist: ").append(track.getData().getArtists().getItems().get(0).getProfile().getName())
                    .append("\nAlbum: ").append(track.getData().getAlbumOfTrack().getName()).append("\n\n");
        }

        if (spotifyData.getAlbums() != null && !spotifyData.getAlbums().getItems().isEmpty()) {
            Item album = spotifyData.getAlbums().getItems().get(0);
            responseText.append("Album: ").append(album.getData().getName())
                    .append("\nArtist: ").append(album.getData().getArtists().getItems().get(0).getProfile().getName()).append("\n\n");
        }

        if (spotifyData.getPodcasts() != null && !spotifyData.getPodcasts().getItems().isEmpty()) {
            Item podcast = spotifyData.getPodcasts().getItems().get(0);
            responseText.append("Podcast: ").append(podcast.getData().getName())
                    .append("\nHost: ").append(podcast.getData().getPublisher().getName()).append("\n\n");
        }

        if (responseText.length() == 0) {
            responseText.append("No results found");
        }

        resultResponseDto.setText(responseText.toString());

        RootResponseDto rootResponseDto = new RootResponseDto();
        rootResponseDto.setOk(true);
        rootResponseDto.setResult(resultResponseDto);
        return rootResponseDto;
    }

    @Scheduled(fixedDelay = 1000)
    public void refresh() {
        RootRequestDto updateService = getUpdateService();
        if (updateService != null && !updateService.getResult().isEmpty()) {
            Integer latestUpdateId = updateService.getResult().get(updateService.getResult().size() - 1).getUpdateId();
            if (latestUpdateId > lastUpdateId) {
                lastUpdateId = Long.valueOf(latestUpdateId);
                response();
            }
        }
    }

    public void response() {
        RootRequestDto updateService = getUpdateService();
        if (updateService != null && !updateService.getResult().isEmpty()) {
            String text = updateService.getResult().get(0).getMessage().getText();
            Long id = updateService.getResult().get(0).getMessage().getChat().getId();
            TelegramSendDto dto = new TelegramSendDto();
            dto.setChatId(String.valueOf(id));

            RootResponseDto searchResult = searchByName(text);
            dto.setText(searchResult != null ? searchResult.getResult().getText() : "No results found");
            sendMessage(dto);
        }
    }
}
