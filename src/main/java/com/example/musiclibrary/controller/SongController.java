package com.example.musiclibrary.controller;

import com.example.musiclibrary.Song;
import com.example.musiclibrary.dto.SongCreateRequest;
import com.example.musiclibrary.service.SongService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/songs")
public class SongController {

    private final SongService service;

    public SongController(SongService service) {
        this.service = service;
    }

    @GetMapping
    public List<Song> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Song getById(@PathVariable Integer id) {
        return service.getById(id);
    }

    @GetMapping("/search")
    public List<Song> search(@RequestParam String q) {
        return service.searchByTitle(q);
    }

    @GetMapping("/filter")
    public List<Song> filter(@RequestParam String artistName) {
        return service.filterByArtistName(artistName);
    }

    @GetMapping("/sorted")
    public List<Song> sorted() {
        return service.sortByDuration();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Song create(@RequestBody SongCreateRequest req) {
        Integer id = service.create(req);
        return service.getById(id);
    }

    @PutMapping("/{id}")
    public Song update(@PathVariable Integer id, @RequestBody SongCreateRequest req) {
        service.update(id, req);
        return service.getById(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        service.delete(id);
    }
}
