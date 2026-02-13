package com.example.musiclibrary.service;

import com.example.musiclibrary.Artist;
import com.example.musiclibrary.exception.NotFoundException;
import com.example.musiclibrary.exception.ValidationException;
import com.example.musiclibrary.repository.ArtistRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArtistService {
    private final ArtistRepository repo;

    public ArtistService(ArtistRepository repo) {
        this.repo = repo;
    }

    public Integer create(Artist artist) {
        validate(artist);
        return repo.create(artist);
    }

    public Artist getById(Integer id) {
        Artist a = repo.findById(id);
        if (a == null) throw new NotFoundException("Artist " + id + " not found");
        return a;
    }

    public List<Artist> getAll() {
        return repo.findAll();
    }

    public void update(Integer id, Artist artist) {
        validate(artist);
        if (!repo.existsById(id)) throw new NotFoundException("Artist " + id + " not found");
        repo.update(id, artist);
    }

    public void delete(Integer id) {
        if (!repo.existsById(id)) throw new NotFoundException("Artist " + id + " not found");
        repo.delete(id);
    }

    private void validate(Artist artist) {
        if (artist == null) throw new ValidationException("Artist is null");
        if (artist.getName() == null || artist.getName().isBlank()) throw new ValidationException("Artist name is empty");
        if (artist.getGenre() == null || artist.getGenre().isBlank()) throw new ValidationException("Artist genre is empty");
    }
}
