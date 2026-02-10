package com.chordly.desktop.manager;

import com.chordly.desktop.model.dto.internal.ChangeViewRequest;
import com.chordly.desktop.model.dto.internal.ChangeViewResponse;
import com.chordly.desktop.model.event.ChangeViewEvent;
import com.chordly.desktop.model.event.EventBus;
import com.chordly.desktop.model.ui.ViewName;
import com.chordly.desktop.view.CreateProjectView;
import com.chordly.desktop.view.HomeView;
import com.chordly.desktop.view.ProjectView;
import javafx.scene.Node;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ViewManager {
    private static ViewManager viewManager;
    private final Map<ViewName, Node> views;
    private Node currentView;

    private ViewManager() {
        views = new ConcurrentHashMap<>();
        initViews();

    }

    protected static ViewManager getInstance() {
        if (viewManager == null) {
            viewManager = new ViewManager();
        }
        return viewManager;
    }

    protected void changeView(ChangeViewRequest request) {
        if (views.get(request.getViewName()) != null) {
            currentView = views.get(request.getViewName());
            EventBus.getInstance().publish(new ChangeViewEvent(new ChangeViewResponse(currentView)));
        }
    }

    private void initViews() {
        views.put(ViewName.HOME, new HomeView());
        views.put(ViewName.PROJECT, new ProjectView());
        views.put(ViewName.CREATE_PROJECT, new CreateProjectView());
    }
}
