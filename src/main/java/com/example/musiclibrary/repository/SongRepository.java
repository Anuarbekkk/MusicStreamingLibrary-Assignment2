package com.example.musiclibrary.repository;

import com.example.musiclibrary.Song;
import java.util.List;

public interface SongRepository extends CrudRepository<Song, Integer> {
    List<Song> findAllWithArtist();
}
