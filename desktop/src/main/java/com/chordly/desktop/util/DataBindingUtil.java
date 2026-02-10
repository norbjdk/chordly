package com.chordly.desktop.util;

import com.chordly.desktop.manager.AppManager;
import com.chordly.desktop.model.dto.internal.OpenProjectRequest;
import com.chordly.desktop.model.entity.ProjectEntity;
import com.chordly.desktop.model.event.EventBus;
import com.chordly.desktop.model.event.OpenProjectEvent;
import com.chordly.desktop.model.event.ProjectCreatedEvent;
import javafx.application.Platform;

import java.util.function.Consumer;


public class DataBindingUtil {
    private final AppManager appManager;

    public DataBindingUtil() {
        this.appManager = AppManager.getInstance();
    }

    public void subscribeToProjectOpened(Consumer<OpenProjectEvent> handler) {
        EventBus.getInstance().subscribe(OpenProjectEvent.class, event -> {
            Platform.runLater(() -> handler.accept(event));
            Logger.debug("Project opened event handled");
        });
    }

    public void subscribeToProjectCreated(Consumer<ProjectCreatedEvent> handler) {
        EventBus.getInstance().subscribe(ProjectCreatedEvent.class, event -> {
            Platform.runLater(() -> handler.accept(event));
        });
    }
}
