package com.chordly.desktop.model.event;

public class LoginFailedEvent {
    private String errorMessage;

    public LoginFailedEvent(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() { return errorMessage; }
}
