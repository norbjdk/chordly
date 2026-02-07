package com.chordly.desktop.util;

import javafx.scene.text.Font;

public final class FontFactory {
    private FontFactory() {}

    public static Font getBravura(int size) {
        return Font.loadFont(FontFactory.class.getResourceAsStream("/com/chordly/desktop/font/Bravura.otf"), size);
    }

    public static String getGClef() {
        return "\uD834\uDD1E";
    }

    public static String getFClef() {
        return "\uD834\uDD22";
    }

    public static String getWholeNote() {
        return "\uD834\uDDBA";
    }

    public static String getHalfNote() {
        return "\uD834\uDDBB";
    }

    public static String getQuarterNote() {
        return "\uD834\uDD5F";
    }

    public static String getQuaverNote() {
        return "\uD834\uDD60";
    }

    public static String getSemiquaverNote() {
        return "\uD834\uDD61";
    }

    public static String getThirtyTwoNote() {
        return "\uD834\uDD62";
    }

    public enum Dynamic {
        P, F, M, MF, MP, FF, PP
    }

    public static String getDynamic(Dynamic dynamic) {
        String toReturn;
        switch (dynamic) {
            case P -> toReturn = "\uD834\uDD19";
            case F -> toReturn = "\uD834\uDD1E";
            case M -> toReturn = "\uD834\uDD1A";
            case MF -> toReturn = "\uE52D";
            case MP -> toReturn = "\uE52C";
            case FF -> toReturn = "\uE531";
            case PP -> toReturn = "\uE52B";
            case null, default -> throw new RuntimeException("Incorrect dynamic");
        }
        return toReturn;
    }
}
