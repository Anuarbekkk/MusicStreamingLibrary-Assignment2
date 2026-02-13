package com.example.musiclibrary.controller;

import com.example.musiclibrary.Artist;
import com.example.musiclibrary.service.ArtistService;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

@RestController
@RequestMapping("/api/artists")
public class ArtistController {

    private final ArtistService service;

    public ArtistController(ArtistService service) {
        this.service = service;
    }

    @GetMapping
    public List<Artist> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Artist getById(@PathVariable Integer id) {
        return service.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Artist create(@RequestBody Artist artist) {
        Integer id = service.create(artist);
        artist.setId(id);
        return artist;
    }

    @PutMapping("/{id}")
    public void update(@PathVariable Integer id, @RequestBody Artist artist) {
        service.update(id, artist);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        service.delete(id);
    }
}
