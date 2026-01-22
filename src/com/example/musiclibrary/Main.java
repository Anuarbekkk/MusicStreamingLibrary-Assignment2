package com.example.musiclibrary;

import com.example.musiclibrary.db.ArtistDao;
import com.example.musiclibrary.db.SongDao;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {

        // Artists
        Artist a1 = new Artist("The Weeknd", "Pop/R&B");
        Artist a2 = new Artist("The Weeknd", "Pop/R&B");
        Artist a3 = new Artist("Daft Punk", "Electronic");

        // Songs
        Song s1 = new Song("Blinding Lights", a1, 200);
        Song s2 = new Song("Starboy", a1, 230);
        Song s3 = new Song("Get Lucky", a3, 248);

        // Playlist (data pool)
        Playlist playlist = new Playlist("My Favorites");
        playlist.addSong(s1);
        playlist.addSong(s2);
        playlist.addSong(s3);

        // Output all
        System.out.println("=== All songs ===");
        System.out.println(playlist);

        // Searching
        System.out.println("\n=== Search by title ===");
        System.out.println(playlist.findSongByTitle("Starboy"));

        // Filtering
        System.out.println("\n=== Filter by artist ===");
        System.out.println(playlist.filterByArtist("The Weeknd"));

        // Sorting
        System.out.println("\n=== Sort by duration ===");
        playlist.sortByDuration();
        System.out.println(playlist);

        // Polymorphism
        System.out.println("\n=== Polymorphism demo ===");
        AudioContent content = s1;
        content.play();

        // Equals demo
        System.out.println("\n=== Equals demo ===");
        System.out.println("a1 equals a2? " + a1.equals(a2));

        // ===== DB DEMO (Assignment 3) =====
        try {
            ArtistDao artistDao = new ArtistDao();
            SongDao songDao = new SongDao();

            System.out.println("\n=== DB: INSERT ===");
            int weekndId = artistDao.createArtist("The Weeknd", "Pop/R&B");
            int daftId = artistDao.createArtist("Daft Punk", "Electronic");

            int song1Id = songDao.createSong("Blinding Lights", 200, weekndId);
            int song2Id = songDao.createSong("Get Lucky", 248, daftId);

            System.out.println("\n=== DB: SELECT ===");
            System.out.println(artistDao.getAllArtists());
            System.out.println(songDao.getAllSongs());

            System.out.println("\n=== DB: UPDATE ===");
            artistDao.updateArtistGenre(weekndId, "R&B");
            songDao.updateSongDuration(song1Id, 210);
            System.out.println(songDao.getAllSongs());

            System.out.println("\n=== DB: DELETE ===");
            songDao.deleteSong(song2Id);
            System.out.println(songDao.getAllSongs());

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
