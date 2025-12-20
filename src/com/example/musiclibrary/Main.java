package com.example.musiclibrary;

public class Main {
    public static void main(String[] args) {

        Artist a1 = new Artist("The Weeknd", "Pop/R&B");
        Artist a2 = new Artist("The Weeknd", "Pop/R&B");
        Artist a3 = new Artist("Daft Punk", "Electronic");

        Song s1 = new Song("Blinding Lights", a1, 200);
        Song s2 = new Song("Starboy", a1, 230);
        Song s3 = new Song("Get Lucky", a3, 248);

        Playlist p1 = new Playlist("My Favorites");
        p1.addSong(s1);
        p1.addSong(s3);

        Playlist p2 = new Playlist("My Favorites");
        p2.addSong(new Song("Blinding Lights", a2, 200));
        p2.addSong(new Song("Get Lucky", a3, 248));

        System.out.println("=== Artists ===");
        System.out.println(a1);
        System.out.println(a3);

        System.out.println("\n=== Songs ===");
        System.out.println(s1);
        System.out.println(s2);
        System.out.println(s3);

        System.out.println("\n=== Playlists ===");
        System.out.println(p1);
        System.out.println();
        System.out.println(p2);

        System.out.println("\n=== Comparisons ===");
        System.out.println("a1 equals a2? " + a1.equals(a2));
        System.out.println("p1 equals p2? " + p1.equals(p2));
    }
}
