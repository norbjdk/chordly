package com.chordly.desktop.manager;

import com.chordly.desktop.model.dto.internal.ChangeViewRequest;
import com.chordly.desktop.model.dto.internal.CreateProjectRequest;
import com.chordly.desktop.model.dto.internal.OpenProjectRequest;
import com.chordly.desktop.model.entity.ProjectEntity;
import com.chordly.desktop.model.ui.ViewName;
import com.chordly.desktop.util.Logger;

public class AppManager {
    private static AppManager instance;
    private final ViewManager viewManager;
    private final ProjectManager projectManager;

    private AppManager() {
        viewManager = ViewManager.getInstance();
        projectManager = ProjectManager.getInstance();
        Logger.debug("App Manager Initialized");
    }

    public static AppManager getInstance() {
        if (instance == null) {
            instance = new AppManager();
        }
        return instance;
    }

    public void changeView(ChangeViewRequest request) {
        viewManager.changeView(request);
    }

    public void createProject(CreateProjectRequest request) {
        projectManager.createProject(request);
        changeView(new ChangeViewRequest(ViewName.PROJECT));
    }

    public void openProject(OpenProjectRequest request) {
        projectManager.openProject(request);
        changeView(new ChangeViewRequest(ViewName.PROJECT));
    }
}
