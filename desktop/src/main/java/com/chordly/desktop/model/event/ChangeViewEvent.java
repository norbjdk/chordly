package com.chordly.desktop.model.event;

public class ChangeViewEvent {
    private final String viewName;

    public ChangeViewEvent(String viewName) {
        this.viewName = viewName;
    }

    public String getViewName() {
        return viewName;
    }
}
