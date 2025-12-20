package com.example.musiclibrary;

import java.util.Objects;

public class Song {
    private String title;
    private Artist artist;
    private int durationSec;

    public Song(String title, Artist artist, int durationSec) {
        this.title = title;
        this.artist = artist;
        this.durationSec = durationSec;
    }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public Artist getArtist() { return artist; }
    public void setArtist(Artist artist) { this.artist = artist; }

    public int getDurationSec() { return durationSec; }
    public void setDurationSec(int durationSec) { this.durationSec = durationSec; }

    @Override
    public String toString() {
        return "Song{title='" + title + "', artist=" + artist +
                ", durationSec=" + durationSec + "}";
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
