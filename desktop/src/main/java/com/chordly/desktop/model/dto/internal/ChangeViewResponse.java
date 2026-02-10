package com.chordly.desktop.model.dto.internal;

import com.chordly.desktop.model.ui.Presentable;
import javafx.scene.Node;

public class ChangeViewResponse {
    private final Node view;

    public ChangeViewResponse(Node view) {
        this.view = view;
    }

    public Node getView() {
        return view;
    }
}
