package com.chordly.desktop.manager;

import com.chordly.desktop.model.dto.internal.CreateProjectRequest;
import com.chordly.desktop.model.dto.internal.CreateProjectResponse;
import com.chordly.desktop.model.dto.internal.OpenProjectRequest;
import com.chordly.desktop.model.dto.internal.OpenProjectResponse;
import com.chordly.desktop.model.entity.ProjectEntity;
import com.chordly.desktop.model.event.EventBus;
import com.chordly.desktop.model.event.ProjectCreatedEvent;
import com.chordly.desktop.model.event.ProjectOpenedEvent;
import com.chordly.desktop.service.ProjectService;
import com.chordly.desktop.util.Logger;

public class ProjectManager {
    private static ProjectManager instance;
    private final ProjectService service;
    private ProjectEntity project;

    private ProjectManager() {
        service = new ProjectService();
        Logger.debug("Created Project Manager Instance");
    }

    public static ProjectManager getInstance() {
        if (instance == null) {
            instance = new ProjectManager();
        }
        Logger.debug("Obtained Project Manager Instance");
        return instance;
    }

    protected void createProject(CreateProjectRequest request) {
        project = service.createProject(request);
        EventBus.getInstance().publish(new ProjectCreatedEvent(new CreateProjectResponse(project)));
        Logger.debug("Created " + project.toString());
    }

    protected void openProject(OpenProjectRequest request) {
        project = service.openProject(request);
        Logger.debug("Opened project: " + request.getFile().getName());
        EventBus.getInstance().publish(new ProjectOpenedEvent(new OpenProjectResponse(project)));
    }

    protected ProjectEntity getProject() {
        return project;
    }
}
