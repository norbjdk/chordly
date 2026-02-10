package com.chordly.desktop.model.event;

import com.chordly.desktop.model.dto.internal.ChangeViewRequest;
import com.chordly.desktop.model.dto.internal.ChangeViewResponse;

public class ChangeViewEvent {
    private final ChangeViewResponse response;

    public ChangeViewEvent(ChangeViewResponse response) {
        this.response = response;
    }

    public ChangeViewResponse getResponse() {
        return response;
    }
}
