package com.chordly.desktop.util;

public class Logger {
    private Logger() {}

    public static void debug(String message) {
        System.out.println("[CHORDLY] " + message);
    }

    public static void err(String message) {
        System.err.println("[CHORDLY]" + message);
    }
}
