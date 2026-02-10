package com.chordly.desktop.component.music;

import com.chordly.desktop.util.FontFactory;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class StaffPane extends Canvas {
    private static final double SPACING = 10;
    private static final int LINES = 5;
    private static final double HEIGHT = SPACING * (LINES - 1);

    private GraphicsContext gc;
    private String clef = "G";
    private int clefLine = 2;

    public StaffPane(double width) {
        super(width, 100);
        gc = getGraphicsContext2D();
        draw();
    }

    public void setClef(String clef, int clefLine) {
        this.clef = clef;
        this.clefLine = clefLine;
        draw();
    }

    private void draw() {
        gc.clearRect(0, 0, getWidth(), getHeight());

        drawStaffLines();

        drawClef();
    }

    private void drawStaffLines() {
        gc.setStroke(Color.BLACK);
        gc.setLineWidth(1.0);

        double startY = 20;

        for (int i = 0; i < LINES; i++) {
            double y = startY + (i * SPACING);
            gc.strokeLine(0, y, getWidth(), y);
        }
    }

    private void drawClef() {
        Font clefFont = FontFactory.getBravura(48);
        gc.setFont(clefFont);
        gc.setFill(Color.BLACK);

        String clefSymbol;
        double x = 10;
        double y = 20 + (SPACING * 2);

        switch (clef) {
            case "G":
                clefSymbol = FontFactory.getGClef();
                y = 20 + (SPACING * 3.5);
            case "F":
                clefSymbol = FontFactory.getFClef();
                y = 20 + (SPACING * 1.5);
                break;
            case "C":
                clefSymbol = FontFactory.getCClef();
                y = 20 + (SPACING * 2);
                break;
            default:
                clefSymbol = FontFactory.getGClef();
        }

        gc.fillText(clefSymbol, x, y);
    }
}
