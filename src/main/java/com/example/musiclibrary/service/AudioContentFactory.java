package com.example.musiclibrary.service;

import com.example.musiclibrary.Artist;
import com.example.musiclibrary.Song;
import org.springframework.stereotype.Component;

@Component
public class AudioContentFactory {
    public Song createSong(String title, int durationSec, Artist artist) {
        return Song.builder()
                .title(title)
                .durationSec(durationSec)
                .artist(artist)
                .build();
    }
}
