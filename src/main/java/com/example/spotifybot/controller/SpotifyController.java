package com.example.spotifybot.controller;

import com.example.spotifybot.dto.request.TelegramSendDto;
import com.example.spotifybot.dto.response.RootResponseDto;
import com.example.spotifybot.service.SpotifyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/spotify")
@RequiredArgsConstructor
public class SpotifyController {

    private final SpotifyService spotifyService;

    @GetMapping("/search")
    public ResponseEntity<RootResponseDto> searchByName(@RequestParam String name) {
        RootResponseDto response = spotifyService.searchByName(name);
        if (response != null) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/send")
    public ResponseEntity<RootResponseDto> sendMessage(@RequestBody TelegramSendDto telegramSendDto) {
        RootResponseDto response = spotifyService.sendMessage(telegramSendDto);
        if (response != null) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }
}
