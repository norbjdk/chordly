package com.chordly.desktop.model.dto.internal;

public class SheetData {
    private String title = "";
    private String subtitle = "";
    private String composer = "";
    private Template template;

    public enum Template {
        TREBLE, BASS, GRAND
    }

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

    public Template getTemplate() {
        return template;
    }

    public void setTemplate(Template template) {
        this.template = template;
    }
}
