package com.chordly.desktop.model.event;

public class ChangeProjectTitleEvent {
    private final String title;

    public ChangeProjectTitleEvent(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
