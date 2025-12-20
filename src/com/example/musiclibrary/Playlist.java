package com.example.musiclibrary;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Playlist {
    private String name;
    private List<Song> songs;

    public Playlist(String name) {
        this.name = name;
        this.songs = new ArrayList<>();
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public List<Song> getSongs() { return songs; }

    public void addSong(Song song) { songs.add(song); }

    @Override
    public String toString() {
        return "Playlist{name='" + name + "', songs=" + songs + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Playlist)) return false;
        Playlist playlist = (Playlist) o;
        return Objects.equals(name, playlist.name) &&
                Objects.equals(songs, playlist.songs);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, songs);
    }
}
