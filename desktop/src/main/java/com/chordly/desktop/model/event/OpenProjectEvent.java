package com.chordly.desktop.model.event;

import com.chordly.desktop.model.dto.internal.OpenProjectRequest;

public class OpenProjectEvent {
    private final OpenProjectRequest request;

    public OpenProjectEvent(OpenProjectRequest request) {
        this.request = request;
    }

    public OpenProjectRequest getRequest() {
        return request;
    }
}
