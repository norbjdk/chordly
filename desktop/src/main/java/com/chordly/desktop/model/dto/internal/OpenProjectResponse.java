package com.chordly.desktop.model.dto.internal;

import com.chordly.desktop.model.entity.ProjectEntity;

public class OpenProjectResponse {
    private final ProjectEntity project;

    public OpenProjectResponse(ProjectEntity project) {
        this.project = project;
    }

    public ProjectEntity getProject() {
        return project;
    }
}
