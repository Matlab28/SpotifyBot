package com.example.spotifybot.client;

import com.example.spotifybot.dto.spotify.Root;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "spotifyApi", url = "https://spotify23.p.rapidapi.com")
public interface SpotifyApiClient {

    @GetMapping("/search/")
    Root getData(@RequestHeader("x-rapidapi-host") String host,
                 @RequestHeader("x-rapidapi-key") String apiKey,
                 @RequestParam("type") String type,
                 @RequestParam("q") String query,
                 @RequestParam("offset") int offset,
                 @RequestParam("limit") int limit,
                 @RequestParam("numberOfTopResults") int numberOfTopResults);
}



//package com.example.spotifybot.client;
//
//import com.example.spotifybot.dto.spotify.Root;
//import org.springframework.cloud.openfeign.FeignClient;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestHeader;
//
//@FeignClient(name = "spotifyApi", url = "https://spotify23.p.rapidapi.com/search/?type=multi&offset=0&limit=10&numberOfTopResults=5")
//public interface SpotifyApiClient {
//
//    @GetMapping
//    Root getData(@RequestHeader("x-rapidapi-host") String host,
//                 @RequestHeader("x-rapidapi-key") String apiKey);
//}
