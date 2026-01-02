package com.example.musiclibrary;

public abstract class AudioContent {
    protected String title;
    protected int durationSec;

    public AudioContent(String title, int durationSec) {
        this.title = title;
        this.durationSec = durationSec;
    }

    public String getTitle() {
        return title;
    }

    public int getDurationSec() {
        return durationSec;
    }

    public abstract void play();
}
