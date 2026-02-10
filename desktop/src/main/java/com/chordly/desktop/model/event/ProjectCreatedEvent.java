package com.chordly.desktop.model.event;

import com.chordly.desktop.model.dto.internal.CreateProjectResponse;
import com.chordly.desktop.model.entity.ProjectEntity;

public class ProjectCreatedEvent {
    private final CreateProjectResponse response;

    public ProjectCreatedEvent(CreateProjectResponse response) {
        this.response = response;
    }

    public CreateProjectResponse getResponse() {
        return response;
    }
}
