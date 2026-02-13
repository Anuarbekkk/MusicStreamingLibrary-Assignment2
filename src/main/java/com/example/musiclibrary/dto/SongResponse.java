package com.example.musiclibrary.dto;

public class SongResponse {
    private Integer id;
    private String title;
    private Integer durationSec;
    private Integer artistId;

    public SongResponse() {}

    public SongResponse(Integer id, String title, Integer durationSec, Integer artistId) {
        this.id = id;
        this.title = title;
        this.durationSec = durationSec;
        this.artistId = artistId;
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public Integer getDurationSec() { return durationSec; }
    public void setDurationSec(Integer durationSec) { this.durationSec = durationSec; }

    public Integer getArtistId() { return artistId; }
    public void setArtistId(Integer artistId) { this.artistId = artistId; }
}
