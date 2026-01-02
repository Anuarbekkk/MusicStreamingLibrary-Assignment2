package com.example.musiclibrary;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public class Playlist {
    private String name;
    private List<Song> songs;

    public Playlist(String name) {
        this.name = name;
        this.songs = new ArrayList<>();
    }

    // Encapsulation (get/set)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Song> getSongs() {
        return songs;
    }

    // Data pool action: add song
    public void addSong(Song song) {
        songs.add(song);
    }

    // Searching
    public Song findSongByTitle(String title) {
        for (Song song : songs) {
            if (song.getTitle().equalsIgnoreCase(title)) {
                return song;
            }
        }
        return null;
    }

    // Filtering
    public List<Song> filterByArtist(String artistName) {
        List<Song> result = new ArrayList<>();
        for (Song song : songs) {
            if (song.getArtist().getName().equalsIgnoreCase(artistName)) {
                result.add(song);
            }
        }
        return result;
    }

    // Sorting
    public void sortByDuration() {
        songs.sort(Comparator.comparingInt(Song::getDurationSec));
    }

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
