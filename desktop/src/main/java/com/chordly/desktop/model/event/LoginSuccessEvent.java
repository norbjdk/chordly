package com.chordly.desktop.model.event;

import com.chordly.desktop.model.entity.UserEntity;

public class LoginSuccessEvent {
    private UserEntity user;

    public LoginSuccessEvent(UserEntity user) {
        this.user = user;
    }

    public UserEntity getUser() { return user; }
}
