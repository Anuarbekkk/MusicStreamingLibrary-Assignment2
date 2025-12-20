package com.example.musiclibrary;

import java.util.Objects;

public class Artist {
    private String name;
    private String genre;

    public Artist(String name, String genre) {
        this.name = name;
        this.genre = genre;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getGenre() { return genre; }
    public void setGenre(String genre) { this.genre = genre; }

    @Override
    public String toString() {
        return "Artist{name='" + name + "', genre='" + genre + "'}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Artist)) return false;
        Artist artist = (Artist) o;
        return Objects.equals(name, artist.name) &&
                Objects.equals(genre, artist.genre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, genre);
    }
}
