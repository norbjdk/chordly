package com.chordly.desktop.model.event;

import com.chordly.desktop.model.dto.internal.OpenProjectResponse;

public class ProjectOpenedEvent {
    private final OpenProjectResponse response;

    public ProjectOpenedEvent(OpenProjectResponse response) {
        this.response = response;
    }

    public OpenProjectResponse getResponse() {
        return response;
    }
}
