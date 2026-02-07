package com.chordly.desktop.component;

import com.chordly.desktop.model.dto.internal.SheetData;
import javafx.scene.layout.AnchorPane;

import java.util.Objects;

public class SheetComponent extends AnchorPane {
    private final SheetData data;
    private int sheetWidth;
    private int sheetHeight;

    public SheetComponent(SheetData data) {
        this.data = data;
        this.sheetHeight = 550;
        this.sheetWidth = 700;
        this.setPrefSize(sheetWidth, sheetHeight);
        getStylesheets().add(Objects.requireNonNull(getClass().getResource("/com/chordly/desktop/style/sheet.css")).toExternalForm());
        getStyleClass().add("sheet");
    }

    public SheetData getData() {
        return data;
    }

    public void setSheetHeight(int sheetHeight) {
        this.sheetHeight = sheetHeight;
    }

    public void setSheetWidth(int sheetWidth) {
        this.sheetWidth = sheetWidth;
    }
}
