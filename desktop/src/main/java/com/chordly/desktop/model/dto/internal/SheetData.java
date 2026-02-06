package com.chordly.desktop.model.dto.internal;

public class SheetData {
    private String title = "";
    private String subtitle = "";
    private String composer = "";

    public SheetData() {}

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getComposer() {
        return composer;
    }

    public void setComposer(String composer) {
        this.composer = composer;
    }
}
