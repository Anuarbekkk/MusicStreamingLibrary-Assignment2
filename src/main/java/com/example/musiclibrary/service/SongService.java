package com.example.musiclibrary.service;

import com.example.musiclibrary.Artist;
import com.example.musiclibrary.Song;
import com.example.musiclibrary.dto.SongCreateRequest;
import com.example.musiclibrary.exception.NotFoundException;
import com.example.musiclibrary.exception.ValidationException;
import com.example.musiclibrary.repository.ArtistRepository;
import com.example.musiclibrary.repository.SongRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SongService {

    private final SongRepository songRepo;
    private final ArtistRepository artistRepo;
    private final AudioContentFactory factory;

    public SongService(SongRepository songRepo, ArtistRepository artistRepo, AudioContentFactory factory) {
        this.songRepo = songRepo;
        this.artistRepo = artistRepo;
        this.factory = factory;
    }

    public Integer create(SongCreateRequest req) {
        validate(req);

        Artist artist = artistRepo.findById(req.getArtistId());
        if (artist == null) throw new ValidationException("Artist " + req.getArtistId() + " does not exist");

        Song song = factory.createSong(req.getTitle(), req.getDurationSec(), artist);
        return songRepo.create(song);
    }

    public Song getById(Integer id) {
        Song s = songRepo.findById(id);
        if (s == null) throw new NotFoundException("Song " + id + " not found");
        return s;
    }

    public List<Song> getAll() {
        return songRepo.findAllWithArtist();
    }

    public void update(Integer id, SongCreateRequest req) {
        validate(req);

        Song existing = songRepo.findById(id);
        if (existing == null) throw new NotFoundException("Song " + id + " not found");

        Artist artist = artistRepo.findById(req.getArtistId());
        if (artist == null) throw new ValidationException("Artist " + req.getArtistId() + " does not exist");

        Song updated = factory.createSong(req.getTitle(), req.getDurationSec(), artist);
        updated.setId(id);
        songRepo.update(id, updated);
    }

    public void delete(Integer id) {
        Song existing = songRepo.findById(id);
        if (existing == null) throw new NotFoundException("Song " + id + " not found");
        songRepo.delete(id);
    }

    public List<Song> searchByTitle(String q) {
        if (q == null || q.isBlank()) throw new ValidationException("q is required");
        String needle = q.toLowerCase();
        return songRepo.findAllWithArtist().stream()
                .filter(s -> s.getTitle() != null && s.getTitle().toLowerCase().contains(needle))
                .toList();
    }

    public List<Song> filterByArtistName(String artistName) {
        if (artistName == null || artistName.isBlank()) throw new ValidationException("artistName is required");
        String needle = artistName.toLowerCase();
        return songRepo.findAllWithArtist().stream()
                .filter(s -> s.getArtist() != null && s.getArtist().getName() != null)
                .filter(s -> s.getArtist().getName().toLowerCase().contains(needle))
                .toList();
    }

    public List<Song> sortByDuration() {
        return songRepo.findAllWithArtist().stream()
                .sorted((a, b) -> Integer.compare(a.getDurationSec(), b.getDurationSec()))
                .toList();
    }

    private void validate(SongCreateRequest req) {
        if (req == null) throw new ValidationException("Body is required");

        if (req.getTitle() == null || req.getTitle().trim().isEmpty())
            throw new ValidationException("title is required");

        if (req.getDurationSec() == null || req.getDurationSec() <= 0)
            throw new ValidationException("durationSec must be > 0");

        if (req.getArtistId() == null || req.getArtistId() <= 0)
            throw new ValidationException("artistId must be > 0");
    }
}
