package com.example.musiclibrary;

import java.util.Objects;

public class Song extends AudioContent {
    private Artist artist;

    public Song(String title, Artist artist, int durationSec) {
        super(title, durationSec);
        this.artist = artist;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    @Override
    public void play() {
        System.out.println("Playing: " + title + " by " + artist.getName());
    }

    @Override
    public String toString() {
        return "Song{title='" + title + "', artist=" + artist + ", durationSec=" + durationSec + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Song)) return false;
        Song song = (Song) o;
        return durationSec == song.durationSec &&
                Objects.equals(title, song.title) &&
                Objects.equals(artist, song.artist);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, artist, durationSec);
    }
}
