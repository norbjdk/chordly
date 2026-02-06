package com.chordly.desktop.model.ui;

import javafx.fxml.Initializable;

public interface UIElement extends Presentable, Stylable {
    void applyEvents();
    void applyListeners();
}
