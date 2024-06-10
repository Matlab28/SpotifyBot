package com.example.spotifybot.config;

import com.example.spotifybot.dto.spotify.Date;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;

public class DateDeserializer extends StdDeserializer<Date> {

    protected DateDeserializer() {
        super(Date.class);
    }

    @Override
    public Date deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonNode node = p.readValueAsTree();
        String isoString = node.get("isoString").asText();
        return Date.fromIsoString(isoString);
    }
}
