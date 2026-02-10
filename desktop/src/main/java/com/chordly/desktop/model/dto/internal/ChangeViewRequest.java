package com.chordly.desktop.model.dto.internal;

import com.chordly.desktop.model.ui.ViewName;

public class ChangeViewRequest {
    private final ViewName viewName;

    public ChangeViewRequest(ViewName viewName) {
        this.viewName = viewName;
    }

    public ViewName getViewName() {
        return viewName;
    }
}
